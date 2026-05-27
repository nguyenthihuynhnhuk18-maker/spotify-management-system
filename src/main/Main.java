package main;

import dao.TrackDAO;
import model.Track;
import factory.DAOFactory;
public class Main {

    public static void main(String[] args) {

        TrackDAO dao =
        		DAOFactory.getTrackDAO();

        for(Track t : dao.findAll()) {

            System.out.println(
                    t.getId()
                    + " - "
                    + t.getName()
                    + " - "
                    + t.getPopularity()
            );
        }
    }
}