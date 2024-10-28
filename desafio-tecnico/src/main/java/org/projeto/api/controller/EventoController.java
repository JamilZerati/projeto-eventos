package org.projeto.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.projeto.api.dto.EventoTO;
import org.projeto.api.dto.InstituicaoTO;
import org.projeto.api.filter.EventoFilter;
import org.projeto.domain.service.EventService;
import org.projeto.domain.service.util.EventoParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/eventos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoController {

    private final EventService eventService;

    public EventoController(EventService eventService) {
        this.eventService = eventService;
    }

    @GET
    public Response getEvents(@QueryParam("nome") String nome,
                              @QueryParam("dataInicial") @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") String dataInicial,
                                @QueryParam("dataFinal") @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") String dataFinal,
                                @QueryParam("instituicaoId") Integer instituicaoId,
                                @QueryParam("ativo") Boolean ativo,
                              @QueryParam("page") @DefaultValue("0") @Min(0) Integer page,
                              @QueryParam("size") @DefaultValue("10") @Min(1) Integer size) {
        EventoFilter evento = EventoFilter.builder()
                .nome(nome)
                .dataInicial(dataInicial != null ? LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null)
                .dataFinal(dataFinal != null ? LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null)
                .instituicaoId(instituicaoId)
                .ativo(ativo)
                .page(page)
                .size(size)
                .build();
        return Response.ok(eventService.getEvents(evento)).build();
    }

    @GET
    @Path("/{id}")
    public Response getEventById(Integer id) {
        return Response.ok(eventService.getEventById(id)).build();
    }

    @POST
    public Response createEvent(@Valid EventoTO eventoTO) {
        eventService.createEvent(EventoParser.toEntity(eventoTO));
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEvent(Integer id, @Valid EventoTO eventoTO) {
        eventService.updateEvent(EventoParser.toEntity(eventoTO, id));
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEvent(Integer id) {
        eventService.deleteEvent(id);
        return Response.ok().build();
    }
}
