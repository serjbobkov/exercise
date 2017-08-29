package ru.bsa.tinkoff.services.application;

import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.annotation.Nonnull;
import java.util.Date;

public interface ApplicationService {

    @Nonnull
    Application addApplication(@Nonnull String productName,
                               @Nonnull Date dtCreated,
                               @Nonnull Contact contact);
    @Nonnull
    Application findApplication(int applicationId) throws ApplicationNotFoundException;

    @Nonnull
    Application findLastApplication(final Contact contact) throws ApplicationNotFoundException;


}
