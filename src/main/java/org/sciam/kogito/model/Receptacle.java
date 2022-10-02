package org.sciam.kogito.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

@Entity
@Data
public class Receptacle extends PanacheEntity {

    public long code;
    public LocalDateTime creationDate;
    public double maxWeight;
    public double weight;
    public String destination;
    public String status;
    public long containerId;

}
