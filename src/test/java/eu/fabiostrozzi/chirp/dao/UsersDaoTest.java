// UsersDaoTest.java, created on Jan 19, 2013
package eu.fabiostrozzi.chirp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tets the data access layer for the {@link UserData} entities.
 * 
 * @author fabio
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UsersDaoTest {

    @Autowired
    private UsersDao dao;

    @Test
    public void testGetByToken() {
        UserData user = dao.getByToken("fc57c1fc-60fd-11e2-8d5b-544249f16afb");
        assertNotNull(user);
        assertEquals("fabio.strozzi", user.getUsername());
        assertEquals("Fabio", user.getFirstName());
        assertEquals("Strozzi", user.getLastName());
        assertEquals("fc57c1fc-60fd-11e2-8d5b-544249f16afb", user.getToken());

        user = dao.getByToken("123456767");
        assertNull(user);
    }

    @Test
    public void testGetByUsername() {
        UserData user = dao.getByUsername("fabio.strozzi");
        assertNotNull(user);
        assertEquals("fabio.strozzi", user.getUsername());
        assertEquals("Fabio", user.getFirstName());
        assertEquals("Strozzi", user.getLastName());
        assertEquals("fc57c1fc-60fd-11e2-8d5b-544249f16afb", user.getToken());
        
        user = dao.getByToken("john.doe");
        assertNull(user);
    }

    @Test
    public void testFollow() {
        
    }
    
    
    @Test
    public void testUnfollow() {
        
    }
}
