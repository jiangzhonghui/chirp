// Converter.java, created on Jan 19, 2013
package eu.fabiostrozzi.chirp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import eu.fabiostrozzi.chirp.dao.UserData;
import eu.fabiostrozzi.chirp.rest.User;

/**
 * Converts entities from one layer to another.
 * 
 * @author fabio
 */
@Component
public class Converter {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(Converter.class);

    /**
     * @param data
     * @return
     */
    public List<User> restUsersOf(List<UserData> data) {
        ArrayList<User> users = new ArrayList<>();
        for (UserData ud : data)
            users.add(restUserOf(ud));
        return users;
    }

    /**
     * @param ud
     * @return
     */
    public User restUserOf(UserData ud) {
        User u = new User();
        u.setFirstName(ud.getFirstName());
        u.setLastName(ud.getLastName());
        u.setUsername(ud.getUsername());
        return u;
    }

}
