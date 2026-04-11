/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.roles;

import javax.swing.JPanel;
import model.Role;
import model.UserAccount;
import ui.citizen.CitizenWorkAreaJPanel;

/**
 *
 * @author sashajohnson
 */
public class CitizenRole extends Role{
    @Override
    public String getRoleName() {
        return "Citizen";
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account) {
        return new CitizenWorkAreaJPanel(userProcessContainer, account);
    }
}
