package no.arktekk.training.spring.domain;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Auction {
    private final double id;
    private final String description;

    public Auction(double id, String description) {
        this.id = id;
        this.description = description;
    }

    public Double id() {
        return id;
    }

    public String description() {
        return description;
    }
}
