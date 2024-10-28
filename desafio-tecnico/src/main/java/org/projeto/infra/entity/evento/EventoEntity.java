package org.projeto.infra.entity.evento;

import jakarta.persistence.*;
import org.projeto.infra.entity.instituicao.InstituicaoEntity;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "eventos")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id")
    private Integer eventoId;

    @Column(name = "nome")
    public String nome;

    @Column(name = "data_inicial")
    public LocalDate dataInicial;

    @Column(name = "data_final")
    public LocalDate dataFinal;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private InstituicaoEntity instituicao;


    public Integer getEventoId() {
        return eventoId;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public InstituicaoEntity getInstituicao() {
        return instituicao;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setInstituicao(InstituicaoEntity instituicao) {
        this.instituicao = instituicao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;

    }

    public static EventoEntityBuilder builder() {
        return new EventoEntityBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoEntity evento = (EventoEntity) o;
        return Objects.equals(eventoId, evento.eventoId) && Objects.equals(nome, evento.nome) && Objects.equals(dataInicial, evento.dataInicial) && Objects.equals(dataFinal, evento.dataFinal) && Objects.equals(ativo, evento.ativo) && Objects.equals(instituicao.getInstituicaoId(), evento.instituicao.getInstituicaoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, nome, dataInicial, dataFinal, ativo, instituicao);
    }

    public static class EventoEntityBuilder {
        private Integer eventId;
        private String nome;
        private LocalDate dataInicial;
        private LocalDate dataFinal;
        private InstituicaoEntity instituicao;
        private boolean ativo;

        public EventoEntityBuilder() {
        }

        public EventoEntityBuilder eventId(Integer eventId) {
            this.eventId = eventId;
            return this;
        }

        public EventoEntityBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public EventoEntityBuilder dataInicial(LocalDate dataInicial) {
            this.dataInicial = dataInicial;
            return this;
        }

        public EventoEntityBuilder dataFinal(LocalDate dataFinal) {
            this.dataFinal = dataFinal;
            return this;
        }

        public EventoEntityBuilder instituicao(InstituicaoEntity instituicao) {
            this.instituicao = instituicao;
            return this;
        }

        public EventoEntityBuilder ativo(boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public EventoEntity build() {
            EventoEntity eventoEntity = new EventoEntity();
            eventoEntity.setEventoId(this.eventId);
            eventoEntity.setNome(this.nome);
            eventoEntity.setDataInicial(this.dataInicial);
            eventoEntity.setDataFinal(this.dataFinal);
            eventoEntity.setInstituicao(this.instituicao);
            eventoEntity.setAtivo(this.ativo);
            return eventoEntity;
        }
    }


}
