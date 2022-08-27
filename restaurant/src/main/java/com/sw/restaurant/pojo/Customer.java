package com.sw.restaurant.pojo;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Table(name="customer")
@Entity
public class Customer implements Comparable<Customer>{
    @Id
    @Column(name="id")
    private final String id;
    @Column(name="customer_name")
    private String customerName;
    @Column(name="email")
    private String email;
    @Column(name="dob")
    private Date dob;
    @Column(name="gender")
    private String gender;
    @Column(name="points")
    private int points;
    @Column(name="cellphone")
    private String cellphone;



    public Customer() {
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        Long diff =  Long.parseLong(this.getId()) - Long.parseLong(o.getId());
        if (diff<=0) return -1;
        return 1;
    }
}
