package org.projeto.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import org.projeto.api.dto.EventoTO;
import org.projeto.api.exception.EventNotFoundException;
import org.projeto.api.filter.EventoFilter;
import org.projeto.domain.service.util.EventoParser;
import org.projeto.infra.entity.evento.EventoEntity;
import org.projeto.infra.entity.instituicao.InstituicaoEntity;
import org.projeto.infra.repository.EventRepository;
import org.projeto.infra.repository.impl.EventRepositoryImpl;

import java.util.List;


@ApplicationScoped
public class EventService {

    private final EventRepository eventRepository;
    private final InstituicaoService instituicaoService;
    private final Logger logger;

    public EventService(EventRepositoryImpl eventRepository, InstituicaoService instituicaoService,
                        Logger logger) {
        this.eventRepository = eventRepository;
        this.instituicaoService = instituicaoService;
        this.logger = logger;
    }

    @Transactional
    public void createEvent(EventoEntity eventoEntity) {
        validarEvento(eventoEntity);
        InstituicaoEntity instituicao = instituicaoService.findInstituicaoEntityById(eventoEntity.getInstituicao().getInstituicaoId());
        eventoEntity.setInstituicao(instituicao);
        eventRepository.salvarEvento(eventoEntity);
    }

    @Transactional
    public void updateEvent(EventoEntity eventoAtualizado) {
        EventoEntity eventoBuscadoAAtualizar = eventRepository.buscarEventoPorId(eventoAtualizado.getEventoId()).orElseThrow(EventNotFoundException::new);
        if (eventoAtualizado.equals(eventoBuscadoAAtualizar)) {
            logger.info("Evento de id %d não foi alterado pois possui os mesmos dados.".formatted(eventoAtualizado.getEventoId()));
            return;
        }

        atualizarCampos(eventoAtualizado, eventoBuscadoAAtualizar);
        eventRepository.atualizarEvento(eventoBuscadoAAtualizar);
    }

    @Transactional
    public List<EventoTO> getEvents(EventoFilter eventoFilter) {
        return eventRepository.buscarEventos(eventoFilter).map(eventos ->
                eventos.stream().map(EventoParser::toTO).toList()).orElseThrow(EventNotFoundException::new);
    }

    @Transactional
    public EventoTO getEventById(Integer id) {
        return eventRepository.buscarEventoPorId(id).map(EventoParser::toTO).orElseThrow(EventNotFoundException::new);
    }

    @Transactional
    public void handleEventStatus() {
        eventRepository.ativarEventos();
        eventRepository.desativarEventos();
    }

    @Transactional
    public void deleteEvent(Integer id) {
        eventRepository.deletarEvento(id);
    }

    private void validarEvento(EventoEntity eventoEntity) {
        if (eventoEntity.getDataInicial().isAfter(eventoEntity.getDataFinal())) {
            throw new IllegalArgumentException("Data inicial não pode ser maior que a data final.");
        }
    }

    private void atualizarCampos(EventoEntity eventoAtualizado, EventoEntity eventoBuscado) {
        eventoBuscado.setNome(eventoAtualizado.getNome());
        eventoBuscado.setDataInicial(eventoAtualizado.getDataInicial());
        eventoBuscado.setDataFinal(eventoAtualizado.getDataFinal());
        eventoBuscado.setInstituicao(eventoAtualizado.getInstituicao());
        eventoBuscado.setAtivo(eventoAtualizado.isAtivo());

    }
}
