package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import dao.TrackDAO;
import factory.DAOFactory;
import model.Track;

public class TrackDAOTest {

    @Test
    public void testFindAll() {

        TrackDAO dao =
                DAOFactory.getTrackDAO();

        assertTrue(
                dao.findAll().size() > 0
        );
    }

    @Test
    public void testPopularTracks() {

        TrackDAO dao =
                DAOFactory.getTrackDAO();

        assertNotNull(
                dao.getPopularTracks()
        );
    }

    // TEST SEARCH
    @Test
    public void testSearch() {

        TrackDAO dao =
                DAOFactory.getTrackDAO();

        ArrayList<Track> list =
                dao.search("Shape");

        assertTrue(
                list.size() > 0
        );
    }

    // TEST FIND BY ID
    @Test
    public void testFindById() {

        TrackDAO dao =
                DAOFactory.getTrackDAO();

        Optional<Track> track =
                dao.findById(1);

        assertTrue(
                track.isPresent()
        );
    }

    // TEST PAGINATION
    @Test
    public void testPagination() {

        TrackDAO dao =
                DAOFactory.getTrackDAO();

        ArrayList<Track> list =
                dao.findAll(1, 3);

        assertEquals(
                3,
                list.size()
        );
    }

    // TEST SORT
    @Test
    public void testSortByPopularity() {

        TrackDAO dao =
                DAOFactory.getTrackDAO();

        ArrayList<Track> list =
                dao.sortByPopularity();

        assertTrue(
                list.get(0).getPopularity()
                >=
                list.get(1).getPopularity()
        );
    }
}