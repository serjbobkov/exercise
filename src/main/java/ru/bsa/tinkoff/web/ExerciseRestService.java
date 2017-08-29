package ru.bsa.tinkoff.web;

import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.web.dto.ApplicationResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/api")
public interface ExerciseRestService {

    @GET
    @Path("/application/last/{contactId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    ApplicationResponse getLastApplication(@PathParam("contactId") int contactId) throws ApplicationNotFoundException, ContactNotFoundException;


}
