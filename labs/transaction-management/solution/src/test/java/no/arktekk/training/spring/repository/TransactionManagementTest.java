package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.service.AuctionService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TransactionManagementTest {

    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    private AnnotationTransactionAttributeSource annotationTransactionAttributeSource;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private DataSource dataSource;


    /**
     * Register transaction manager, and add neccecary
     * transaction attributes in order to get this test to run
     */
    @Test public void step_1() throws Exception {

        int auctionsBeforeTest = auctionCount();

        auctionService.newAuction(new Auction(1D, 100D, "Mint Progrog Records", new DateTime(), new DateTime().plusDays(10)));

        assertEquals(auctionsBeforeTest + 1, auctionCount());
    }


    private int auctionCount() {
        return new JdbcTemplate(dataSource).queryForInt("select count(*) from Auctions");
    }

}
