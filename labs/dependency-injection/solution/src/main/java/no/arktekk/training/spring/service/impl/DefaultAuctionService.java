package no.arktekk.training.spring.service.impl;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.service.AuctionService;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class DefaultAuctionService implements AuctionService {
    private final AuctionRepository auctionRepository;

    public DefaultAuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }


    public List<Auction> allRunningAuctions() {
        return auctionRepository.listAllRunningAuctions();
    }

    public Auction findById(Double auctionId) {
        return auctionRepository.findById(auctionId);
    }
}
