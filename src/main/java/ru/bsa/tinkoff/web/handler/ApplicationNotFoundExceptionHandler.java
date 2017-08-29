package ru.bsa.tinkoff.web.handler;

import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.web.dto.ErrorDetail;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;



public class ApplicationNotFoundExceptionHandler implements ExceptionMapper<ApplicationNotFoundException> {

    @Override
    public Response toResponse(final ApplicationNotFoundException exception) {
        Response.Status status;

        status = Response.Status.NOT_FOUND;
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(404);
        errorDetail.setErrorMessage(exception.getMessage());

        return Response.status(status).entity(errorDetail).build();
    }
}
