package ru.bsa.tinkoff.services.application.model;


import java.io.Serializable;
import java.util.Date;

public class Application implements Serializable {

    private static final long serialVersionUID = -7367969390786288054L;


    private final String productName;
    private final int applicationId;
    private final Date dtCreated;
    private final int contactId;


    public Application(final String productName, final int applicationId, final Date dtCreated, final int contactId) {
        this.productName = productName;
        this.applicationId = applicationId;
        this.dtCreated = dtCreated;
        this.contactId = contactId;
    }


    public String getProductName() {
        return productName;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public int getContactId() {
        return contactId;
    }

    @Override
    public String toString() {
        return "Application{"
                + "productName='" + productName + '\''
                + ", applicationId=" + applicationId
                + ", dtCreated=" + dtCreated
                + ", contactId=" + contactId
                + '}';
    }
}
