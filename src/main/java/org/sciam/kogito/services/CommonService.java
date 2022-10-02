package org.sciam.kogito.services;

import java.time.LocalDateTime;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.sciam.kogito.model.Container;
import org.sciam.kogito.model.Receptacle;

@ApplicationScoped
public class CommonService {

    public Receptacle buildReceptacle(String destination) {
        Receptacle r = new Receptacle();
        r.code = new Random().nextLong(10000) + 1;
        r.creationDate = LocalDateTime.now();
        r.maxWeight = 23.0;
        r.weight = 0;
        r.status = "OPENED";
        r.destination = destination;
        r.persist();
        return r;
    }

    public Container buildContainer(String destination) {
        Container container = new Container();
        container.setDate(LocalDateTime.now());
        container.setNumber(new Random().nextLong(10000) + 1);
        container.setDestination(destination);
        container.setStatus("OPENED");
        container.setSize(3);
        return container;

    }

}
