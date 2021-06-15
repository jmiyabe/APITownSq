package com.betownsq.api.model;

import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group implements Cloneable {

    private String userType;
    private long idCondominium;
    private List<Functionality> functionalityList;

    public Group(){}

    public Group(String userType, long idCondominium, List<Functionality> functionalityList) {
        this.userType = userType;
        this.idCondominium = idCondominium;
        this.functionalityList = functionalityList;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public long getIdCondominium() {
        return idCondominium;
    }

    public void setIdCondominium(long idCondominium) {
        this.idCondominium = idCondominium;
    }

    public List<Functionality> getFunctionalityList() {
        return functionalityList;
    }

    public void setFunctionalityList(List<Functionality> functionalityList) {
        this.functionalityList = functionalityList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int listSize = this.functionalityList.size();
        for (int i=0 ; i<listSize; i++){
            builder.append(functionalityList.get(i));
            if(i < listSize-1){
                builder.append(",");
            }
        }
        return "[" + builder.toString() + "]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Group clonedGroup = (Group) super.clone();
        Iterator<Functionality> iterator = this.functionalityList.iterator();
        List<Functionality> clonedList = new ArrayList<>();
        while(iterator.hasNext()){
            clonedList.add((Functionality) iterator.next().clone());
        }
        clonedGroup.setFunctionalityList(clonedList);
        return clonedGroup;
    }
}
