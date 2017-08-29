package ru.bsa.tinkoff.web.handler;

import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.web.dto.ErrorDetail;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception exception) {
        Response.Status status;

        status = Response.Status.INTERNAL_SERVER_ERROR;
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(500);
        errorDetail.setErrorMessage(exception.getMessage());

        return Response.status(status).entity(errorDetail).build();
    }
}
