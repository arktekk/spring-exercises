package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.config.DatabaseConfig;
import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.impl.JdbcAuctionRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class JdbcAuctionRepositoryTest {
    DatabaseConfig config = new DatabaseConfig();

    @Test
    public void listAllRunningActions() {
        AuctionRepository repository = new JdbcAuctionRepository(config.dataSource());
        List<Auction> auctions = repository.listAllRunningAuctions();
        Assert.assertEquals(2, auctions.size());
    }
}
