package no.arktekk.training.spring.domain;

import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Items implements Iterable<Album> {
    Auction auction;
    List<Album> albums;

    private Items() {
    }

    public Iterator<Album> iterator() {
        return albums.iterator();
    }
}
