package com.estudotempo.model;

public class TemaEstudo {
    private final String id;
    private final String titulo;
    private final String descricao;
    private final String nivel;
    private final String[] topicosChave;

    public TemaEstudo(String id, String titulo, String descricao, String nivel, String[] topicosChave) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivel = nivel;
        this.topicosChave = topicosChave;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNivel() {
        return nivel;
    }

    public String[] getTopicosChave() {
        return topicosChave;
    }

    
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"").append(escapeJson(id)).append("\",");
        sb.append("\"titulo\":\"").append(escapeJson(titulo)).append("\",");
        sb.append("\"descricao\":\"").append(escapeJson(descricao)).append("\",");
        sb.append("\"nivel\":\"").append(escapeJson(nivel)).append("\",");
        sb.append("\"topicosChave\":[");
        for (int i = 0; i < topicosChave.length; i++) {
            sb.append("\"").append(escapeJson(topicosChave[i])).append("\"");
            if (i < topicosChave.length - 1) sb.append(",");
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    private String escapeJson(String valor) {
        if (valor == null) return "";
        return valor.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\b", "\\b")
                    .replace("\f", "\\f")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

    @Override
    public String toString() {
        return titulo + " (" + nivel + ")";
    }
}
