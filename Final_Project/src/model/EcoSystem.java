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
    private ArrayList<Enterprise> enterpriseList;
    private UserAccountDirectory systemUserAccountDirectory;
    
    private EcoSystem() {
        networkName = "Disaster Management Network";
        enterpriseList = new ArrayList<>();
        systemUserAccountDirectory = new UserAccountDirectory();
    }

    public static EcoSystem getInstance() {
        if (system == null) {
            system = new EcoSystem();
        }
        return system;
    }

    public String getNetworkName() {
        return networkName;
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void addEnterprise(Enterprise enterprise) {
        enterpriseList.add(enterprise);
    }

    public UserAccountDirectory getSystemUserAccountDirectory() {
        return systemUserAccountDirectory;
    }
    
    public UserAccount authenticateUser(String username, String password) {
        UserAccount account = systemUserAccountDirectory.authenticateUser(username, password);
        if (account != null) {
            return account;
        }

        for (Enterprise enterprise : enterpriseList) {
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
