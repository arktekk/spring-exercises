package no.arktekk.training.spring.service;

import no.arktekk.training.spring.domain.Auction;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface AuctionService {
    void newAuction(Auction auction);
}
