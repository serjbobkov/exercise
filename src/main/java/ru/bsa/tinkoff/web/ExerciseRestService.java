package ru.bsa.tinkoff.web;


import io.swagger.annotations.Api;
import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/api")
public interface ExerciseRestService {

    @GET
    @Path("/application/last/{contactId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Application getLastApplication(@PathParam("contactId") final int contactId) throws ApplicationNotFoundException, ContactNotFoundException;

}
