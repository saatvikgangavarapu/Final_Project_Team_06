/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import model.directory.UserAccountDirectory;
import model.enterprise.Enterprise;

/**
 *
 * @author sashajohnson
 */
public class EcoSystem {

    private static EcoSystem system;
    private String networkName;
    private UserAccountDirectory systemUserAccountDirectory;
    private Network network;

    private EcoSystem() {
        networkName = "Disaster Management Network";
        network = new Network();
        systemUserAccountDirectory = new UserAccountDirectory();
    }

    public static EcoSystem getInstance() {
        if (system == null) {
            system = new EcoSystem();
        }
        return system;
    }

    public Network getNetwork() {
        return network;
    }

    public String getNetworkName() {
        return networkName;
    }

    public UserAccountDirectory getSystemUserAccountDirectory() {
        return systemUserAccountDirectory;
    }

    public UserAccount authenticateUser(String username, String password) {
        UserAccount account = systemUserAccountDirectory.authenticateUser(username, password);
        if (account != null) {
            return account;
        }

        for (Enterprise enterprise : network.getEnterpriseList()) {
            account = enterprise.getUserAccountDirectory().authenticateUser(username, password);
            if (account != null) {
                return account;
            }

            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                account = org.getUserAccountDirectory().authenticateUser(username, password);
                if (account != null) {
                    return account;
                }
            }
        }

        return null;
    }
}
