/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.directory;
import java.util.ArrayList;
import model.Person;
import model.Role;
import model.UserAccount;

/**
 *
 * @author sashajohnson
 */
public class UserAccountDirectory {
    
    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public UserAccount createUserAccount(String username, String password, Person person, Role role) {
        UserAccount userAccount = new UserAccount(username, password, person, role);
        userAccountList.add(userAccount);
        return userAccount;
    }

    public UserAccount authenticateUser(String username, String password) {
        for (UserAccount ua : userAccountList) {
            if (ua.authenticate(username, password)) {
                return ua;
            }
        }
        return null;
    }

    public boolean isUsernameUnique(String username) {
        for (UserAccount ua : userAccountList) {
            if (ua.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }
}
