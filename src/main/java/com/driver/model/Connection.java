package com.driver.model;

import javax.persistence.*;

@Entity
public class Connection {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String users;

    @ManyToOne
    User user;

    @ManyToOne
    ServiceProvider serviceProvider;


    public Connection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
