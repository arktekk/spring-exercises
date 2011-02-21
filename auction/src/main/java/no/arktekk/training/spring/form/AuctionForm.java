package no.arktekk.training.spring.form;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AuctionForm {
    Double minimumPrice;

    public Double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    @Override
    public String toString() {
        return "AuctionForm{" +
                "minimumPrice=" + minimumPrice +
                '}';
    }
}
