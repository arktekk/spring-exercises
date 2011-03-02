package no.arktekk.training.spring.domain;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Artist {
    final String name;


    public Artist(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (name != null ? !name.equals(artist.name) : artist.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
