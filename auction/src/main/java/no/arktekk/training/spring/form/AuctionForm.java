package no.arktekk.training.spring.form;

import no.arktekk.training.spring.domain.Auction;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.format.annotation.NumberFormat.Style.CURRENCY;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AuctionForm implements Form<Auction> {

    @NotNull private Double id;

    @NumberFormat(style = CURRENCY)
    @NotNull private Double minimumPrice;

    private String description;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @NotNull private DateTime starts;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @NotNull private DateTime expires;

    private List<AlbumForm> albums = new ArrayList<AlbumForm>();


    /**
     * *********************************************************************
     * Getters and Setters section.. Unfortunatly needed by spring mvc :(  *
     * *********************************************************************
     */
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

    public DateTime getStarts() {
        return starts;
    }

    public void setStarts(DateTime starts) {
        this.starts = starts;
    }

    public DateTime getExpires() {
        return expires;
    }

    public void setExpires(DateTime expires) {
        this.expires = expires;
    }

    public List<AlbumForm> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumForm> albums) {
        this.albums = albums;
    }
}
