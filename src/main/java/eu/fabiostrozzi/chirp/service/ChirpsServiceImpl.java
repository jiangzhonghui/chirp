// ChirpsServiceImpl.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import eu.fabiostrozzi.chirp.model.Chirp;
import eu.fabiostrozzi.chirp.model.User;

/**
 * @author fabio
 */
@Service
public class ChirpsServiceImpl implements ChirpsService {
    private static final Logger log = LoggerFactory.getLogger(ChirpsServiceImpl.class);

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.ChirpsService#getFollowingOf(java.lang.String)
     */
    @Override
    public List<User> getFollowingOf(String user) {
        // TODO
        return null;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.ChirpsService#getFollowersOf(java.lang.String)
     */
    @Override
    public List<User> getFollowersOf(String user) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.ChirpsService#getChirpsOf(java.lang.String)
     */
    @Override
    public List<Chirp> getChirpsOf(String user) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#searchChirpsOf(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Chirp> searchChirpsOf(String user, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.ChirpsService#userExists(java.lang.String)
     */
    @Override
    public boolean userExists(String user) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#follow(java.lang.String, java.lang.String)
     */
    @Override
    public void follow(String actor, String friend) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#unfollow(java.lang.String, java.lang.String)
     */
    @Override
    public void unfollow(String actor, String who) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#isValidToken(java.lang.String)
     */
    @Override
    public boolean isValidToken(String token) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getUserByToken(java.lang.String)
     */
    @Override
    public String getUserByToken(String token) {
        // TODO Auto-generated method stub
        return null;
    }

}
