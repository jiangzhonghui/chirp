// ChirpsServiceImpl.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.fabiostrozzi.chirp.dao.ChirpUserEntity;
import eu.fabiostrozzi.chirp.dao.ChirpsDao;
import eu.fabiostrozzi.chirp.dao.UserEntity;
import eu.fabiostrozzi.chirp.dao.UsersDao;
import eu.fabiostrozzi.chirp.rest.Chirp;
import eu.fabiostrozzi.chirp.rest.User;

/**
 * Implements the logic of the service layer.
 * 
 * @author fabio
 */
@Service
public class ChirpsServiceImpl implements ChirpsService {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ChirpsServiceImpl.class);

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ChirpsDao chirpsDao;

    @Autowired
    private Converter converter;

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getFollowingOf(java.lang.String)
     */
    @Override
    public List<User> getFollowedBy(String user) {
        List<UserEntity> data = usersDao.getFollowedBy(user);
        List<User> users = converter.restUsersOf(data);
        return users;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getFollowersOf(java.lang.String)
     */
    @Override
    public List<User> getFollowersOf(String user) {
        List<UserEntity> data = usersDao.getFollowersOf(user);
        List<User> users = converter.restUsersOf(data);
        return users;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getChirpsOf(java.lang.String)
     */
    @Override
    public List<Chirp> getChirpsFor(String user) {
        List<ChirpUserEntity> data = chirpsDao.findByUsername(user);
        List<Chirp> chirps = converter.restChirpsOf(data);
        return chirps;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#searchChirpsOf(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Chirp> searchChirpsFor(String user, String key) {
        List<ChirpUserEntity> data = chirpsDao.findByUsernameAndKey(user, key);
        List<Chirp> chirps = converter.restChirpsOf(data);
        return chirps;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#userExists(java.lang.String)
     */
    @Override
    public boolean userExists(String user) {
        UserEntity u = usersDao.getByUsername(user);
        return u != null;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#follow(java.lang.String, java.lang.String)
     */
    @Override
    public void follow(String actor, String friend) {
        usersDao.follow(actor, friend);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#unfollow(java.lang.String, java.lang.String)
     */
    @Override
    public void unfollow(String actor, String who) {
        usersDao.unfollow(actor, who);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#isValidToken(java.lang.String)
     */
    @Override
    public boolean isValidToken(String token) {
        UserEntity u = usersDao.getByToken(token);
        return u != null;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getUserByToken(java.lang.String)
     */
    @Override
    public String getUserByToken(String token) {
        UserEntity u = usersDao.getByToken(token);
        return u != null ? u.getUsername() : null;
    }

}
