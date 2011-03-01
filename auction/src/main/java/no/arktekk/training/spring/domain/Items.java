package no.arktekk.training.spring.domain;

import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Items implements Iterable<Item> {
    Auction auction;
    List<Item> items;

    private Items() {
    }

    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
