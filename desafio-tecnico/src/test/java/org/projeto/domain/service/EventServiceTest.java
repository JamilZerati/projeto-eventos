package org.projeto.domain.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.projeto.api.dto.EventoTO;
import org.projeto.api.dto.InstituicaoTO;
import org.projeto.api.exception.EventNotFoundException;
import org.projeto.api.filter.EventoFilter;
import org.projeto.domain.service.util.EventoParser;
import org.projeto.infra.entity.evento.EventoEntity;
import org.projeto.infra.entity.instituicao.InstituicaoEntity;
import org.projeto.infra.repository.impl.EventRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class EventServiceTest {

    @Inject
    EventService eventService;

    @InjectMock
    EventRepositoryImpl eventRepository;

    @InjectMock
    InstituicaoService instituicaoService;

    @Test
    void testCreateEvent() {
        InstituicaoEntity instituicao = criarInstituicaoEntity(1, "Instituicao 1");
        EventoEntity eventoEntityCriar = criarEventoEntity(1, "Evento 1", LocalDate.parse("2024-11-25"), LocalDate.parse("2024-11-26"), false,
                instituicao);

        when(instituicaoService.findInstituicaoEntityById(1)).thenReturn(instituicao);

        // Act
        eventService.createEvent(eventoEntityCriar);

        // Assert
        ArgumentCaptor<EventoEntity> argument = ArgumentCaptor.forClass(EventoEntity.class);
        verify(eventRepository).salvarEvento(argument.capture());
        assertEquals(instituicao, argument.getValue().getInstituicao());
        assertEquals(1, argument.getValue().getInstituicao().getInstituicaoId());
        assertEquals("Evento 1", argument.getValue().getNome());


    }

    private InstituicaoEntity criarInstituicaoEntity(int i, String s) {
        return InstituicaoEntity.builder()
                .id(i)
                .nome(s)
                .build();
    }

    private EventoEntity criarEventoEntity(Integer id, String nome, LocalDate dataInicial, LocalDate dataFinal, boolean ativo,
                                           InstituicaoEntity instituicao) {
        return EventoEntity.builder()
                .eventId(id)
                .nome(nome)
                .dataInicial(dataInicial)
                .dataFinal(dataFinal)
                .ativo(ativo)
                .instituicao(instituicao)
                .build();
    }

    @Test
    void testUpdateEvent() {
        // Arrange
        EventoTO eventTOAtualizado = criarEventoTO(1, "Evento 1 Atualizado", LocalDate.parse("2024-11-26"), LocalDate.parse("2024-11-30"), true,
                criarInstituicaoTO(1, "Instituicao 1"));

        EventoEntity eventoEntityAntigo = criarEventoEntity(1, "Evento 1", LocalDate.parse("2024-11-25"), LocalDate.parse("2024-11-26"), false,
                criarInstituicaoEntity(1, "Instituicao 1"));


        when(eventRepository.buscarEventoPorId(1)).thenReturn(Optional.of(eventoEntityAntigo));

        // Act
        eventService.updateEvent(EventoParser.toEntity(eventTOAtualizado));

        // Assert
        ArgumentCaptor<EventoEntity> argument = ArgumentCaptor.forClass(EventoEntity.class);
        verify(eventRepository).atualizarEvento(argument.capture());
        assertEquals("Evento 1 Atualizado", argument.getValue().getNome());
        assertEquals(LocalDate.parse("2024-11-26"), argument.getValue().getDataInicial());
        assertEquals(LocalDate.parse("2024-11-30"), argument.getValue().getDataFinal());
        assertEquals(1, argument.getValue().getInstituicao().getInstituicaoId());
        assertTrue(argument.getValue().isAtivo());

    }

    @Test
    void testGetEvents() {
        // Arrange
        EventoFilter eventoFilter = new EventoFilter();
        eventoFilter.setNome("Evento 1");

        EventoEntity eventoEntity = criarEventoEntity(1, "Evento 1", LocalDate.parse("2024-11-25"), LocalDate.parse("2024-11-26"), false,
                criarInstituicaoEntity(1, "Instituicao 1"));
        when(eventRepository.buscarEventos(eventoFilter)).thenReturn(Optional.of(List.of(eventoEntity)));

        // Act
        List<EventoTO> result = eventService.getEvents(eventoFilter);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testGetEventById() {
        // Arrange
        EventoEntity eventoEntity = criarEventoEntity(1, "Evento 1", LocalDate.parse("2024-11-25"), LocalDate.parse("2024-11-26"), false,
                criarInstituicaoEntity(1, "Instituicao 1"));
        when(eventRepository.buscarEventoPorId(1)).thenReturn(Optional.of(eventoEntity));

        // Act
        EventoTO result = eventService.getEventById(1);

        // Assert
        assertEquals(eventoEntity.getEventoId(), result.getEventoId());
        assertEquals(eventoEntity.getNome(), result.getNome());
        assertEquals(eventoEntity.getDataInicial(), result.getDataInicial());
        assertEquals(eventoEntity.getDataFinal(), result.getDataFinal());
        assertEquals(eventoEntity.isAtivo(), result.isAtivo());
        assertEquals(eventoEntity.getInstituicao().getInstituicaoId(), result.getInstituicao().getId());

    }

    @Test
    void testGetEventByIdThrowsException() {
        // Arrange
        when(eventRepository.buscarEventoPorId(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EventNotFoundException.class, () -> eventService.getEventById(999));
    }

    @Test
    void testHandleEventStatus() {
        // Act
        eventService.handleEventStatus();

        // Assert
        verify(eventRepository).ativarEventos();
        verify(eventRepository).desativarEventos();
    }

    private EventoTO criarEventoTO(Integer id, String nome, LocalDate dataInicial, LocalDate dataFinal, boolean ativo,
                                   InstituicaoTO instituicao) {
        return EventoTO.builder()
                .eventId(id)
                .nome(nome)
                .dataInicial(dataInicial)
                .dataFinal(dataFinal)
                .ativo(ativo)
                .instituicao(instituicao)
                .build();
    }

    private InstituicaoTO criarInstituicaoTO(int i, String s) {
        return InstituicaoTO.builder()
                .id(i)
                .nome(s)
                .build();
    }
}
