package no.arktekk.training.spring.form;

import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.domain.Label;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AlbumForm implements Form<Album> {
    private Double id;
    private String title;
    private String artist;
    private Category category;
    private Label label;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
