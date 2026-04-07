/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.organization;
import model.WorkQueue;
import model.directory.PersonDirectory;
import model.directory.UserAccountDirectory;

/**
 *
 * @author sashajohnson
 */
public abstract class Organization {
    private String name;
    private WorkQueue workQueue;
    private PersonDirectory personDirectory;
    private UserAccountDirectory userAccountDirectory;
    public Organization(String name) { this.name = name; this.workQueue = new WorkQueue(); this.personDirectory = new PersonDirectory(); this.userAccountDirectory = new UserAccountDirectory();
    }

    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    @Override
    public String toString() {
        return name;
    }
}
