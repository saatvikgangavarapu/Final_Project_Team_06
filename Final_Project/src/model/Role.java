/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import javax.swing.JPanel;

/**
 *
 * @author sashajohnson
 */
public abstract class Role {
    public abstract String getRoleName();
    public abstract JPanel createWorkArea(JPanel userProcessContainer, UserAccount account);

    @Override
    public String toString() {
        return getRoleName();
    }
}
