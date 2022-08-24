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
    @Column(name="partysize")
    private int partySize;
    @Column(name="timeslot")
    private String timeslot;
    @Column(name="cellphone")
    private String cellphone;
    @Column(name="notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customer_id",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    public Reservation() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSS");
        String dateStr = sdf.format(calendar.getTime());
        StringBuilder sb = new StringBuilder(dateStr);
        sb.append(String.valueOf((int) 10* Math.random()));
        this.id = sb.toString();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
