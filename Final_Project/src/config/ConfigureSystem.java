/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import model.EcoSystem;
import model.Person;
import model.enterprise.GovernmentEmergencyEnterprise;
import model.enterprise.HealthcareEnterprise;
import model.enterprise.NGOReliefEnterprise;
import model.organization.AmbulanceOrganization;
import model.organization.FireDepartmentOrganization;
import model.organization.FoodSupplyOrganization;
import model.organization.HospitalOrganization;
import model.organization.PoliceOrganization;
import model.organization.ShelterManagementOrganization;
import model.roles.CitizenRole;

/**
 *
 * @author sashajohnson
 */
public class ConfigureSystem {
    public static EcoSystem configure() {
        EcoSystem system = EcoSystem.getInstance();

        if (!system.getEnterpriseList().isEmpty()) {
            return system;
        }

        GovernmentEmergencyEnterprise government = new GovernmentEmergencyEnterprise("Government Emergency Services");
        HealthcareEnterprise healthcare = new HealthcareEnterprise("Healthcare Services");
        NGOReliefEnterprise ngo = new NGOReliefEnterprise("NGO & Relief Services");

        government.getOrganizationDirectory().addOrganization(new PoliceOrganization());
        government.getOrganizationDirectory().addOrganization(new FireDepartmentOrganization());

        healthcare.getOrganizationDirectory().addOrganization(new HospitalOrganization());
        healthcare.getOrganizationDirectory().addOrganization(new AmbulanceOrganization());

        ngo.getOrganizationDirectory().addOrganization(new FoodSupplyOrganization());
        ngo.getOrganizationDirectory().addOrganization(new ShelterManagementOrganization());

        system.addEnterprise(government);
        system.addEnterprise(healthcare);
        system.addEnterprise(ngo);

        Person citizen = new Person("P001", "Citizen User", 25, "citizen@test.com");
        government.getOrganizationDirectory().getOrganizationList().get(0).getPersonDirectory().addPerson(citizen);
        government.getOrganizationDirectory().getOrganizationList().get(0).getUserAccountDirectory()
                .createUserAccount("citizen", "citizen123", citizen, new CitizenRole());

        return system;
    }
}
