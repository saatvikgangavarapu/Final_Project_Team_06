/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.roles;

import javax.swing.JPanel;
import model.Network;
import model.Role;
import model.UserAccount;
import ui.police.PoliceWorkAreaJPanel;

/**
 *
 * @author sashajohnson
 */
public class PoliceOfficerRole extends Role {
    @Override
    public String getRoleName() {
        return "Police Officer";
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Network network) {
        return new PoliceWorkAreaJPanel(userProcessContainer, account);
    }
}
