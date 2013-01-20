// ChirpsService.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.service;

import java.util.List;

import eu.fabiostrozzi.chirp.rest.Chirp;
import eu.fabiostrozzi.chirp.rest.User;

/**
 * Chirps service layer.
 * 
 * @author fabio
 */
public interface ChirpsService {

    /**
     * @param user
     * @return
     */
    List<User> getFollowedBy(String user);

    /**
     * @param user
     * @return
     */
    List<User> getFollowersOf(String user);

    /**
     * @param user
     * @return
     */
    List<Chirp> getChirpsFor(String user);

    /**
     * @param user
     * @param key
     * @return
     */
    List<Chirp> searchChirpsFor(String user, String key);

    /**
     * @param user
     * @return
     */
    boolean userExists(String user);

    /**
     * @param actor
     * @param friend
     */
    void follow(String actor, String friend);

    /**
     * @param actor
     * @param who
     */
    void unfollow(String actor, String who);

    /**
     * Tests whether a token is valid or not.
     * <p>
     * Valid tokens have the following format:
     * 
     * <pre>
     * user_id#authentication_key
     * </pre>
     * 
     * @param token
     * @return
     * @throws Exception
     */
    boolean isValidToken(String token) throws Exception;

    /**
     * @param token
     * @return
     * @throws Exception
     */
    String getUserByToken(String token) throws Exception;
}
