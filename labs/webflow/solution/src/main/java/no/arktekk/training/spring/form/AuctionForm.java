package no.arktekk.training.spring.form;

import no.arktekk.training.spring.domain.Auction;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AuctionForm implements Form<Auction> {

    private Double id;

    private Double minimumPrice;

    private String description;

    private DateTime startDate;

    private DateTime expiresDate;

    private List<AlbumForm> albums = new ArrayList<AlbumForm>();



    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(DateTime expiresDate) {
        this.expiresDate = expiresDate;
    }

    public List<AlbumForm> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumForm> albums) {
        this.albums = albums;
    }
}
