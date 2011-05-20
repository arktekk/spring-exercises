package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Album;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Transactional(readOnly = true)
public interface AlbumRepository {
    Album findById(String id);

    List<Album> listForAuction(String auctionId);

    @Transactional void storeForAuction(String id, List<Album> albums);
}
