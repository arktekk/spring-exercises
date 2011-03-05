package no.arktekk.training.spring;

import no.arktekk.training.spring.no.arktekk.training.spring.config.TestDataPopulator;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.service.AuctionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class DependencyInjectionTest {


    /**
     * Register the AuctionService and AuctionRepository in applicationContext.xml
     * Use xml config this time with beans and references.
     * <p/>
     * TIP: The repository implementation is:
     * no.arktekk.training.spring.repository.impl.StubAuctionRepository
     * The service implementation is:
     * no.arktekk.training.spring.service.impl.DefaultAuctionService
     */
    @Test public void step_1() {
        ApplicationContext ctx = createSpringContainer();
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        AuctionService auctionService = lookupAuctionService(ctx);
        assertNotNull(auctionRepository);
        assertNotNull(auctionService);
    }

    /**
     * Register the TestDataPopulator in the container.
     * Use xml config to register it
     * <p/>
     * TIP: the populator implementation is:
     * no.arktekk.training.spring.config.TestDataPopulator
     */
    @Test public void step_2() {
        ApplicationContext ctx = createSpringContainer();
        TestDataPopulator populator = lookupPopulator(ctx);
        assertNotNull(populator);
    }

    /**
     * Use one of the supported methods of lifecycle initialization callbacks
     * so that the method createTestData() in TestDataPopulator is executed when
     * Spring is starting up the container
     * <p/>
     * NOTE: Feel free to experiment with the different methods of configuring a initialization callback.
     */
    @Test public void step_3() {
        ApplicationContext ctx = createSpringContainer();
        AuctionService auctionService = lookupAuctionService(ctx);
        assertEquals(2, auctionService.allRunningAuctions().size());
    }

    /**
     * Register a property-placeholder using the property file "auction.properties"
     * inject the value of the key "useExtendedTestData" into the TestDataPopulator.
     * The populator should now indicate that it provides extra testdata.
     * <p/>
     * NOTE: It does not actually create more test data in this lab, since then step_3 would break ;)
     */
    @Test public void step_4() {
        ApplicationContext ctx = createSpringContainer();
        AuctionService auctionService = lookupAuctionService(ctx);
        assertEquals(2, auctionService.allRunningAuctions().size());
        TestDataPopulator populator = lookupPopulator(ctx);
        assertEquals(true, populator.isUseExtendedTestDataSet());
    }

    private ApplicationContext createSpringContainer() {
        return new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    private AuctionRepository lookupAuctionRepository(ApplicationContext ctx) {
        return ctx.getBean(AuctionRepository.class);
    }

    private AuctionService lookupAuctionService(ApplicationContext ctx) {
        return ctx.getBean(AuctionService.class);
    }

    private TestDataPopulator lookupPopulator(ApplicationContext ctx) {
        return ctx.getBean(TestDataPopulator.class);
    }
}
