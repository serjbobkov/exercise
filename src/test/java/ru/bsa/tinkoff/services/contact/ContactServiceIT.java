package ru.bsa.tinkoff.services.contact;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.inject.Inject;


import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceIT {


    @Inject
    private ContactService contactService;


    @Test
    @Transactional
    public void addContact() throws Exception {

        final Contact contact = contactService.addContact(100);
        assertThat(contact.getContactId())
                .isEqualTo(100);
    }

    @Test
    @Transactional
    public void findContact() throws Exception {
        final Contact contact = contactService.addContact(100);
        assertThat(contactService.findContact(100)).isEqualToComparingFieldByField(contact);

    }


}