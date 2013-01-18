// MockedChirpsService.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import eu.fabiostrozzi.chirp.model.Chirp;
import eu.fabiostrozzi.chirp.model.User;
import eu.fabiostrozzi.chirp.service.ChirpsService;

/**
 * Mocked implementation of the {@link ChirpsService}.
 * 
 * @author fabio
 */
@Service
public class MockedChirpsService implements ChirpsService {
    private static final Logger log = LoggerFactory.getLogger(MockedChirpsService.class);

    private List<User> users = new ArrayList<>();
    private Map<String, List<Chirp>> chirpsOf = new Hashtable<>();
    private Map<String, List<User>> followersOf = new Hashtable<>();
    private Map<String, List<User>> following = new Hashtable<>();
    private Map<String, String> tokenOf = new Hashtable<>();

    public MockedChirpsService() {
        users.add(new User("fabio.strozzi", "Fabio", "Strozzi"));
        users.add(new User("jack.sparrow", "Jack", "Sparrow"));
        users.add(new User("tiger.man", "Tiger", "Man"));
        users.add(new User("hulk.hogan", "Hulk", "Hogan"));
        users.add(new User("ip.man", "Ip", "Man"));

        tokenOf.put("fc57c1fc-60fd-11e2-8d5b-544249f16afb", "fabio.strozzi");
        tokenOf.put("02392a98-60fe-11e2-8d6e-544249f16afb", "jack.sparrow");
        tokenOf.put("03b60f6c-60fe-11e2-bd3f-544249f16afb", "tiger.man");
        tokenOf.put("048ef8ea-60fe-11e2-ae31-544249f16afb", "hulk.hogan");
        tokenOf.put("54dd989c-60fe-11e2-a0f3-544249f16afb", "ip.man");

        List<User> list = new ArrayList<>();
        list.add(users.get(1));
        list.add(users.get(2));
        list.add(users.get(3));
        followersOf.put("fabio.strozzi", list);

        list = new ArrayList<>();
        list.add(users.get(0));
        list.add(users.get(3));
        list.add(users.get(4));
        followersOf.put("jack.sparrow", list);

        list = new ArrayList<>();
        list.add(users.get(0));
        followersOf.put("ip.man", list);

        list = new ArrayList<>();
        list.add(users.get(1));
        list.add(users.get(4));
        following.put("fabio.strozzi", list);

        list = new ArrayList<>();
        list.add(users.get(0));
        list.add(users.get(3));
        following.put("hulk.hogan", list);

        List<Chirp> fabiosChirps = new ArrayList<>();
        fabiosChirps.add(new Chirp("1", users.get(0), new Date(), "First chirp!"));
        fabiosChirps.add(new Chirp("2", users.get(0), new Date(), "Second chirp!"));
        fabiosChirps.add(new Chirp("3", users.get(0), new Date(), "Third chirp!"));
        fabiosChirps.add(new Chirp("4", users.get(0), new Date(), "Last chirp!"));
        chirpsOf.put("fabio.strozzi", fabiosChirps);

        List<Chirp> jacksChirps = new ArrayList<>();
        fabiosChirps.add(new Chirp("5", users.get(1), new Date(), "I'm a pirate!"));
        fabiosChirps.add(new Chirp("6", users.get(1), new Date(), "I don't know where I am!"));
        fabiosChirps.add(new Chirp("7", users.get(1), new Date(), "I like sailing!"));

        jacksChirps.addAll(fabiosChirps);
        chirpsOf.put("jack.sparrow", jacksChirps);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getFollowingOf(java.lang.String)
     */
    @Override
    public List<User> getFollowingOf(String user) {
        List<User> list = following.get(user);
        return list == null ? new ArrayList<User>() : list;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getFollowersOf(java.lang.String)
     */
    @Override
    public List<User> getFollowersOf(String user) {
        List<User> list = followersOf.get(user);
        return list == null ? new ArrayList<User>() : list;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getChirpsOf(java.lang.String)
     */
    @Override
    public List<Chirp> getChirpsOf(String user) {
        List<Chirp> chirps = chirpsOf.get(user);
        return chirps == null ? new ArrayList<Chirp>() : chirps;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#searchChirpsOf(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Chirp> searchChirpsOf(String user, String key) {
        return getChirpsOf(user);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#userExists(java.lang.String)
     */
    @Override
    public boolean userExists(String user) {
        for (User u : users) {
            if (u.getUsername().equals(user))
                return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#follow(java.lang.String, java.lang.String)
     */
    @Override
    public void follow(String actor, String friend) {
        log.info("User '{}' is no following '{}'", actor, friend);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#unfollow(java.lang.String, java.lang.String)
     */
    @Override
    public void unfollow(String actor, String who) {
        log.info("User '{}' is no longer following '{}'", actor, who);
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#isValidToken(java.lang.String)
     */
    @Override
    public boolean isValidToken(String token) {
        return tokenOf.get(token) != null;
    }

    /*
     * (non-Javadoc)
     * @see eu.fabiostrozzi.chirp.service.ChirpsService#getUserByToken(java.lang.String)
     */
    @Override
    public String getUserByToken(String token) {
        return tokenOf.get(token);
    }

}
