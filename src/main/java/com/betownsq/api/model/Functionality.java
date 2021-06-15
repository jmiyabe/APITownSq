package com.betownsq.api.model;

import com.betownsq.api.utils.Constants;

public class Functionality implements Cloneable{
    private String functionality;
    private String permission;
    private int level;

    public Functionality(){}

    public Functionality(String functionality, String permission) {
        this.functionality = functionality;
        this.permission = permission;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
        if(this.permission.equals("Escrita")){
            this.level = 3;
        } else if(this.permission.equals("Leitura")){
            this.level = 2;
        } else {
            this.level = 0;
        }
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "(" + this.functionality + "," + this.permission + ")";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
