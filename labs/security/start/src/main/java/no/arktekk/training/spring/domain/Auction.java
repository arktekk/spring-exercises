package no.arktekk.training.spring.domain;

import org.joda.time.DateTime;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Auction {
    private final double id;
    private final double minimumPrice;
    private final String description;
    private final DateTime starts;
    private final DateTime expires;
    private final List<Album> albums;

    public Auction(double id, double minimumPrice, String description, DateTime starts, DateTime expires, List<Album> albums) {
        this.id = id;
        this.minimumPrice = minimumPrice;
        this.description = description;
        this.starts = starts;
        this.expires = expires;
        this.albums = albums;
    }

    public double id() {
        return id;
    }

    public double minimumPrice() {
        return minimumPrice;
    }

    public String description() {
        return description;
    }

    public DateTime starts() {
        return starts;
    }

    public DateTime expires() {
        return expires;
    }

    public List<Album> albums() {
        return albums;
    }
}
