/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author sashajohnson
 */
public class IdGenerator {
    private static int counter = 0;
    
    public static String generateId() {
        counter++;
        return "REQ-" + counter;
    }
}
