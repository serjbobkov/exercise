package ru.bsa.tinkoff.services.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Application that = (Application) o;

        if (applicationId != that.applicationId) {
            return false;
        }
        if (contactId != that.contactId) {
            return false;
        }
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) {
            return false;
        }
        return dtCreated != null ? dtCreated.equals(that.dtCreated) : that.dtCreated == null;
    }

    @Override
    public int hashCode() {
        int result = productName != null ? productName.hashCode() : 0;
        result = 31 * result + applicationId;
        result = 31 * result + (dtCreated != null ? dtCreated.hashCode() : 0);
        result = 31 * result + contactId;
        return result;
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
