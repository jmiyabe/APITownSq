package com.betownsq.api.repository;

import com.betownsq.api.model.Condominium;
import com.betownsq.api.model.Functionality;
import com.betownsq.api.model.Group;
import com.betownsq.api.model.User;
import com.betownsq.api.utils.Constants;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class UserRepository {
    List<User> userList = new ArrayList<>();
    List<Group> groupList = new ArrayList<>();

    private UserRepository(){
        readFromDatabase();
    }

    public void readFromDatabase() {
        try {
            File database = new File("src/database.txt");
            Scanner scanner = new Scanner(database);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arrayProps = line.replaceAll("[\\[\\]()]", "").split(";",-1);
                if(arrayProps.length > 0) {
                    if (arrayProps[0].equals(Constants.USER)) {
                        userList.add(populateAndReturnUser(arrayProps));
                    } else {
                        groupList.add(populateAndReturnGroup(arrayProps));
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User populateAndReturnUser(String[] arrayUserProps) {
        User newUser = new User();
        newUser.setEmail(arrayUserProps[1]);
        newUser.setCondominiumList(populateCondominiumList(arrayUserProps[2]));
        return newUser;
    }

    private List<Condominium> populateCondominiumList(String stringLine) {
        List<Condominium> condominiumList = new ArrayList<>();
        String[] condominiumProps = stringLine.split(",", -1);
        for(int i=0; i<condominiumProps.length; i+=2){
            if(!condominiumProps[i].trim().isEmpty()) {
                Condominium condominium = new Condominium();
                condominium.setUserType(condominiumProps[i]);
                condominium.setIdCondominium(Integer.parseInt(condominiumProps[i+1]));
                condominiumList.add(condominium);
            }
        }
        return condominiumList;
    }

    private Group populateAndReturnGroup(String[] arrayGroupProps) {
        Group newGroup = new Group();
        List<Functionality> functionalityList = new ArrayList<>();

        newGroup.setUserType(arrayGroupProps[1]);
        newGroup.setIdCondominium(Integer.parseInt(arrayGroupProps[2]));
        newGroup.setFunctionalityList(populateFuncionalityList(arrayGroupProps[3]));

        return newGroup;
    }

    private List<Functionality> populateFuncionalityList(String stringLine) {
        List<Functionality> functionalityList = new ArrayList<>();
        String[] functionalityProps = stringLine.split(",", -1);

        for(int i=0; i<functionalityProps.length; i+=2){
            if(!functionalityProps[i].trim().isEmpty()) {
                Functionality functionality = new Functionality();
                functionality.setFunctionality(functionalityProps[i]);
                functionality.setPermission(functionalityProps[i+1]);
                functionalityList.add(functionality);
            }
        }
        return functionalityList;
    }

    public String getUserProps(String email) throws CloneNotSupportedException {
        StringBuilder builder = new StringBuilder();
        List<Group> priorityGroupList = new ArrayList<>();

        if (userExists(email)) {
            User user = getUserByEmail(email);
            List<Condominium> condominiumList = user.getCondominiumList();
            for (Condominium condominium: condominiumList){
                Group newGroup = getCloneGroupByCondominium(condominium);
                if(condominiumExists(priorityGroupList,newGroup)){
                    Group priorityGroup = getGroupBYCondominiumId(priorityGroupList, newGroup);
                    if(priorityGroup != null){
                        priorityGroup.setFunctionalityList(
                                getMostRelevantFunctionalityList(priorityGroup.getFunctionalityList(),newGroup.getFunctionalityList()));
                    }
                } else {
                    priorityGroupList.add(newGroup);
                }
            }
        } else {
            return "User not found";
        }
        for (Group group: priorityGroupList) {
            builder.append(group.getIdCondominium() + ";" + group.toString() + "\n") ;
        }
        System.out.println(builder.toString());
        return builder.toString();
    }

    private boolean userExists(String email){
        return userList.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    private User getUserByEmail(String email) {
        return userList.stream().filter(user1 -> user1.getEmail().equals(email)).findAny().orElse(null);
    }

    private Group getCloneGroupByCondominium(Condominium condominium) throws CloneNotSupportedException {
        return (Group) groupList.stream().filter(group -> group.getUserType().equals(condominium.getUserType()) &&
                group.getIdCondominium() == condominium.getIdCondominium()).findAny().orElse(null).clone();
    }

    private boolean condominiumExists(List<Group> groupList, Group newGroup){
        return groupList.stream().anyMatch(group-> group.getIdCondominium() == newGroup.getIdCondominium());
    }

    private Group getGroupBYCondominiumId(List<Group> groupList, Group newGroup){
        return groupList.stream().filter(group -> group.getIdCondominium() == newGroup.getIdCondominium()).findAny().orElse(null);
    }

    private List<Functionality> getMostRelevantFunctionalityList(List<Functionality> oldFunctionalityList,
                                                                 List<Functionality> newFunctionalityList) {
        List<Functionality> functionalityList = new ArrayList<>();

        for (Functionality functionality : oldFunctionalityList) {
            Functionality newFunctionality = newFunctionalityList.stream().filter(f -> f.getFunctionality().equals(functionality.getFunctionality()))
                    .findAny().orElse(null);
            if (newFunctionality != null) {
                newFunctionalityList.remove(newFunctionality);
                if (functionality.getLevel() < newFunctionality.getLevel()) {
                    functionalityList.add(newFunctionality);
                } else {
                    functionalityList.add(functionality);
                }
            } else {
                functionalityList.add(functionality);
            }
        }

        if(newFunctionalityList.size() > 0){
            for (Functionality functionality: newFunctionalityList){
                functionalityList.add(functionality);
            }
        }

        return functionalityList;
    }
}
