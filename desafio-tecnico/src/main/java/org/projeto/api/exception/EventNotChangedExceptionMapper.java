package org.projeto.api.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EventNotChangedExceptionMapper implements ExceptionMapper<EventNotChangedException> {

    @Override
    public Response toResponse(EventNotChangedException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
