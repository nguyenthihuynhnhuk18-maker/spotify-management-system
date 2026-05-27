package factory;

import dao.TrackDAO;
import dao.UserDAO;
import dao.PlaylistDAO;

public class DAOFactory {

    public static TrackDAO getTrackDAO() {
        return new TrackDAO();
    }

    public static UserDAO getUserDAO() {
        return new UserDAO();
    }

    public static PlaylistDAO getPlaylistDAO() {
        return new PlaylistDAO();
    }
}