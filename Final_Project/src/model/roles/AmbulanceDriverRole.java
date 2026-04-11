/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.roles;
import javax.swing.JPanel;
import model.Role;
import model.UserAccount;
import ui.ambulance.AmbulanceWorkAreaJPanel;
import model.Network;
/**
 *
 * @author sashajohnson
 */
public class AmbulanceDriverRole extends Role{
    
    @Override
    public String getRoleName() {
        return "Ambulance Driver";
    }
    
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Network network) {
        return new AmbulanceWorkAreaJPanel(userProcessContainer, account, network);
    }    
}
