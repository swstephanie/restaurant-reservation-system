package com.sw.restaurant.pojo;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

@Table(name="reservation")
@Entity
public class Reservation {
    @Id
    @Column(name="id")
    private final String id;
    @Column(name="table_id")
    private String table_id;
//    @Column(name="customer_id")
//    private String customer_id;
    @Column(name="party_size")
    private int party_size;
    @Column(name="timeslot")
    private String timeslot;
    @Column(name="notes")
    private String notes;

    @OneToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    public Reservation() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSS");
        String dateStr = sdf.format(calendar.getTime());
        StringBuilder sb = new StringBuilder(dateStr);
        sb.append(String.valueOf((int) 10* Math.random()));
        this.id = sb.toString();
    }


    public String getId() {
        return id;
    }

//    public String getCustomer_id() {
//        return customer_id;
//    }
//
//    public void setCustomer_id(String customer_id) {
//        this.customer_id = customer_id;
//    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public int getParty_size() {
        return party_size;
    }

    public void setParty_size(int party_size) {
        this.party_size = party_size;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
