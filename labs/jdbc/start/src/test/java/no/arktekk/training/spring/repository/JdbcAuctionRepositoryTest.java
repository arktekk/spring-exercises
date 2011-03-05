package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Auction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcAuctionRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuctionRepository auctionRepository;


    /**
     * Refactor the findById method to use Spring-JDBC instead of
     * JDBC directly
     * <p/>
     * TIP: Do not forget rowmapper!
     *
     * @throws Exception
     */
    @Test public void step_1() throws Exception {
        Auction auction = auctionRepository.findById(1D);
        assertEquals("Mint prog rock albums", auction.description());
    }

    /**
     * Refactor the listAllRunningAuctions method to use Spring-JDBC instead of
     * JDBC directly
     *
     * @throws Exception
     */
    @Test public void step_2() throws Exception {
        List<Auction> auctions = auctionRepository.listAllRunningAuctions();
        assertEquals(2, auctions.size());
    }

}
