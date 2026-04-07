/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.enterprise;
import model.directory.OrganizationDirectory;
import model.directory.UserAccountDirectory;

/**
 *
 * @author sashajohnson
 */
public abstract class Enterprise {
    private String name;
    private String type;
    private OrganizationDirectory organizationDirectory;
    private UserAccountDirectory userAccountDirectory;

    public Enterprise(String name, String type) {
        this.name = name;
        this.type = type;
        this.organizationDirectory = new OrganizationDirectory();
        this.userAccountDirectory = new UserAccountDirectory();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    @Override
    public String toString() {
        return name;
    }
}

