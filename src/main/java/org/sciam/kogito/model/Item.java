package org.sciam.kogito.model;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item extends PanacheEntity {

    public long code;
    public long receptacleId;
    public double weight;
    public String destination;

}
