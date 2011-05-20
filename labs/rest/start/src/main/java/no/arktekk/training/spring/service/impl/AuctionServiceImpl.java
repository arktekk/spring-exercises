package no.arktekk.training.spring.service.impl;

import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AlbumRepository;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Service
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository, AlbumRepository albumRepository) {
        this.auctionRepository = auctionRepository;
        this.albumRepository = albumRepository;
    }

    public List<Auction> allRunningAuctions() {
        return auctionRepository.listAllRunningAuctions();
    }

    public Auction findById(String auctionId) {
        Auction auction = auctionRepository.findById(auctionId);
        List<Album> albums = albumRepository.listForAuction(auction.id());
        for (Album album : albums) {
            auction.albums().add(album);
        }
        return auction;
    }

    public void newAuction(Auction auction) {
        auction.assignNewId();
        auctionRepository.newAuction(auction);
        albumRepository.storeForAuction(auction.id(), auction.albums());
    }
}
