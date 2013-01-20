// ChirpsServiceImpl.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.service;

import static org.springframework.util.StringUtils.hasText;

import java.security.MessageDigest;
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
    private static final Logger log = LoggerFactory.getLogger(ChirpsServiceImpl.class);

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ChirpsDao chirpsDao;

    @Autowired
    private Converter converter;

    private class Token {
        private long userId;
        private String key;

        public Token(long userId, String key) {
            this.userId = userId;
            this.key = key;
        }
    }

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
        if (actor.equals(friend)) {
            log.error("Invalid attempt to make user '{}' follow himself", actor);
            throw new InvalidOperationException("User cannot follow himself");
        }
        usersDao.follow(actor, friend);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#unfollow(java.lang.String, java.lang.String)
     */
    @Override
    public void unfollow(String actor, String who) {
        if (actor.equals(who)) {
            log.error("Invalid attempt to make user '{}' unfollow himself", actor);
            throw new InvalidOperationException("User cannot unfollow himself");
        }
        usersDao.unfollow(actor, who);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#isValidToken(java.lang.String)
     */
    @Override
    public boolean isValidToken(String token) throws Exception {
        Token t = parseToken(token);

        UserEntity u = usersDao.get(t.userId);
        if (u == null)
            return false;

        return checkSecret(t.key, u.getSalt(), u.getHash());
    }

    /**
     * Checks that the secret hash can be obtained using the input key and salt.
     * 
     * @param key
     * @param salt
     * @param hash
     * @return
     * @throws Exception
     */
    private boolean checkSecret(String key, String salt, String hash) throws Exception {
        String secret = salt + key;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(secret.getBytes());
        String digest = bytesToHex(bytes);
        return digest.equals(hash);
    }

    /**
     * @param token
     * @return
     */
    private Token parseToken(String token) {
        if (!hasText(token)) {
            log.error("Attempt to authenticate using a null or empty token");
            throw new AuthenticationException("Attempt to authenticate using a null or empty token");
        }

        int index = token.indexOf('#');
        if (index <= 0 || index == token.length() - 1) {
            log.error("Invalid authentication token format in token '{}'", token);
            throw new AuthenticationException("Invalid authentication token format");
        }

        String userPart = token.substring(0, index);
        String keyPart = token.substring(index + 1);

        long userId = 0;
        try {
            userId = Long.parseLong(userPart);
        } catch (NumberFormatException e) {
            log.error("Cannot extract user identifier from token '{}'", token);
            throw new AuthenticationException("Cannot extract user identifier");
        }

        return new Token(userId, keyPart);
    }

    /**
     * Transaltes bytes to hex format.
     * 
     * @param bytes
     * @return
     */
    private String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++)
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getUserByToken(java.lang.String)
     */
    @Override
    public String getUserByToken(String token) throws Exception {
        Token t = parseToken(token);

        UserEntity u = usersDao.get(t.userId);
        if (u == null)
            return null;

        boolean success = checkSecret(t.key, u.getSalt(), u.getHash());
        return success ? u.getUsername() : null;
    }

}
