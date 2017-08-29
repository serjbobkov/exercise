package ru.bsa.tinkoff;


import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.bsa.tinkoff.services.application.ApplicationService;
import ru.bsa.tinkoff.services.contact.ContactService;
import ru.bsa.tinkoff.web.ExerciseRestServiceImpl;

import javax.inject.Inject;
import java.util.Collections;

@SpringBootApplication
public class ExerciseApplication {
    private final Bus bus;

    private final ApplicationService applicationService;

    private final ContactService contactService;


    @Inject
    public ExerciseApplication(final Bus bus, final ApplicationService applicationService, final ContactService contactService) {
        this.bus = bus;
        this.applicationService = applicationService;
        this.contactService = contactService;
    }

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Collections.singletonList(new ExerciseRestServiceImpl(applicationService, contactService)));
        endpoint.setAddress("/");
        endpoint.setFeatures(Collections.singletonList(createSwaggerFeature()));
        return endpoint.create();
    }

    private Swagger2Feature createSwaggerFeature() {
        Swagger2Feature swagger2Feature = new Swagger2Feature();
        swagger2Feature.setPrettyPrint(true);
        swagger2Feature.setTitle("Tinkoff test project");
        swagger2Feature.setContact("serj.bobkov@gmail.com");
        swagger2Feature.setDescription("Test project for Tinkoff");
        swagger2Feature.setVersion("1.0.0");
        swagger2Feature.setSupportSwaggerUi(true);
        return swagger2Feature;
    }


    public static void main(final String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

}
