package ru.bsa.tinkoff.services.application;

import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.bsa.tinkoff.services.application.dao.ApplicationDao;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.dao.ContactDao;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.inject.Inject;
import javax.sql.DataSource;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static ru.bsa.tinkoff.TestUtil.currentTransactionDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationDaoIT {
    @Inject
    private ApplicationDao applicationDao;

    @Inject
    private ContactDao contactDao;

    @Inject
    private DataSource dataSource;

    private Contact contact;

    @Before
    public void setup() {
        if (contact == null) {
            contact = contactDao.insert(1);
        }
    }

    @Test
    @Transactional
    public void insert() {

        final Date d = new Date();
        final String productName = "name";

        Application application = applicationDao.insert(productName, d, contact);

        assertThat(application)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(application.getProductName())
                            .isEqualTo(productName);
                    assertThat(u.getDtCreated().getTime())
                            .isEqualTo(d.getTime());
                    assertThat(u.getContactId())
                            .isEqualTo(contact.getContactId());
                });


        assertThat(new Request(currentTransactionDataSource(dataSource), "SELECT * FROM applications WHERE application_id=?", application.getApplicationId()))
                .hasNumberOfRows(1)
                .column("product_name").value().isEqualTo(productName)
                .column("contact").value().isEqualTo(contact.getContactId())
                .column("dt_created").value().isEqualTo(new DateTimeValue(new Timestamp(d.getTime())));
    }

    @Test
    @Transactional
    public void findById() {
        Application app = applicationDao.insert("name", new Date(), contact);

        assertThat(applicationDao.find(app.getApplicationId()))
                .isPresent()
                .hasValueSatisfying(sc -> {
                    assertThat(sc).isEqualToComparingFieldByFieldRecursively(app);
                });
    }

    @Test
    @Transactional
    public void findLast() {


        for (int i = 0; i < 10; i++) {
            applicationDao.insert("name" + i, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), contact);
        }

        Application application = applicationDao.insert("test", new Date(), contact);

        for (int i = 0; i < 10; i++) {
            applicationDao.insert("name" + i, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), contact);
        }

        assertThat(applicationDao.findLast(contact))
                .isPresent()
                .hasValueSatisfying(sc -> {
                    assertThat(sc).isEqualToComparingFieldByFieldRecursively(application);
                });
    }


}