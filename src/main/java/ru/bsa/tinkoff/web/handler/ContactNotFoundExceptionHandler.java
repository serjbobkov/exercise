package ru.bsa.tinkoff.web.handler;

import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.web.dto.ErrorDetail;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class ContactNotFoundExceptionHandler implements ExceptionMapper<ContactNotFoundException> {

    @Override
    public Response toResponse(final ContactNotFoundException exception) {
        Response.Status status;

        status = Response.Status.NOT_FOUND;
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(404);
        errorDetail.setErrorMessage(exception.getMessage());

        return Response.status(status).entity(errorDetail).build();
    }
}
