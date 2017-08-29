package ru.bsa.tinkoff.services.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.ContactService;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.inject.Inject;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceIT {


    @Inject
    private ContactService contactService;

    @Inject
    private ApplicationService applicationService;

    @Test
    @Transactional
    public void addApplication() throws Exception {

        final Contact contact = contactService.addContact(100);

        final Date d = new Date();

        final Application application = applicationService.addApplication("test", d, contact);

        assertThat(application.getApplicationId())
                .isPositive();
        assertThat(application.getProductName())
                .isEqualTo("test");
        assertThat(application.getDtCreated().getTime())
                .isEqualTo(d.getTime());
        assertThat(application.getContactId())
                .isEqualTo(100);


    }

    @Test
    @Transactional
    public void findApplication() throws Exception {
        final Contact contact = contactService.addContact(100);

        final Date d = new Date();

        final Application application = applicationService.addApplication("test", d, contact);

        assertThat(applicationService.findApplication(application.getApplicationId())).isEqualToComparingFieldByField(application);

    }

    @Test
    @Transactional
    public void findLastApplication() throws Exception {

        final Contact contact = contactService.addContact(100);

        for (int i = 0; i < 10; i++) {
            applicationService.addApplication("name" + i, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), contact);
        }

        Application application = applicationService.addApplication("test", new Date(), contact);

        for (int i = 0; i < 10; i++) {
            applicationService.addApplication("name" + i, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), contact);
        }

        assertThat(applicationService.findLastApplication(contact)).isEqualToComparingFieldByField(application);


    }
}