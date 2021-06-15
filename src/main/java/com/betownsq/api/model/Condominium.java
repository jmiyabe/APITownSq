package com.betownsq.api.model;

public class Condominium {
    private int idCondominium;
    private String userType;

    public Condominium(){}

    public Condominium(int idCondominium, String userType) {
        this.idCondominium = idCondominium;
        this.userType = userType;
    }

    public int getIdCondominium() {
        return idCondominium;
    }

    public void setIdCondominium(int idCondominium) {
        this.idCondominium = idCondominium;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
