package com.pfe22.ava.entities.AppUsers;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false , updatable = false)
    private Long  Id ;
    private String userId;
    private String firstName ;
    private String lastName ;
    private String username ;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password ;
    private String email ;
    private Date joinDate;
    private Date lastLoginDate;
    private  Date lastLoginDateDisplay;
    private String role;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;


    //constructor ,getters and setters
    public  User (){}

    public User(Long id,
                String userId,
                String firstName,
                String lastName,
                String username,
                String password,
                String email,
                Date joinDate,
                Date lastLoginDate,
                Date lastLoginDateDisplay,
                String role,
                String[] authorities,
                boolean isActive,
                boolean isNotLocked)
    {
        this.Id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.joinDate = joinDate;
        this.role = role;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDateDisplay=lastLoginDateDisplay;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public Date getLastLoginDateDisplay()  {
        return lastLoginDateDisplay;
    }

    public String getRole() {
        return role;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }


}
