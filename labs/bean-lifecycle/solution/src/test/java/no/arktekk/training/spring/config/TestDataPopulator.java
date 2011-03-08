package no.arktekk.training.spring.config;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;

import javax.annotation.PostConstruct;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class TestDataPopulator {
    private final AuctionRepository auctionRepository;
    private boolean useExtendedTestDataSet = false;

    public TestDataPopulator(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public void setUseExtendedTestDataSet(boolean useExtendedTestDataSet) {
        this.useExtendedTestDataSet = useExtendedTestDataSet;
    }

    public boolean isUseExtendedTestDataSet() {
        return useExtendedTestDataSet;
    }

    @PostConstruct
    public void createTestData() {
        auctionRepository.createNewAuction(new Auction(1, "My first auction"));
        auctionRepository.createNewAuction(new Auction(2, "My second auction"));
    }

}
