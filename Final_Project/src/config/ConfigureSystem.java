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
import model.roles.AmbulanceDriverRole;
import model.roles.CitizenRole;
import model.roles.DoctorRole;
import model.roles.FireOfficerRole;
import model.roles.NGOVolunteerRole;
import model.roles.PoliceOfficerRole;
import model.roles.ShelterManagerRole;
import model.roles.SupplyManagerRole;
import model.roles.AdminRole;

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

        PoliceOrganization policeOrg = new PoliceOrganization();
        FireDepartmentOrganization fireOrg = new FireDepartmentOrganization();
        HospitalOrganization hospitalOrg = new HospitalOrganization();
        AmbulanceOrganization ambulanceOrg = new AmbulanceOrganization();
        FoodSupplyOrganization foodOrg = new FoodSupplyOrganization();
        ShelterManagementOrganization shelterOrg = new ShelterManagementOrganization();

        government.getOrganizationDirectory().addOrganization(policeOrg);
        government.getOrganizationDirectory().addOrganization(fireOrg);

        healthcare.getOrganizationDirectory().addOrganization(hospitalOrg);
        healthcare.getOrganizationDirectory().addOrganization(ambulanceOrg);

        ngo.getOrganizationDirectory().addOrganization(foodOrg);
        ngo.getOrganizationDirectory().addOrganization(shelterOrg);

        system.addEnterprise(government);
        system.addEnterprise(healthcare);
        system.addEnterprise(ngo);

        Person citizen = new Person("P001", "Citizen User", 25, "citizen@test.com");
        government.getOrganizationDirectory().getOrganizationList().get(0).getPersonDirectory().addPerson(citizen);
        government.getOrganizationDirectory().getOrganizationList().get(0).getUserAccountDirectory()
                .createUserAccount("citizen", "citizen123", citizen, new CitizenRole());
       
        Person policeOfficer = new Person("P002", "Police Officer", 34, "police@test.com");
        government.getOrganizationDirectory().getOrganizationList().get(0).getPersonDirectory().addPerson(policeOfficer);
        government.getOrganizationDirectory().getOrganizationList().get(0).getUserAccountDirectory()
                .createUserAccount("police", "police123", policeOfficer, new PoliceOfficerRole());
        
        Person fireOfficer = new Person("P003", "Fire Officer", 31, "fire@test.com");
        government.getOrganizationDirectory().getOrganizationList().get(1).getPersonDirectory().addPerson(fireOfficer);
        government.getOrganizationDirectory().getOrganizationList().get(1).getUserAccountDirectory()
            .createUserAccount("fire", "fire123", fireOfficer, new FireOfficerRole());
        
        Person doctor = new Person("P004", "Doctor User", 40, "doctor@test.com");
        healthcare.getOrganizationDirectory().getOrganizationList().get(0).getPersonDirectory().addPerson(doctor);
        healthcare.getOrganizationDirectory().getOrganizationList().get(0).getUserAccountDirectory()
                .createUserAccount("doctor", "doctor123", doctor, new DoctorRole());

        Person ambulanceDriver = new Person("P005", "Ambulance Driver", 29, "ambulance@test.com");
        healthcare.getOrganizationDirectory().getOrganizationList().get(1).getPersonDirectory().addPerson(ambulanceDriver);
        healthcare.getOrganizationDirectory().getOrganizationList().get(1).getUserAccountDirectory()
                .createUserAccount("ambulance", "ambulance123", ambulanceDriver, new AmbulanceDriverRole());

        Person ngoVolunteer = new Person("P006", "NGO Volunteer", 27, "ngo@test.com");
        ngo.getOrganizationDirectory().getOrganizationList().get(0).getPersonDirectory().addPerson(ngoVolunteer);
        ngo.getOrganizationDirectory().getOrganizationList().get(0).getUserAccountDirectory()
                .createUserAccount("ngo", "ngo123", ngoVolunteer, new NGOVolunteerRole());
        
        Person shelterManager = new Person("P007", "Shelter Manager", 38, "shelter@test.com");
        ngo.getOrganizationDirectory().getOrganizationList().get(1).getPersonDirectory().addPerson(shelterManager);
        ngo.getOrganizationDirectory().getOrganizationList().get(1).getUserAccountDirectory()
                .createUserAccount("shelter", "shelter123", shelterManager, new ShelterManagerRole());

        Person supplyManager = new Person("P008", "Supply Manager", 36, "supply@test.com");
        ngo.getOrganizationDirectory().getOrganizationList().get(0).getPersonDirectory().addPerson(supplyManager);
        ngo.getOrganizationDirectory().getOrganizationList().get(0).getUserAccountDirectory()
                .createUserAccount("supply", "supply123", supplyManager, new SupplyManagerRole());
        
        Person admin = new Person("P000", "System Admin", 35, "admin@test.com");
        system.getSystemUserAccountDirectory()
                .createUserAccount("admin", "admin123", admin, new AdminRole());
        
        FakerDataGenerator.populate(system);

        return system;
    }
}
