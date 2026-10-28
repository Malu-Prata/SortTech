package com.estudotempo.service;

import com.estudotempo.model.AreaComputacao;
import com.estudotempo.model.TemaEstudo;

import java.util.*;

public class SorteadorService {

    private final Random random;
    private final Map<AreaComputacao, TemaEstudo> ultimoSorteadoPorArea;
    private final List<TemaEstudo> historico;

    public SorteadorService() {
        this.random = new Random();
        this.ultimoSorteadoPorArea = new HashMap<>();
        this.historico = new ArrayList<>();
    }

    
    public TemaEstudo sortear(AreaComputacao area) {
        if (area == null) {
            throw new IllegalArgumentException("Área não pode ser nula.");
        }

        List<TemaEstudo> temas = area.getTemas();
        if (temas.isEmpty()) {
            return null;
        }

        if (temas.size() == 1) {
            TemaEstudo unico = temas.get(0);
            historico.add(unico);
            ultimoSorteadoPorArea.put(area, unico);
            return unico;
        }

        TemaEstudo ultimo = ultimoSorteadoPorArea.get(area);
        TemaEstudo selecionado;
        do {
            int indice = random.nextInt(temas.size());
            selecionado = temas.get(indice);
        } while (selecionado.equals(ultimo));

        ultimoSorteadoPorArea.put(area, selecionado);
        historico.add(selecionado);
        return selecionado;
    }

    public static class ResultadoSorteio {
        private final AreaComputacao area;
        private final TemaEstudo tema;

        public ResultadoSorteio(AreaComputacao area, TemaEstudo tema) {
            this.area = area;
            this.tema = tema;
        }

        public AreaComputacao getArea() { return area; }
        public TemaEstudo getTema() { return tema; }
    }

    public ResultadoSorteio sortearEntreAreas(List<AreaComputacao> areas) {
        if (areas == null || areas.isEmpty()) {
            areas = Arrays.asList(AreaComputacao.values());
        }

        List<ResultadoSorteio> pool = new ArrayList<>();
        for (AreaComputacao a : areas) {
            for (TemaEstudo t : a.getTemas()) {
                pool.add(new ResultadoSorteio(a, t));
            }
        }

        if (pool.isEmpty()) return null;

        int indice = random.nextInt(pool.size());
        ResultadoSorteio escolhido = pool.get(indice);
        historico.add(escolhido.getTema());
        return escolhido;
    }

    public List<TemaEstudo> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public int getTotalSorteios() {
        return historico.size();
    }
}
