package no.arktekk.training.spring.form;

import no.arktekk.training.spring.domain.*;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class ItemForm implements Form<Item> {
    private Category category;
    private Artist artist;
    private Composer composer;
    private Label label;
    private Tracks tracks;


    @Override public Item to() {
        return null;
    }

    @Override public AuctionForm apply(Item item) {
        return null;
    }
}
