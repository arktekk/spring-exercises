package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Auction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Transactional(readOnly = true)
public interface AuctionRepository {
    List<Auction> listAllRunningAuctions();

    Auction findById(String auctionId);

    @Transactional void newAuction(Auction auction);
}
