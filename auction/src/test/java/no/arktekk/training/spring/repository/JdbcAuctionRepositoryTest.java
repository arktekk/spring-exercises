package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.config.DatabaseConfig;
import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.impl.JdbcAuctionRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class JdbcAuctionRepositoryTest {
    DatabaseConfig config = new DatabaseConfig();

    @Before
    public void testAssumtions() {
        JdbcTemplate template = new JdbcTemplate(config.dataSource());
        assertEquals(3, template.queryForInt("select count(*) from auction"));
    }

    @Test
    public void listAllRunningActions() {
        AuctionRepository repository = new JdbcAuctionRepository(config.dataSource());
        List<Auction> auctions = repository.listAllRunningAuctions();
        assertEquals(2, auctions.size());
    }

    @After
    public void checkSideEffects() {
        JdbcTemplate template = new JdbcTemplate(config.dataSource());
        assertEquals(3, template.queryForInt("select count(*) from auction"));
    }
}
