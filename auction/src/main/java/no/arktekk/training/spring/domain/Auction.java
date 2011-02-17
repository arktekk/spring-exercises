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
    private final DateTime start;
    private final DateTime expires;

    public Auction(double id, double minimumPrice, String description, DateTime start, DateTime expires) {
        this.id = id;
        this.minimumPrice = minimumPrice;
        this.description = description;
        this.start = start;
        this.expires = expires;
    }

    public double getId() {
        return id;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getExpires() {
        return expires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auction auction = (Auction) o;

        if (Double.compare(auction.id, id) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = id != +0.0d ? Double.doubleToLongBits(id) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", minimumPrice=" + minimumPrice +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", expires=" + expires +
                '}';
    }
}
