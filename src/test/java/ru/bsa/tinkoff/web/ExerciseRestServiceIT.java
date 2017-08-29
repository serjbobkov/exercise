package ru.bsa.tinkoff.web;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import ru.bsa.tinkoff.services.application.ApplicationService;
import ru.bsa.tinkoff.services.application.exception.ApplicationNotFoundException;
import ru.bsa.tinkoff.services.application.model.Application;
import ru.bsa.tinkoff.services.contact.ContactService;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.services.contact.model.Contact;
import ru.bsa.tinkoff.web.dto.ApplicationResponse;
import ru.bsa.tinkoff.web.dto.ErrorDetail;

import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ExerciseRestServiceIT {

    @MockBean
    private ContactService contactService;

    @MockBean
    private ApplicationService applicationService;

    @LocalServerPort
    private int port;

    @Test
    public void getLastJson() throws Exception {
        when(contactService.findContact(10))
                .thenReturn(new Contact(10));


        Date d = new Date();
        when(applicationService.findLastApplication(new Contact(10)))
                .thenReturn(new Application("test", 10, d, 10));

        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        jsonProvider.addUntouchable(Response.class);

        WebClient client = WebClient.create("http://localhost:"+port+"/exercise/", Collections.singletonList(jsonProvider));
        client.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        client.type(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        client.path("/api/application/last/10");



        Response resp = client.get();

        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(resp.getStatus());
        ApplicationResponse app = resp.readEntity(ApplicationResponse.class);

        assertThat(app).isNotNull();
        assertThat(app.getApplicationId()).isEqualTo(10);
        assertThat(app.getContactId()).isEqualTo(10);
        assertThat(app.getProductName()).isEqualTo("test");
        assertThat(app.getDtCreated()).isEqualTo(d);
    }


    @Test
    public void getLastXML() throws Exception {
        when(contactService.findContact(10))
                .thenReturn(new Contact(10));


        Date d = new Date();
        when(applicationService.findLastApplication(new Contact(10)))
                .thenReturn(new Application("test", 10, d, 10));

        JAXBElementProvider provider = new JAXBElementProvider();

        WebClient client = WebClient.create("http://localhost:"+port+"/exercise/", Collections.singletonList(provider));
        client.accept(MediaType.APPLICATION_XML);
        client.type(MediaType.APPLICATION_XML);
        client.path("/api/application/last/10");

        Response resp = client.get();

        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(resp.getStatus());

        ApplicationResponse app = resp.readEntity(ApplicationResponse.class);

        assertThat(app).isNotNull();
        assertThat(app.getApplicationId()).isEqualTo(10);
        assertThat(app.getContactId()).isEqualTo(10);
        assertThat(app.getProductName()).isEqualTo("test");
        assertThat(app.getDtCreated()).isEqualTo(d);
    }


    @Test
    public void getLastFailureJSON() throws Exception {
        when(contactService.findContact(10))
                .thenThrow(new ContactNotFoundException("Contact with id = 10 not found"));



        JacksonJsonProvider provider = new JacksonJsonProvider();
        provider.addUntouchable(Response.class);

        WebClient client = WebClient.create("http://localhost:"+port+"/exercise/", Collections.singletonList(provider));
        client.path("/api/application/last/10");
        client.accept(MediaType.APPLICATION_JSON);

        Response resp = client.get();
        assertThat(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).isEqualTo(resp.getStatus());

        ErrorDetail errorDetail = resp.readEntity(ErrorDetail.class);
        assertThat(errorDetail.getErrorMessage()).isEqualTo("Contact with id = 10 not found");


    }


    @Test
    public void getLastFailureXML() throws Exception {
        when(contactService.findContact(10))
                .thenThrow(new ContactNotFoundException("Contact with id = 10 not found"));

        JAXBElementProvider provider = new JAXBElementProvider();

        WebClient client = WebClient.create("http://localhost:"+port+"/exercise/", Collections.singletonList(provider));
        client.path("/api/application/last/10");
        client.accept(MediaType.APPLICATION_XML);

        Response resp = client.get();
        assertThat(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).isEqualTo(resp.getStatus());

        ErrorDetail errorDetail = resp.readEntity(ErrorDetail.class);
        assertThat(errorDetail.getErrorMessage()).isEqualTo("Contact with id = 10 not found");

    }


}