package com.betownsq.api.model;

import java.util.List;

public class User {

    private String name;
    private String email;
    private List<Condominium> condominiumList;

    public User(){}

    public User(String name, String email, List<Condominium> condominiumList) {
        this.name = name;
        this.email = email;
        this.condominiumList = condominiumList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Condominium> getCondominiumList() {
        return condominiumList;
    }

    public void setCondominiumList(List<Condominium> condominiumList) {
        this.condominiumList = condominiumList;
    }
}
