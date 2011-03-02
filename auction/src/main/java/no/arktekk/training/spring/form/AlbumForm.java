package no.arktekk.training.spring.form;

import no.arktekk.training.spring.domain.*;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AlbumForm implements Form<Album> {
    private Category category;
    private Artist artist;
    private Composer composer;
    private Label label;
    private Tracks tracks;

    /**
     * *********************************************************************
     * Getters and Setters section.. Unfortunatly needed by spring mvc :(  *
     * *********************************************************************
     */
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Composer getComposer() {
        return composer;
    }

    public void setComposer(Composer composer) {
        this.composer = composer;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}
