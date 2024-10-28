package org.projeto.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EventoTO extends Pageable {

    private Integer eventoId;

    @NotBlank(message = "Nome do evento é obrigatório")
    private String nome;

    @NotNull(message = "Data inicial é obrigatória")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dataInicial;

    @NotNull(message = "Data final é obrigatória")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dataFinal;

    @NotNull(message = "É obrigatório informar se o evento está ativo")
    private Boolean ativo;

    @NotNull(message = "Instituição é obrigatória")
    private InstituicaoTO instituicao;

    public EventoTO(){
        super();
    }

    public EventoTO(Integer page, Integer size){
        super(page, size);
    }

    public static EventoTOBuilder builder(){
        return new EventoTOBuilder();
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public InstituicaoTO getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(InstituicaoTO instituicao) {
        this.instituicao = instituicao;
    }

    public static class EventoTOBuilder{

        private Integer eventId;
        private String nome;
        private LocalDate dataInicial;
        private LocalDate dataFinal;
        private boolean ativo;
        private InstituicaoTO instituicao;
        private Integer page;
        private Integer size;

        public EventoTOBuilder(){
        }

        public EventoTOBuilder eventId(Integer eventId){
            this.eventId = eventId;
            return this;
        }

        public EventoTOBuilder nome(String nome){
            this.nome = nome;
            return this;
        }

        public EventoTOBuilder dataInicial(LocalDate dataInicial){
            this.dataInicial = dataInicial;
            return this;
        }

        public EventoTOBuilder dataFinal(LocalDate dataFinal){
            this.dataFinal = dataFinal;
            return this;
        }

        public EventoTOBuilder ativo(Boolean ativo){
            this.ativo = ativo;
            return this;
        }

        public EventoTOBuilder instituicao(InstituicaoTO instituicao){
            this.instituicao = instituicao;
            return this;
        }

        public EventoTOBuilder page(Integer page){
            this.page = page;
            return this;
        }

        public EventoTOBuilder size(Integer size){
            this.size = size;
            return this;
        }


        public EventoTO build(){
            EventoTO eventoTO = new EventoTO(page, size);
            eventoTO.setEventoId(this.eventId);
            eventoTO.setNome(this.nome);
            eventoTO.setDataInicial(this.dataInicial);
            eventoTO.setDataFinal(this.dataFinal);
            eventoTO.setAtivo(this.ativo);
            eventoTO.setInstituicao(this.instituicao);
            return eventoTO;
        }
    }
}
