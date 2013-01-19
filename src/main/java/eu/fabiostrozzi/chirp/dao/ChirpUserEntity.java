// ChirpUserEntity.java, created on Jan 19, 2013
package eu.fabiostrozzi.chirp.dao;

/**
 * Database layer abstraction of a chirp along with the owner data.
 * 
 * @author fabio
 */
public class ChirpUserEntity {
    private ChirpEntity chirp;
    private UserEntity user;

    /**
     * @return the chirp
     */
    public ChirpEntity getChirp() {
        return chirp;
    }

    /**
     * @param chirp
     *            the chirp to set
     */
    public void setChirp(ChirpEntity chirp) {
        this.chirp = chirp;
    }

    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

}
