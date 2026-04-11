/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.roles;

import javax.swing.JPanel;
import model.Role;
import model.UserAccount;
import ui.fire.FireWorkAreaJPanel;
import model.Network;


/**
 *
 * @author sashajohnson
 */
public class FireOfficerRole extends Role{
    @Override
    public String getRoleName() {
        return "Fire Officer";
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Network network) {
        return new FireWorkAreaJPanel(userProcessContainer, account);
    }
}
