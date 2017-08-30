package ru.bsa.tinkoff.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(
            value = "Get last application for contact",
            response = ApplicationResponse.class
    )

    @ApiResponses({
            @ApiResponse(code = 404, message = "Application not found for contact with target id or contact with target id not found")
    })
    ApplicationResponse getLastApplication(@ApiParam(value = "Target contact id", required = true) @PathParam("contactId") int contactId) throws ApplicationNotFoundException, ContactNotFoundException;


}
