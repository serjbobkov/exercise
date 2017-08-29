package ru.bsa.tinkoff.services.contact;

import org.assertj.db.api.Assertions;
import org.assertj.db.type.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.bsa.tinkoff.services.contact.dao.ContactDao;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.inject.Inject;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.bsa.tinkoff.TestUtil.currentTransactionDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactDaoIT {
    @Inject
    private ContactDao contactDao;

    @Inject
    private DataSource dataSource;


    @Test
    @Transactional
    public void insert() {


        Contact contact = contactDao.insert(10);

        assertThat(contact)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(contact.getContactId())
                            .isEqualTo(10);
                });


        Assertions.assertThat(new Request(currentTransactionDataSource(dataSource), "SELECT * FROM contacts WHERE contact_id=?", 10))
                .hasNumberOfRows(1)
                .column("contact_id").value().isEqualTo(contact.getContactId());
    }

    @Test
    @Transactional
    public void findById() {
        Contact contact = contactDao.insert(10);

        assertThat(contactDao.findContact(10))
                .isPresent()
                .hasValueSatisfying(sc -> {
                    assertThat(sc).isEqualToComparingFieldByFieldRecursively(contact);
                });
    }




}