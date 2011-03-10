package no.arktekk.training.spring.service;

import no.arktekk.training.spring.domain.Auction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Transactional(readOnly = true)
public interface AuctionService {
    List<Auction> allRunningAuctions();

    Auction findById(String auctionId);

    @Transactional void newAuction(Auction auction);
}
