/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;


import java.util.ArrayList;
import java.util.Random;
import model.EcoSystem;
import model.Person;
import model.UserAccount;
import model.WorkRequest;
import model.enterprise.Enterprise;
import model.organization.FireDepartmentOrganization;
import model.organization.FoodSupplyOrganization;
import model.organization.HospitalOrganization;
import model.organization.Organization;
import model.organization.PoliceOrganization;
import model.organization.ShelterManagementOrganization;
import model.requests.EmergencyRescueRequest;
import model.requests.EvacuationRequest;
import model.requests.MedicalAidRequest;
import model.requests.PoliceRequest;
import model.requests.ShelterRequest;
import model.requests.SupplyRequest;
import model.roles.CitizenRole;

/**
 *
 * @author sashajohnson
 */
public class FakerDataGenerator {
    private static int requestCounter = 1000;
    private static final Random random = new Random();

    private static final String[] names = {
        "Alex Johnson",
        "Taylor Smith",
        "Jordan Lee",
        "Morgan Brown",
        "Casey Davis",
        "Jamie Wilson",
        "Riley Moore",
        "Cameron White"
    };

    private static final String[] messages = {
        "Emergency assistance needed",
        "Medical help required",
        "Evacuation support needed",
        "Food and water requested",
        "Shelter assistance required",
        "Rescue team requested"
    };
    
    private static String generateRequestId() {
        requestCounter++;
        return "REQ-" + requestCounter;
    }

    public static void populate(EcoSystem system) {

        ArrayList<Organization> allOrganizations = new ArrayList<>();
        ArrayList<UserAccount> citizenAccounts = new ArrayList<>();

        for (Enterprise enterprise : system.getNetwork().getEnterpriseList()) {
            allOrganizations.addAll(enterprise.getOrganizationDirectory().getOrganizationList());
        }

        Organization policeOrg = findOrganization(allOrganizations, PoliceOrganization.class);
        Organization fireOrg = findOrganization(allOrganizations, FireDepartmentOrganization.class);
        Organization hospitalOrg = findOrganization(allOrganizations, HospitalOrganization.class);
        Organization supplyOrg = findOrganization(allOrganizations, FoodSupplyOrganization.class);
        Organization shelterOrg = findOrganization(allOrganizations, ShelterManagementOrganization.class);

        // Create extra citizen accounts for demo purposes
        if (policeOrg != null) {
            for (int i = 0; i < 4; i++) {
                String name = names[i];
                String personId = "FC" + (100 + i);
                String email = "citizen" + i + "@demo.com";

                Person person = new Person(personId, name, 22 + i, email);
                policeOrg.getPersonDirectory().addPerson(person);

                UserAccount account = policeOrg.getUserAccountDirectory().createUserAccount(
                        "demoCitizen" + i,
                        "pass123",
                        person,
                        new CitizenRole()
                );

                citizenAccounts.add(account);
            }
        }

        if (citizenAccounts.isEmpty()) {
            return;
        }

        // Create sample requests
        createPoliceRequests(policeOrg, citizenAccounts, 3);
        createFireRequests(fireOrg, citizenAccounts, 3);
        createMedicalRequests(hospitalOrg, citizenAccounts, 3);
        createSupplyRequests(supplyOrg, citizenAccounts, 3);
        createShelterRequests(shelterOrg, citizenAccounts, 3);
        createEvacuationRequests(policeOrg, citizenAccounts, 2);
    }

    private static void createPoliceRequests(Organization policeOrg, ArrayList<UserAccount> citizens, int count) {
        if (policeOrg == null) {
            return;
        }

        for (int i = 0; i < count; i++) {
            PoliceRequest request = new PoliceRequest();
            populateBaseRequest(request, citizens);
            policeOrg.getWorkQueue().addWorkRequest(request);
        }
    }

    private static void createFireRequests(Organization fireOrg, ArrayList<UserAccount> citizens, int count) {
        if (fireOrg == null) {
            return;
        }

        for (int i = 0; i < count; i++) {
            EmergencyRescueRequest request = new EmergencyRescueRequest();
            populateBaseRequest(request, citizens);
            fireOrg.getWorkQueue().addWorkRequest(request);
        }
    }

    private static void createMedicalRequests(Organization hospitalOrg, ArrayList<UserAccount> citizens, int count) {
        if (hospitalOrg == null) {
            return;
        }

        for (int i = 0; i < count; i++) {
            MedicalAidRequest request = new MedicalAidRequest();
            populateBaseRequest(request, citizens);
            hospitalOrg.getWorkQueue().addWorkRequest(request);
        }
    }

    private static void createSupplyRequests(Organization supplyOrg, ArrayList<UserAccount> citizens, int count) {
        if (supplyOrg == null) {
            return;
        }

        for (int i = 0; i < count; i++) {
            SupplyRequest request = new SupplyRequest();
            populateBaseRequest(request, citizens);
            supplyOrg.getWorkQueue().addWorkRequest(request);
        }
    }

    private static void createShelterRequests(Organization shelterOrg, ArrayList<UserAccount> citizens, int count) {
        if (shelterOrg == null) {
            return;
        }

        for (int i = 0; i < count; i++) {
            ShelterRequest request = new ShelterRequest();
            populateBaseRequest(request, citizens);
            shelterOrg.getWorkQueue().addWorkRequest(request);
        }
    }

    private static void createEvacuationRequests(Organization policeOrg, ArrayList<UserAccount> citizens, int count) {
        if (policeOrg == null) {
            return;
        }

        for (int i = 0; i < count; i++) {
            EvacuationRequest request = new EvacuationRequest();
            populateBaseRequest(request, citizens);
            policeOrg.getWorkQueue().addWorkRequest(request);
        }
    }

    private static void populateBaseRequest(WorkRequest request, ArrayList<UserAccount> citizens) {
        request.setRequestId(generateRequestId());
        request.setSender(randomCitizen(citizens));
        request.setStatus(randomStatus());
        request.setMessage(randomFrom(messages));
        request.setPriority(randomPriority());
    }

    private static UserAccount randomCitizen(ArrayList<UserAccount> citizens) {
        return citizens.get(random.nextInt(citizens.size()));
    }

    private static String randomStatus() {
        String[] statuses = {
            "Pending",
            "Assigned",
            "In Progress",
            "Completed"
        };
        return statuses[random.nextInt(statuses.length)];
    }

    private static String randomPriority() {
        String[] priorities = {
            "Low",
            "Medium",
            "High"
        };
        return priorities[random.nextInt(priorities.length)];
    }

    private static String randomFrom(String[] values) {
        return values[random.nextInt(values.length)];
    }

    private static Organization findOrganization(ArrayList<Organization> organizations, Class<?> organizationClass) {
        for (Organization org : organizations) {
            if (organizationClass.isInstance(org)) {
                return org;
            }
        }
        return null;
    }
}
