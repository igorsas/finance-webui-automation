package com.igor.model.daoModel;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "adding_type")
public class ReplenishmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;

    public ReplenishmentType() {
    }

    public ReplenishmentType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReplenishmentType.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type='" + type + "'")
                .toString();
    }
}
