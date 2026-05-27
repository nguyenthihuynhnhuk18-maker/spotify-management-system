package dao;

import model.Track;
import model.TrackStatistic;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Optional;

import java.util.stream.Collectors;

public class TrackDAO implements CrudDAO<Track> {

    Connection conn =
            DatabaseConnection
                    .getInstance()
                    .getConnection();

    // FIND BY ID
    public Optional<Track> findById(
            int id
    ) {

        return findAll()

                .stream()

                .filter(t ->
                        t.getId() == id
                )

                .findFirst();
    }

    // STATISTICS
    public ArrayList<TrackStatistic>
    getStatistics() {

        ArrayList<TrackStatistic> list =
                new ArrayList<>();

        try {

            String sql =

            "SELECT genre, " +
            "COUNT(*) AS total, " +
            "AVG(popularity) AS avg_popularity " +

            "FROM tracks " +

            "GROUP BY genre";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                TrackStatistic statistic =

                        new TrackStatistic(

                                rs.getString("genre"),

                                rs.getInt("total"),

                                rs.getDouble(
                                        "avg_popularity"
                                )
                        );

                list.add(statistic);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // STREAM API
    public ArrayList<Track> getPopularTracks() {

        return findAll()

                .stream()

                .filter(t ->
                        t.getPopularity() >= 90
                )

                .sorted((a,b) ->
                        b.getPopularity()
                        - a.getPopularity()
                )

                .collect(
                        Collectors.toCollection(
                                ArrayList::new
                        )
                );
    }

    // FIND ALL
    @Override
    public ArrayList<Track> findAll() {

        ArrayList<Track> list =
                new ArrayList<>();

        try {

            String sql =

            "SELECT t.track_id, " +
            "t.track_name, " +
            "t.popularity, " +
            "t.duration_ms, " +
            "t.genre, " +
            "a.album_name, " +
            "ar.artist_name " +

            "FROM tracks t " +

            "JOIN albums a " +
            "ON t.album_id = a.album_id " +

            "JOIN artists ar " +
            "ON a.artist_id = ar.artist_id";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Track track =
                        new Track(

                                rs.getInt(
                                        "track_id"
                                ),

                                rs.getString(
                                        "track_name"
                                ),

                                rs.getInt(
                                        "popularity"
                                ),

                                rs.getInt(
                                        "duration_ms"
                                ),

                                rs.getString(
                                        "album_name"
                                ),

                                rs.getString(
                                        "artist_name"
                                ),

                                rs.getString(
                                        "genre"
                                )
                        );

                list.add(track);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // PAGINATION
    public ArrayList<Track> findAll(
            int page,
            int pageSize
    ) {

        ArrayList<Track> list =
                new ArrayList<>();

        try {

            int offset =
                    (page - 1) * pageSize;

            String sql =

            "SELECT t.track_id, " +
            "t.track_name, " +
            "t.popularity, " +
            "t.duration_ms, " +
            "t.genre, " +
            "a.album_name, " +
            "ar.artist_name " +

            "FROM tracks t " +

            "JOIN albums a " +
            "ON t.album_id = a.album_id " +

            "JOIN artists ar " +
            "ON a.artist_id = ar.artist_id " +

            "LIMIT ?, ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, offset);

            ps.setInt(2, pageSize);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Track track =
                        new Track(

                                rs.getInt("track_id"),

                                rs.getString("track_name"),

                                rs.getInt("popularity"),

                                rs.getInt("duration_ms"),

                                rs.getString("album_name"),

                                rs.getString("artist_name"),

                                rs.getString("genre")
                        );

                list.add(track);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // SEARCH
    public ArrayList<Track> search(
            String keyword
    ) {

        ArrayList<Track> list =
                new ArrayList<>();

        try {

        	String sql =

        			"SELECT t.track_id, " +
        			"t.track_name, " +
        			"t.popularity, " +
        			"t.duration_ms, " +
        			"t.genre, " +
        			"a.album_name, " +
        			"ar.artist_name " +

        			"FROM tracks t " +

        			"JOIN albums a " +
        			"ON t.album_id = a.album_id " +

        			"JOIN artists ar " +
        			"ON a.artist_id = ar.artist_id " +

        			"WHERE t.track_name LIKE ? " +
        			"OR ar.artist_name LIKE ? " +
        			"OR a.album_name LIKE ? " +
        			"OR t.genre LIKE ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            String search =
                    "%" + keyword + "%";

            ps.setString(1, search);

            ps.setString(2, search);

            ps.setString(3, search);

            ps.setString(4, search);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Track track =
                        new Track(

                                rs.getInt(
                                        "track_id"
                                ),

                                rs.getString(
                                        "track_name"
                                ),

                                rs.getInt(
                                        "popularity"
                                ),

                                rs.getInt(
                                        "duration_ms"
                                ),

                                rs.getString(
                                        "album_name"
                                ),

                                rs.getString(
                                        "artist_name"
                                ),

                                rs.getString(
                                        "genre"
                                )
                        );

                list.add(track);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // ADD
    @Override
    public void add(
            Track track
    ) {

        try {

            String sql =

                    "INSERT INTO tracks " +

                    "(track_name, " +
                    "popularity, " +
                    "duration_ms, " +
                    "genre, " +
                    "album_id) " +

                    "VALUES (?, ?, ?, ?, 1)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    track.getName()
            );

            ps.setInt(
                    2,
                    track.getPopularity()
            );

            ps.setInt(
                    3,
                    track.getDuration()
            );

            ps.setString(
                    4,
                    track.getGenre()
            );

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // UPDATE
    @Override
    public void update(
            Track track
    ) {

        try {

            String sql =

                    "UPDATE tracks " +

                    "SET track_name=?, " +
                    "popularity=?, " +
                    "duration_ms=?, " +
                    "genre=? " +

                    "WHERE track_id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    track.getName()
            );

            ps.setInt(
                    2,
                    track.getPopularity()
            );

            ps.setInt(
                    3,
                    track.getDuration()
            );

            ps.setString(
                    4,
                    track.getGenre()
            );

            ps.setInt(
                    5,
                    track.getId()
            );

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // DELETE
    @Override
    public void delete(
            int id
    ) {

        try {

            String sql =

                    "DELETE FROM tracks " +
                    "WHERE track_id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // SORT BY POPULARITY
    public ArrayList<Track>
    sortByPopularity() {

        ArrayList<Track> list =
                new ArrayList<>();

        try {

            String sql =

            "SELECT t.track_id, " +
            "t.track_name, " +
            "t.popularity, " +
            "t.duration_ms, " +
            "t.genre, " +
            "a.album_name, " +
            "ar.artist_name " +

            "FROM tracks t " +

            "JOIN albums a " +
            "ON t.album_id = a.album_id " +

            "JOIN artists ar " +
            "ON a.artist_id = ar.artist_id " +

            "ORDER BY t.popularity DESC";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Track track =
                        new Track(

                                rs.getInt(
                                        "track_id"
                                ),

                                rs.getString(
                                        "track_name"
                                ),

                                rs.getInt(
                                        "popularity"
                                ),

                                rs.getInt(
                                        "duration_ms"
                                ),

                                rs.getString(
                                        "album_name"
                                ),

                                rs.getString(
                                        "artist_name"
                                ),

                                rs.getString(
                                        "genre"
                                )
                        );

                list.add(track);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}