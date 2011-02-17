package no.arktekk.training.spring.domain;

import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Tracks implements Iterable<Track> {
    List<Track> tracks;

    public Iterator<Track> iterator() {
        return tracks.iterator();
    }
}
