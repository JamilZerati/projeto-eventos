package org.projeto.api.filter;

import org.projeto.api.dto.Pageable;

import java.time.LocalDate;

public class EventoFilter extends Pageable {

    private Integer eventoId;

    private String nome;

    private LocalDate dataInicial;

    private LocalDate dataFinal;

    private Boolean ativo;

    private Integer instituicaoId;

    public EventoFilter() {
        super();
    }

    public EventoFilter(Integer page, Integer size) {
        super(page, size);
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

    public static EventoFilterBuilder builder() {
        return new EventoFilterBuilder();
    }

    public static class EventoFilterBuilder {

        private Integer eventId;
        private String nome;
        private LocalDate dataInicial;
        private LocalDate dataFinal;
        private Boolean ativo;
        private Integer instituicaoId;
        private Integer page;
        private Integer size;

        public EventoFilterBuilder() {
        }


        public EventoFilterBuilder eventId(Integer eventId) {
            this.eventId = eventId;
            return this;
        }

        public EventoFilterBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public EventoFilterBuilder dataInicial(LocalDate dataInicial) {
            this.dataInicial = dataInicial;
            return this;
        }

        public EventoFilterBuilder dataFinal(LocalDate dataFinal) {
            this.dataFinal = dataFinal;
            return this;
        }

        public EventoFilterBuilder ativo(Boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public EventoFilterBuilder instituicaoId(Integer instituicaoId) {
            this.instituicaoId = instituicaoId;
            return this;
        }

        public EventoFilterBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public EventoFilterBuilder size(Integer size) {
            this.size = size;
            return this;
        }


        public EventoFilter build() {
            EventoFilter eventoFilter = new EventoFilter(page, size);
            eventoFilter.setEventoId(this.eventId);
            eventoFilter.setNome(this.nome);
            eventoFilter.setDataInicial(this.dataInicial);
            eventoFilter.setDataFinal(this.dataFinal);
            eventoFilter.setAtivo(this.ativo);
            eventoFilter.setInstituicaoId(this.instituicaoId);
            return eventoFilter;
        }
    }
}
