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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auction auction = (Auction) o;

        if (Double.compare(auction.id, id) != 0) return false;
        if (Double.compare(auction.minimumPrice, minimumPrice) != 0) return false;
        if (description != null ? !description.equals(auction.description) : auction.description != null) return false;
        if (expires != null ? !expires.equals(auction.expires) : auction.expires != null) return false;
        if (starts != null ? !starts.equals(auction.starts) : auction.starts != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = id != +0.0d ? Double.doubleToLongBits(id) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = minimumPrice != +0.0d ? Double.doubleToLongBits(minimumPrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (starts != null ? starts.hashCode() : 0);
        result = 31 * result + (expires != null ? expires.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "Auction{" +
                "id=" + id +
                ", minimumPrice=" + minimumPrice +
                ", description='" + description + '\'' +
                ", starts=" + starts +
                ", expires=" + expires +
                ", albums=" + albums +
                '}';
    }
}
