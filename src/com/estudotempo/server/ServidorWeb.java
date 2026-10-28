package com.estudotempo.server;

import com.estudotempo.model.AreaComputacao;
import com.estudotempo.model.TemaEstudo;
import com.estudotempo.service.SorteadorService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ServidorWeb {

    private final int porta;
    private final SorteadorService sorteadorService;
    private final Path pastaWeb;
    private HttpServer server;

    public ServidorWeb(int porta, String caminhoWeb) {
        this.porta = porta;
        this.sorteadorService = new SorteadorService();
        this.pastaWeb = Paths.get(caminhoWeb).toAbsolutePath().normalize();
    }

    public void iniciar() throws IOException {
        server = HttpServer.create(new InetSocketAddress(porta), 0);

        server.createContext("/api/areas", new AreasHandler(pastaWeb));
        server.createContext("/api/sortear", new SortearHandler(sorteadorService));

        server.createContext("/", new ArquivosEstaticosHandler(pastaWeb));

        server.setExecutor(null);
        server.start();
    }

    public void parar() {
        if (server != null) {
            server.stop(0);
        }
    }

    public int getPorta() {
        return porta;
    }

    private static class AreasHandler implements HttpHandler {
        private final Path pastaWeb;

        public AreasHandler(Path pastaWeb) {
            this.pastaWeb = pastaWeb;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            configurarCors(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            Path arquivoJson = pastaWeb.resolve("temas.json");
            if (Files.exists(arquivoJson)) {
                String conteudo = new String(Files.readAllBytes(arquivoJson), StandardCharsets.UTF_8);
                enviarJson(exchange, 200, conteudo);
                return;
            }

            StringBuilder json = new StringBuilder("[");
            AreaComputacao[] areas = AreaComputacao.values();
            for (int i = 0; i < areas.length; i++) {
                json.append(areas[i].toJson(false));
                if (i < areas.length - 1) json.append(",");
            }
            json.append("]");

            enviarJson(exchange, 200, json.toString());
        }
    }

    private static class SortearHandler implements HttpHandler {
        private final SorteadorService sorteador;

        public SortearHandler(SorteadorService sorteador) {
            this.sorteador = sorteador;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            configurarCors(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            Map<String, String> params = extrairParametrosQuery(exchange.getRequestURI());
            String areaParam = params.get("areas");
            if (areaParam == null) areaParam = params.get("area");

            java.util.List<AreaComputacao> areasAlvo = new java.util.ArrayList<>();
            if (areaParam != null && !areaParam.trim().isEmpty() && !"todas".equalsIgnoreCase(areaParam)) {
                String[] partes = areaParam.split(",");
                for (String p : partes) {
                    AreaComputacao a = AreaComputacao.porId(p.trim());
                    if (a != null) areasAlvo.add(a);
                }
            }

            if (areasAlvo.isEmpty()) {
                areasAlvo = java.util.Arrays.asList(AreaComputacao.values());
            }

            SorteadorService.ResultadoSorteio resultado = sorteador.sortearEntreAreas(areasAlvo);
            if (resultado == null) {
                enviarJson(exchange, 500, "{\"erro\":\"Nenhum tema disponível.\"}");
                return;
            }

            String resposta = "{"
                + "\"area\":" + resultado.getArea().toJson(false) + ","
                + "\"tema\":" + resultado.getTema().toJson() + ","
                + "\"totalSorteios\":" + sorteador.getTotalSorteios()
                + "}";

            enviarJson(exchange, 200, resposta);
        }
    }

    private static class ArquivosEstaticosHandler implements HttpHandler {
        private final Path pastaWeb;

        public ArquivosEstaticosHandler(Path pastaWeb) {
            this.pastaWeb = pastaWeb;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            configurarCors(exchange);

            String pathStr = exchange.getRequestURI().getPath();
            if (pathStr.equals("/") || pathStr.isEmpty()) {
                pathStr = "/index.html";
            }

            Path arquivo = pastaWeb.resolve(pathStr.substring(1)).normalize();
            if (!arquivo.startsWith(pastaWeb) || !Files.exists(arquivo) || Files.isDirectory(arquivo)) {
                arquivo = pastaWeb.resolve("index.html");
                if (!Files.exists(arquivo)) {
                    byte[] erro = "404 - Arquivo não encontrado".getBytes(StandardCharsets.UTF_8);
                    exchange.sendResponseHeaders(404, erro.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(erro);
                    }
                    return;
                }
            }

            String mimeType = determinarMimeType(arquivo.getFileName().toString());
            exchange.getResponseHeaders().set("Content-Type", mimeType + "; charset=UTF-8");
            byte[] bytes = Files.readAllBytes(arquivo);
            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }

    private static void configurarCors(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }

    private static void enviarJson(HttpExchange exchange, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static Map<String, String> extrairParametrosQuery(URI uri) {
        Map<String, String> resultado = new HashMap<>();
        String query = uri.getQuery();
        if (query == null || query.isEmpty()) {
            return resultado;
        }

        String[] pares = query.split("&");
        for (String par : pares) {
            int idx = par.indexOf("=");
            try {
                if (idx > 0) {
                    String chave = URLDecoder.decode(par.substring(0, idx), "UTF-8");
                    String valor = URLDecoder.decode(par.substring(idx + 1), "UTF-8");
                    resultado.put(chave, valor);
                } else {
                    resultado.put(URLDecoder.decode(par, "UTF-8"), "");
                }
            } catch (UnsupportedEncodingException ignored) {}
        }
        return resultado;
    }

    private static String determinarMimeType(String nomeArquivo) {
        String lower = nomeArquivo.toLowerCase();
        if (lower.endsWith(".html") || lower.endsWith(".htm")) return "text/html";
        if (lower.endsWith(".css")) return "text/css";
        if (lower.endsWith(".js")) return "application/javascript";
        if (lower.endsWith(".json")) return "application/json";
        if (lower.endsWith(".svg")) return "image/svg+xml";
        if (lower.endsWith(".png")) return "image/png";
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
        if (lower.endsWith(".ico")) return "image/x-icon";
        return "text/plain";
    }
}
