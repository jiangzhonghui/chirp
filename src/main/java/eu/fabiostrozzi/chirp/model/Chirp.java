// Chirp.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A chirp.
 * 
 * @author fabio
 */
@XmlRootElement(name = "chirp")
public class Chirp {
    private String id;
    private User user;
    private Date created;
    private String content;

    public Chirp() {}

    /**
     * @param id
     * @param user
     * @param created
     * @param content
     */
    public Chirp(String id, User user, Date created, String content) {
        this.id = id;
        this.user = user;
        this.created = created;
        this.content = content;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String uuid) {
        this.id = uuid;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
