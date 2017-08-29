package ru.bsa.tinkoff.web;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;
import ru.bsa.tinkoff.services.application.ApplicationService;
import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.ContactService;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.inject.Inject;

@Api("SayHello")
@Service
public class ExerciseRestServiceImpl implements ExerciseRestService {

    private final ApplicationService applicationService;

    private final ContactService contactService;

    @Inject
    public ExerciseRestServiceImpl(final ApplicationService applicationService, final ContactService contactService) {
        this.applicationService = applicationService;
        this.contactService = contactService;
    }


    public Application getLastApplication(final int contactId) throws ApplicationNotFoundException, ContactNotFoundException {
        Contact contact = contactService.findContact(contactId);
        return applicationService.findLastApplication(contact);
    }
}
