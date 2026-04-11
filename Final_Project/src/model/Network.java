/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import model.enterprise.Enterprise;

/**
 *
 * @author TanviModi
 */
public class Network {
    
     private ArrayList<Enterprise> enterpriseList;

    public Network() {
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void addEnterprise(Enterprise enterprise) {
        enterpriseList.add(enterprise);
    }
    
}
