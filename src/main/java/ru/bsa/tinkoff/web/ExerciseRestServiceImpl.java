package ru.bsa.tinkoff.web;


import io.swagger.annotations.Api;
import ru.bsa.tinkoff.services.application.ApplicationService;
import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.ContactService;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.services.contact.model.Contact;
import ru.bsa.tinkoff.web.dto.ApplicationResponse;

import javax.inject.Inject;

@Api("test exercise api")
public class ExerciseRestServiceImpl implements ExerciseRestService {

    private final ApplicationService applicationService;

    private final ContactService contactService;

    @Inject
    public ExerciseRestServiceImpl(final ApplicationService applicationService, final ContactService contactService) {
        this.applicationService = applicationService;
        this.contactService = contactService;
    }


    public ApplicationResponse getLastApplication(final int contactId) throws ApplicationNotFoundException, ContactNotFoundException {
        Contact contact = contactService.findContact(contactId);
        Application app =  applicationService.findLastApplication(contact);
        return new ApplicationResponse(app.getProductName(), app.getApplicationId(), app.getDtCreated(), app.getContactId());
    }

}
