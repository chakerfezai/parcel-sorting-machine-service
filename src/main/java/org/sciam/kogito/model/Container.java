package org.sciam.kogito.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

@Entity
@Data
public class Container extends PanacheEntity {
    public long number;
    public String destination;
    public LocalDateTime date;
    public String status;
    public long size;
}
