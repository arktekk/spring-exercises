package no.arktekk.training.spring.domain;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Album {
    private final Double id;
    private final String title;
    private final String artist;
    private final Category category;
    private final Label label;
    private final List<Track> tracks;

    public Album(Double id, String title, String artist, Category category, Label label, List<Track> tracks) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.artist = artist;
        this.label = label;
        this.tracks = tracks;
    }

    public Album(String title, String artist, Category category, Label label, List<Track> tracks) {
        this(null, title, artist, category, label, tracks);
    }

    public String title() {
        return title;
    }

    public String artist() {
        return artist;
    }

    public Category category() {
        return category;
    }


    public Label label() {
        return label;
    }

    public List<Track> tracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (artist != null ? !artist.equals(album.artist) : album.artist != null) return false;
        if (title != null ? !title.equals(album.title) : album.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }
}
