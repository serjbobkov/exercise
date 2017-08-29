package ru.bsa.tinkoff.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement(name = "APPLICATION")
public class ApplicationResponse {


    @JsonProperty("PRODUCT_NAME")
    private  String productName;

    @JsonProperty("APPLICATION_ID")
    private  int applicationId;

    @JsonProperty("DT_CREATED")
    private  Date dtCreated;

    @JsonProperty("CONTACT_ID")
    private  int contactId;


    public ApplicationResponse(final String productName, final int applicationId, final Date dtCreated, final int contactId) {
        this.productName = productName;
        this.applicationId = applicationId;
        this.dtCreated = dtCreated;
        this.contactId = contactId;
    }

    public ApplicationResponse() {
    }


    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public void setApplicationId(final int applicationId) {
        this.applicationId = applicationId;
    }

    public void setDtCreated(final Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public void setContactId(final int contactId) {
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
}
