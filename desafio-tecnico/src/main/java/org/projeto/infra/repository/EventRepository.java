package org.projeto.infra.repository;

import org.projeto.api.filter.EventoFilter;
import org.projeto.infra.entity.evento.EventoEntity;

import java.util.List;
import java.util.Optional;

public interface EventRepository {


    void salvarEvento(EventoEntity eventoEntity);

    Optional<List<EventoEntity>> buscarEventos(EventoFilter filter);

    Optional<EventoEntity> buscarEventoPorId(Integer id);

    void atualizarEvento(EventoEntity eventoEntity);


    void ativarEventos();

    void desativarEventos();

    void deletarEvento(Integer id);
}
