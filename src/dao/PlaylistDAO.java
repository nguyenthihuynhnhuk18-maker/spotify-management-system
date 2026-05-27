package dao;

import model.Playlist;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlaylistDAO {

    Connection conn =
            DatabaseConnection
                    .getInstance()
                    .getConnection();

    // FIND ALL
    public ArrayList<Playlist> findAll() {

        ArrayList<Playlist> list =
                new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM playlists";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Playlist p =
                        new Playlist(
                                rs.getInt("playlist_id"),
                                rs.getString("playlist_name")
                        );

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ADD PLAYLIST
    public void add(Playlist playlist) {

        try {

            String sql =
                    "INSERT INTO playlists "
                    + "(playlist_name, user_id) "
                    + "VALUES (?, 1)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1,
                    playlist.getName());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}