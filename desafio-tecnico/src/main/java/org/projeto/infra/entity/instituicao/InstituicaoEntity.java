package org.projeto.infra.entity.instituicao;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.projeto.infra.entity.evento.EventoEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instituicoes")
public class InstituicaoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instituicao_id")
    private Integer instituicaoId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventoEntity> eventos =  new ArrayList<>(); ;

    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }

    public Integer getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Integer id) {
        this.instituicaoId = id;
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

    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void addEvento(EventoEntity eventoEntity){
        this.eventos.add(eventoEntity);
    }

    public static InstituicaoEntityBuilder builder(){
        return new InstituicaoEntityBuilder();
    }
    public static class InstituicaoEntityBuilder{
        private Integer id;
        private String nome;
        private String tipo;
        private List<EventoEntity> eventos;

        public InstituicaoEntityBuilder id(Integer id){
            this.id = id;
            return this;
        }

        public InstituicaoEntityBuilder nome(String nome){
            this.nome = nome;
            return this;
        }

        public InstituicaoEntityBuilder tipo(String tipo){
            this.tipo = tipo;
            return this;
        }

        public InstituicaoEntityBuilder eventos(List<EventoEntity> eventos){
            this.eventos = eventos;
            return this;
        }

        public InstituicaoEntity build(){
            InstituicaoEntity instituicaoEntity = new InstituicaoEntity();
            instituicaoEntity.setInstituicaoId(this.id);
            instituicaoEntity.setNome(this.nome);
            instituicaoEntity.setTipo(this.tipo);
            instituicaoEntity.setEventos(this.eventos);
            return instituicaoEntity;
        }
    }
}