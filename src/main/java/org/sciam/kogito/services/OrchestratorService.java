package org.sciam.kogito.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.sciam.kogito.model.Container;
import org.sciam.kogito.model.Item;
import org.sciam.kogito.model.Receptacle;

@ApplicationScoped
public class OrchestratorService {

    @Inject
    CommonService service;

    @Transactional
    public Item addNewItem(Item item) {
        Receptacle r = Receptacle.find("destination = ?1 and status = 'OPENED' ", item.destination).firstResult();
        if (r == null) {
            r = service.buildReceptacle(item.getDestination());
        }
        Item save = Item.builder().code(item.code).receptacleId(r.id).destination(item.destination).weight(item.weight)
                .build();
        save.persist();
        r.weight += save.weight;
        return save;
    }

    public boolean existOpenedContainer(Integer receptacleId) {
        Receptacle r = Receptacle.findById(Long.valueOf(receptacleId));
        var result = false;
        if (r != null) {
            var c = Container.find("destination = ?1 and status = 'OPENED' ", r.destination).firstResult();
            if (c != null) {
                result = true;
            }
        }
        return result;
    }

    @Transactional
    public Receptacle createContainer(Integer receptacleId) {
        Receptacle r = Receptacle.findById(Long.valueOf(receptacleId));
        if (r != null) {
            Container c = service.buildContainer(r.destination);
            c.persist();
            r.containerId = c.id;
        }
        return r;

    }

    @Transactional
    public Receptacle closeReceptacle(Receptacle r) {
        Receptacle r1 = Receptacle.findById(r.id);
        r1.status = "CLOSED";
        r1.persist();
        return r1;
    }

    @Transactional
    public Container addReceptacleToContainer(Integer receptacleId) {
        Receptacle r = Receptacle.findById(Long.valueOf(receptacleId));
        Container c = Container.find("destination = ?1 and status = 'OPENED' ", r.destination).firstResult();
        r.containerId = c.id;
        r.status = "CLOSED";
        r.persist();
        return c;
    }

}
