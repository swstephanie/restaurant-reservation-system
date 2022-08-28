package com.sw.restaurant.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="diningtable")
@Entity
public class DiningTable {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="capacity")
    private int capacity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "DiningTable{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
