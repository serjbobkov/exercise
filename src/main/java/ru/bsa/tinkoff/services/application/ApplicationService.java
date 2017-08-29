package ru.bsa.tinkoff.services.application;

import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.annotation.Nonnull;
import java.util.Date;

public interface ApplicationService {


    Application addApplication(@Nonnull final String productName,
                               @Nonnull final Date dtCreated,
                               @Nonnull final Contact contact);

    Application findApplication(final int applicationId) throws ApplicationNotFoundException;

    Application findLastApplication(@Nonnull final Contact contact) throws ApplicationNotFoundException;


}
