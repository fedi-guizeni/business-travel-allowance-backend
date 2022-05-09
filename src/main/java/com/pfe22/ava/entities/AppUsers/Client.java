package com.pfe22.ava.entities.AppUsers;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Client implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private Long  Id ;
    private String userId;
    private String firstName ;
    private String lastName ;
    private String address ;
    private Integer tel;
    private  String email;
    private Integer fax;
    private Integer account;


    public Client(Long id, String userId, String firstName, String lastName, String address, Integer tel, String email, Integer fax, Integer account) {
        Id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.fax = fax;
       this.account=account;

    }

    public Client() {

    }

    public void setId(Long id) {
        Id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFax(Integer fax) {
        this.fax = fax;
    }



    public Long getId() {
        return Id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public Integer getFax() {
        return fax;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getAccount() {
        return account;
    }
}


