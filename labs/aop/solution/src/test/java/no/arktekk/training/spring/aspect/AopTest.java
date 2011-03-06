package no.arktekk.training.spring.aspect;

import no.arktekk.training.spring.aspect.RepositoryMonitor;
import no.arktekk.training.spring.monitor.Monitor;
import no.arktekk.training.spring.monitor.MonitorFactory;
import no.arktekk.training.spring.repository.AuctionRepository;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AopTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * Enable AOP support in applicationContext.xml
     */
    @Test public void step_1() throws Exception {
        assertNotNull(ctx.getBean(AnnotationAwareAspectJAutoProxyCreator.class));

    }

    /**
     * Add the aspect into the container
     */
    @Test public void step_2() throws Exception {
        assertNotNull(ctx.getBean(RepositoryMonitor.class));
    }

    /**
     * Make the RepositoryMontitor's monitor method
     * be an Around advice with a pointcut that matches
     * all public methods in the AuctionRepository
     */
    @Test public void step_3() throws Exception {
        MonitorFactory monitorFactory = ctx.getBean(MonitorFactory.class);
        AuctionRepository repository = ctx.getBean(AuctionRepository.class);

        Monitor findByIdMonitor = monitorFactory.monitor("no.arktekk.training.spring.repository.AuctionRepository.findById");

        repository.findById(1D);
        assertEquals(new Double(1D), findByIdMonitor.hits());

        repository.findById(2D);
        assertEquals(new Double(2D), findByIdMonitor.hits());
    }

}
