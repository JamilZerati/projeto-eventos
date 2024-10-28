package org.projeto.domain.service.util;

import org.projeto.api.dto.EventoTO;
import org.projeto.api.filter.EventoFilter;
import org.projeto.infra.entity.evento.EventoEntity;

public class EventoParser {

    public static EventoTO toTO(EventoEntity eventoEntity) {
        return new EventoTO.EventoTOBuilder()
                .eventId(eventoEntity.getEventoId())
                .nome(eventoEntity.getNome())
                .dataInicial(eventoEntity.getDataInicial())
                .dataFinal(eventoEntity.getDataFinal())
                .ativo(eventoEntity.isAtivo())
                .instituicao(InstituicaoParser.toTOSemEventos(eventoEntity.getInstituicao()))
                .build();
    }

    public static EventoEntity toEntity(EventoTO eventoTO) {
        return EventoEntity.builder()
                .eventId(eventoTO.getEventoId())
                .nome(eventoTO.getNome())
                .dataInicial(eventoTO.getDataInicial())
                .dataFinal(eventoTO.getDataFinal())
                .ativo(eventoTO.isAtivo())
                .instituicao(InstituicaoParser.toEntity(eventoTO.getInstituicao()))
                .build();
    }

    public static EventoEntity toEntity(EventoTO eventoTO, Integer id) {
        return EventoEntity.builder()
                .eventId(id)
                .nome(eventoTO.getNome())
                .dataInicial(eventoTO.getDataInicial())
                .dataFinal(eventoTO.getDataFinal())
                .ativo(eventoTO.isAtivo())
                .instituicao(InstituicaoParser.toEntity(eventoTO.getInstituicao()))
                .build();
    }

    public static EventoTO toTOSemInstituicao(EventoEntity evento) {
        return EventoTO.builder()
                .eventId(evento.getEventoId())
                .nome(evento.getNome())
                .dataInicial(evento.getDataInicial())
                .dataFinal(evento.getDataFinal())
                .ativo(evento.isAtivo())
                .build();
    }
}
