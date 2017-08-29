package ru.bsa.tinkoff.services.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bsa.tinkoff.services.application.dao.ApplicationDao;

import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.model.Contact;


import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationDao applicationDao;

    @Inject
    public ApplicationServiceImpl(final ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Override
    @Transactional
    @Nonnull
    public Application addApplication(@Nonnull final String productName, @Nonnull final Date dtCreated, @Nonnull final Contact contact) {

        Objects.requireNonNull(productName, "ProductName should be not null");
        Objects.requireNonNull(dtCreated, "DTCreated should be not null");
        Objects.requireNonNull(contact, "Contact should be not null");


        Application application = applicationDao.insert(productName, dtCreated, contact);

        LOGGER.info("Application added {}", application);

        return application;
    }

    @Override
    @Transactional
    @Nonnull
    public Application findApplication(final int applicationId) throws ApplicationNotFoundException {

        Optional<Application> application = applicationDao.find(applicationId);

        if (application.isPresent()) {
            return application.get();
        } else {
            throw new ApplicationNotFoundException("Application with id = " + applicationId + " not found");
        }

    }

    @Override
    @Transactional
    @Nonnull
    public Application findLastApplication(@Nonnull final Contact contact) throws ApplicationNotFoundException {

        Objects.requireNonNull(contact, "Contact should be not null");

        Optional<Application> application = applicationDao.findLast(contact);

        if (application.isPresent()) {
            return application.get();
        } else {
            throw new ApplicationNotFoundException("Applications for contact = " + contact + " not found");
        }
    }
}
