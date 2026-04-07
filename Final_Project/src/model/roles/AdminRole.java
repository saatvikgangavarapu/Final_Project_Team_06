/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.roles;
import javax.swing.JPanel;
import model.Role;
import model.UserAccount;
import ui.admin.SystemAdminWorkAreaJPanel;

/**
 *
 * @author sashajohnson
 */
public class AdminRole extends Role {
    @Override
    public String getRoleName() {
        return "System Admin";
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account) {
        return new SystemAdminWorkAreaJPanel(userProcessContainer, account);
    }
}
