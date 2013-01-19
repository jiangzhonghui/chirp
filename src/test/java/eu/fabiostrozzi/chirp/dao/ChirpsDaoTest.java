// ChirpsDaoTest.java, created on Jan 19, 2013
package eu.fabiostrozzi.chirp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tets the data access layer for the {@link ChirpEntity} and {@link ChirpUserEntity} entities.
 * 
 * @author fabio
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ChirpsDaoTest {

    @Autowired
    private ChirpsDao dao;

    @Test
    public void testFindByUsername() {
        List<ChirpUserEntity> entities = dao.findByUsername("fabio.strozzi");
        assertNotNull("Returned list is never empty", entities);
        assertEquals("17 exactly chirps are returned", 17, entities.size());

        HashSet<String> users = new HashSet<>();
        for (ChirpUserEntity e : entities)
            users.add(e.getUser().getUsername());

        assertTrue("Some chirps are from 'fabio.strozzi'", users.contains("fabio.strozzi"));
        assertTrue("Some chirps are from 'jack.sparrow'", users.contains("jack.sparrow"));
        assertTrue("Some chirps are from 'ip.man'", users.contains("ip.man"));
        assertTrue("Some chirps are from 'tiger.man'", users.contains("tiger.man"));
    }

    @Test
    public void testFindByUsernameAndKey() {
        List<ChirpUserEntity> entities = dao.findByUsernameAndKey("fabio.strozzi", "like");
        assertNotNull("Returned list is never empty", entities);
        assertEquals("3 exactly chirps are returned", 3, entities.size());

        for (ChirpUserEntity e : entities)
            assertTrue("Chirp contains 'like' keyword", e.getChirp().getContent().contains("like"));
    }
}
