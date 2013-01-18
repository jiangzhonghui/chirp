// User.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A user.
 * 
 * @author fabio
 */
@XmlRootElement(name = "user")
public class User {

    private String username;
    private String firstName;
    private String lastName;

    /**
     * 
     */
    public User() {}

    /**
     * @param username
     * @param firstName
     * @param lastName
     */
    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
