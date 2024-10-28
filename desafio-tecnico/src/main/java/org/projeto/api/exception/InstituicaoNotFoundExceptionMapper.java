package org.projeto.api.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InstituicaoNotFoundExceptionMapper implements ExceptionMapper<InstituicaoNotFoundException> {

    @Override
    public Response toResponse(InstituicaoNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }
}
