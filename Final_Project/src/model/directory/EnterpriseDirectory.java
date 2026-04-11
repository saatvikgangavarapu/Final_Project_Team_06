/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.directory;
import java.util.ArrayList;
import model.enterprise.Enterprise;

/**
 *
 * @author sashajohnson
 */
public class EnterpriseDirectory {
    private ArrayList<Enterprise> enterpriseList;

    public EnterpriseDirectory() {
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public Enterprise createAndAddEnterprise(String name, String type) {
        Enterprise enterprise = new Enterprise(name, type) {};
        enterpriseList.add(enterprise);
        return enterprise;
    }
}
