package no.arktekk.training.spring.domain;

import java.util.UUID;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Album {
    private String id;
    private final String title;
    private final String artist;
    private final Category category;
    private final Label label;

    public Album(String id, String title, String artist, Category category, Label label) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.artist = artist;
        this.label = label;
    }

    public String id() {
        return id;
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

    public void assignNewId() {
        id = UUID.randomUUID().toString();
    }
}
