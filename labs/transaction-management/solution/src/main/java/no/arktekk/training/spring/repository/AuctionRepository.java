package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Auction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Transactional(readOnly = true, propagation = Propagation.MANDATORY)
public interface AuctionRepository {

    List<Auction> listAllRunningAuctions();

    Auction findById(Double auctionId);

    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
    void store(Auction auction);
}
