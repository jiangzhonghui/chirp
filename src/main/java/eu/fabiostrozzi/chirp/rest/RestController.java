// RestController.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.rest;

import static org.springframework.util.StringUtils.hasText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.fabiostrozzi.chirp.service.ChirpsService;

/**
 * REST api controller.
 * 
 * @author fabio
 */
@Controller
@RequestMapping
public class RestController {
    private static final Logger log = LoggerFactory.getLogger(RestController.class);
    public static final String TOKEN = "Chirp-Token";

    @Autowired
    private ChirpsService service;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @SuppressWarnings("serial")
    public class NotFoundException extends Exception {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @SuppressWarnings("serial")
    public class IntSrvErrorException extends Exception {}

    /**
     * Get the chirps/tweets of a given user.
     * <p>
     * Anyone is allowed to read them as long as the token is valid.
     * 
     * @param user
     * @param key
     * @param token
     * @return
     */
    @RequestMapping(value = "/{user}/chirps", method = RequestMethod.GET)
    public @ResponseBody
    List<Chirp> get(@PathVariable String user, @RequestParam(value = "search", required = false) String key,
            @RequestHeader(value = TOKEN, required = false) String token) throws IntSrvErrorException,
            NotFoundException {
        try {
            if (!service.userExists(user))
                throw new NotFoundException();

            List<Chirp> chirps = hasText(key) ? service.searchChirpsFor(user, key) : service.getChirpsFor(user);
            return chirps;

        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred while calling '/api/{}/chirps'", user, e);
            throw new IntSrvErrorException();
        }
    }

    /**
     * Get the list of people being followed by the given user and those that are followers of the
     * user.
     * <p>
     * Anyone is allowed to get them as long as the token is valid.
     * 
     * @param user
     * @param token
     * @return
     */
    @RequestMapping(value = "/{user}/people", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, List<User>> getPeople(@PathVariable String user,
            @RequestHeader(value = TOKEN, required = false) String token) throws IntSrvErrorException,
            NotFoundException {
        try {
            if (!service.userExists(user))
                throw new NotFoundException();

            Map<String, List<User>> map = new HashMap<String, List<User>>();
            map.put("followed", service.getFollowedBy(user));
            map.put("followers", service.getFollowersOf(user));
            return map;

        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred while calling '/api/{}/people'", user, e);
            throw new IntSrvErrorException();
        }
    }

    /**
     * Follows a user.
     * <p>
     * User identified by the input token will start following the given user.
     * 
     * @param user
     * @param token
     * @return
     */
    @RequestMapping(value = "/{user}/follow", method = RequestMethod.PUT)
    public @ResponseBody
    Boolean follow(@PathVariable String user, @RequestHeader(value = TOKEN, required = false) String token)
            throws IntSrvErrorException, NotFoundException {
        try {
            if (!service.userExists(user))
                throw new NotFoundException();

            String me = service.getUserByToken(token);
            service.follow(me, user);
            return true;

        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred while calling '/api/{}/follow'", user, e);
            throw new IntSrvErrorException();
        }
    }

    /**
     * Unfollows a user.
     * <p>
     * User identified by the input token will stop following the given user.
     * 
     * @param user
     * @param token
     * @return
     */
    @RequestMapping(value = "/{user}/unfollow", method = RequestMethod.PUT)
    public @ResponseBody
    Boolean unfollow(@PathVariable String user, @RequestHeader(value = TOKEN, required = false) String token)
            throws IntSrvErrorException, NotFoundException {
        try {
            if (!service.userExists(user))
                throw new NotFoundException();

            String me = service.getUserByToken(token);
            service.unfollow(me, user);
            return true;

        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred while calling '/api/{}/follow'", user, e);
            throw new IntSrvErrorException();
        }
    }
}
