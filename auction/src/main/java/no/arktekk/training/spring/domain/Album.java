package no.arktekk.training.spring.domain;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Album {
    private final Category category;
    private final Artist artist;
    private final Composer composer;
    private final Label label;
    private final Tracks tracks;

    public Album(Category category, Artist artist, Composer composer, Label label, Tracks tracks) {
        this.category = category;
        this.artist = artist;
        this.composer = composer;
        this.label = label;
        this.tracks = tracks;
    }

    public Category category() {
        return category;
    }

    public Artist artist() {
        return artist;
    }

    public Composer composer() {
        return composer;
    }

    public Label label() {
        return label;
    }

    public Tracks tracks() {
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
        if (category != null ? !category.equals(album.category) : album.category != null) return false;
        if (composer != null ? !composer.equals(album.composer) : album.composer != null) return false;
        if (label != null ? !label.equals(album.label) : album.label != null) return false;
        if (tracks != null ? !tracks.equals(album.tracks) : album.tracks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (composer != null ? composer.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (tracks != null ? tracks.hashCode() : 0);
        return result;
    }
}
