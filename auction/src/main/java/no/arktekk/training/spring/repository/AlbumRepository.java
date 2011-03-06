package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Album;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Transactional(readOnly = true)
public interface AlbumRepository {
    Album findById(double id);

    List<Album> listForAuction(double auctionId);

    @Transactional void storeForAuction(Long id, List<Album> albums);
}
