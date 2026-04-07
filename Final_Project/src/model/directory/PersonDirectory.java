/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.directory;
import java.util.ArrayList;
import model.Person;

/**
 *
 * @author sashajohnson
 */
public class PersonDirectory {
    private ArrayList<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void addPerson(Person person) {
        personList.add(person);
    }
}
