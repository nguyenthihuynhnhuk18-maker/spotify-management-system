package model;

public class Track {

    private int id;

    private String name;

    private int popularity;

    private int duration;

    private String albumName;

    private String artistName;

    // ADD
    private String genre;

    // EMPTY CONSTRUCTOR
    public Track() {

    }

    // CONSTRUCTOR
    public Track(
            int id,
            String name,
            int popularity,
            int duration,
            String albumName,
            String artistName,
            String genre
    ) {

        this.id = id;

        this.name = name;

        this.popularity = popularity;

        this.duration = duration;

        this.albumName = albumName;

        this.artistName = artistName;

        this.genre = genre;
    }

    // ADD TRACK
    public Track(
            String name,
            int popularity,
            int duration
    ) {

        this.name = name;

        this.popularity = popularity;

        this.duration = duration;
    }

    // GETTER SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    // ADD
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}