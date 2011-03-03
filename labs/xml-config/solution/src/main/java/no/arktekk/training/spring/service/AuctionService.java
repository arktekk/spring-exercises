package no.arktekk.training.spring.service;

import no.arktekk.training.spring.domain.Auction;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface AuctionService {
    List<Auction> allRunningAuctions();

    Auction findById(Double auctionId);
}
