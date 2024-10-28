package org.projeto.api.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.projeto.api.dto.EventoTO;
import org.projeto.domain.service.EventService;
import org.projeto.domain.service.InstituicaoService;
import org.projeto.domain.service.util.EventoParser;

@Path("/instituicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstituicaoController {

    private final InstituicaoService instituicaoService;

    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @GET
    public Response getInstituicoes(@QueryParam("page") @DefaultValue("0") Integer page,
                                    @QueryParam("size") @DefaultValue("10") Integer size) {
        return Response.ok(instituicaoService.getInstituicoes(page, size)).build();
    }

    @GET
    @Path("/{id}")
    public Response getInstituicoesById(Integer id) {
        return Response.ok(instituicaoService.getById(id)).build();
    }
}
