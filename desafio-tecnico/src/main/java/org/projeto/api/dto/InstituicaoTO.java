package org.projeto.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class InstituicaoTO {

    @NotNull
    private Integer id;

    private String nome;

    private String tipo;
    private List<EventoTO> eventos;


    public InstituicaoTO(){
    }

    public InstituicaoTO(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<EventoTO> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoTO> eventos) {
        this.eventos = eventos;
    }


    public static InstituicaoTOBuilder builder(){
        return new InstituicaoTOBuilder();
    }

    public static class InstituicaoTOBuilder {
        private Integer id;
        private String nome;
        private String tipo;
        private List<EventoTO> eventos;

        public InstituicaoTOBuilder id(Integer id){
            this.id = id;
            return this;
        }

        public InstituicaoTOBuilder nome(String nome){
            this.nome = nome;
            return this;
        }

        public InstituicaoTOBuilder tipo(String tipo){
            this.tipo = tipo;
            return this;
        }

        public InstituicaoTOBuilder eventos(List<EventoTO> eventos){
            this.eventos = eventos;
            return this;
        }


        public InstituicaoTO build(){
            InstituicaoTO instituicaoTO = new InstituicaoTO();
            instituicaoTO.setId(this.id);
            instituicaoTO.setNome(this.nome);
            instituicaoTO.setTipo(this.tipo);
            instituicaoTO.setEventos(this.eventos);
            return instituicaoTO;
        }
    }
}
