package com.sw.restaurant.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;


@Table(name="customer")
@Entity
public class Customer {
    @Id
    @Column(name="id")
    private final String id;
    @Column(name="email")
    private String email;
    @Column(name="dob")
    private String dob;
    @Column(name="gender")
    private String gender;
    @Column(name="points")
    private int points;

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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
}
