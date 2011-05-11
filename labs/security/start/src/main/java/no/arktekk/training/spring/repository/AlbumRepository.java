package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Album;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface AlbumRepository {
    Album findById(double id);

    List<Album> listForAuction(double auctionId);
}
