/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ineatconseil.antwerp;

/**
 *
 * @author nicolasger
 */
public class Player {
    private Long id = System.currentTimeMillis();
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}
