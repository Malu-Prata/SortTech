package com.estudotempo;

import com.estudotempo.server.ServidorWeb;

import java.awt.Desktop;
import java.net.URI;

public class Main {
    private static final int PORTA_PADRAO = 8080;

    public static void main(String[] args) {
        int porta = PORTA_PADRAO;
        if (args.length > 0) {
            try {
                porta = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        try {
            ServidorWeb servidor = new ServidorWeb(porta, "web");
            servidor.iniciar();

            String url = "http://localhost:" + porta;
            System.out.println("==================================================");
            System.out.println("Servidor Java iniciado com sucesso.");
            System.out.println("Acesse a interface web em: " + url);
            System.out.println("Endpoints disponiveis:");
            System.out.println("   - GET " + url + "/api/areas");
            System.out.println("   - GET " + url + "/api/sortear?area={id}");
            System.out.println("Pressione Ctrl + C para encerrar o servidor.");
            System.out.println("==================================================");

            abrirNavegador(url);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\nEncerrando servidor...");
                servidor.parar();
            }));

        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void abrirNavegador(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                String os = System.getProperty("os.name").toLowerCase();
                Runtime runtime = Runtime.getRuntime();
                if (os.contains("nix") || os.contains("nux")) {
                    runtime.exec(new String[]{"xdg-open", url});
                } else if (os.contains("mac")) {
                    runtime.exec(new String[]{"open", url});
                } else if (os.contains("win")) {
                    runtime.exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
                }
            }
        } catch (Exception ignored) {}
    }
}
