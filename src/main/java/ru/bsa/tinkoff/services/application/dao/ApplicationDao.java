package ru.bsa.tinkoff.services.application.dao;


import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bsa.tinkoff.jooq.tables.records.ApplicationsRecord;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static ru.bsa.tinkoff.jooq.tables.Applications.APPLICATIONS;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ApplicationDao {

    private final DefaultDSLContext jooq;

    @Inject
    public ApplicationDao(final DefaultDSLContext jooq) {
        this.jooq = jooq;
    }

    public Application insert(final String productName, final Date dtCreated, final Contact contact) {

        final ApplicationsRecord record = jooq.insertInto(APPLICATIONS)
                .set(APPLICATIONS.PRODUCT_NAME, productName)
                .set(APPLICATIONS.DT_CREATED, new Timestamp(dtCreated.getTime()))
                .set(APPLICATIONS.CONTACT, contact.getContactId())
                .returning(APPLICATIONS.APPLICATION_ID,
                        APPLICATIONS.PRODUCT_NAME,
                        APPLICATIONS.CONTACT,
                        APPLICATIONS.DT_CREATED)
                .fetchOne();

        return new Application(record.getValue(APPLICATIONS.PRODUCT_NAME),
                record.getValue(APPLICATIONS.APPLICATION_ID),
                record.getValue(APPLICATIONS.DT_CREATED),
                record.getValue(APPLICATIONS.CONTACT));
    }


    public Optional<Application> find(final int applicationId) {
        return jooq.selectFrom(APPLICATIONS)
                .where(APPLICATIONS.APPLICATION_ID.eq(applicationId))
                .fetchOptional(r -> new Application(r.get(APPLICATIONS.PRODUCT_NAME),
                        r.get(APPLICATIONS.APPLICATION_ID), r.get(APPLICATIONS.DT_CREATED), r.get(APPLICATIONS.CONTACT)));
    }


    public Optional<Application> findLast(final Contact contact) {

        //select * from app where id=? and ts=(select max(ts) from app where id=?)

        return jooq.selectFrom(APPLICATIONS)
                .where(APPLICATIONS.CONTACT.eq(contact.getContactId()))
                .and(APPLICATIONS.DT_CREATED.eq(
                        jooq.select(APPLICATIONS.DT_CREATED.max())
                                .from(APPLICATIONS).where(APPLICATIONS.CONTACT.eq(contact.getContactId()))
                        )
                ).orderBy(APPLICATIONS.APPLICATION_ID.desc()).limit(1)
                .fetchOptional(r -> new Application(r.get(APPLICATIONS.PRODUCT_NAME),
                        r.get(APPLICATIONS.APPLICATION_ID), r.get(APPLICATIONS.DT_CREATED), r.get(APPLICATIONS.CONTACT)));
    }

}
