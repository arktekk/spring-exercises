package no.arktekk.training.spring.form;

import no.arktekk.training.spring.domain.Auction;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AuctionForm implements Form<Auction> {

    private String id;

    @NumberFormat(style = NUMBER)
    @NotNull private Double minimumPrice;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @NotNull private DateTime startDate;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @NotNull private DateTime expiresDate;

    @NotEmpty private List<AlbumForm> albums = new ArrayList<AlbumForm>();

    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
