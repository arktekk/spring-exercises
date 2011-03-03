package no.arktekk.training.spring;

import no.arktekk.training.spring.config.AuctionContainer;
import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.service.AuctionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class DependencyInjectionTest {


    /**
     * Add neccecary methods to the configuration class,
     * and complete the createSpringContainer method in order to get this test to run.
     * <p/>
     * TIP: The repository implementation is:
     * no.arktekk.training.spring.repository.impl.StubAuctionRepository
     */
    @Test public void step_1() {
        ApplicationContext ctx = createSpringContainer();
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        assertNotNull(auctionRepository);
    }


    /**
     * Add neccecary methods to the configuration class in order to get this test to run.
     * <p/>
     * TIP: The repository implementation is:
     * no.arktekk.training.spring.service.impl.DefaultAuctionService
     * TIP: The service needs the repository in the constructor in order to work.
     */
    @Test public void step_2() {
        ApplicationContext ctx = createSpringContainer();
        AuctionService auctionService = lookupAuctionService(ctx);
        assertNotNull(auctionService);
    }

    /**
     * Just a nice little test to se that everything still works as expected.
     * <p/>
     * Extra: Notice that the lookupAuctionRepository has changed. What have happened, and why ?
     */
    @Test public void shouldPassWhenStep1AndStep2Completed() {
        ApplicationContext ctx = createSpringContainer();
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        AuctionService auctionService = lookupAuctionService(ctx);

        assertEquals(0, auctionService.allRunningAuctions().size());

        auctionRepository.createNewAuction(new Auction(1, "My first auction"));
        auctionRepository.createNewAuction(new Auction(2, "My second auction"));

        assertEquals(2, auctionService.allRunningAuctions().size());

        Auction auction = auctionService.findById(1D);
        assertEquals("My first auction", auction.description());
    }


    /**
     * Use the class no.arktekk.training.spring.config.AuctionContainer to build the container
     */
    private ApplicationContext createSpringContainer() {
        return new AnnotationConfigApplicationContext(AuctionContainer.class);
    }


    private AuctionRepository lookupAuctionRepository(ApplicationContext ctx) {
        return ctx.getBean(AuctionRepository.class);
    }

    private AuctionService lookupAuctionService(ApplicationContext ctx) {
        return ctx.getBean(AuctionService.class);
    }
}
