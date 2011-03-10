package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.form.AuctionForm;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.List;
import java.util.Map;

import static java.util.Locale.US;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class MvcTest {
    ApplicationContext applicationCtx = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
    ApplicationContext webappCtx = new FileSystemXmlApplicationContext(new String[]{"/src/main/webapp/WEB-INF/auction-servlet.xml"}, applicationCtx);

    /**
     * in the auction-servlet.xml add the Index controller as bean
     * either with xml or with component scanning. If you
     * Component scan, this test will check for duplicates.
     */
    @Test public void step_1() {
        assertNotNull(webappCtx.getBean(IndexController.class));
        assertFalse(applicationCtx.containsBean("indexController"));
        for (String beanDefinedInWebCtx : webappCtx.getBeanDefinitionNames()) {
            if (!stdSpringBeanNames(beanDefinedInWebCtx))
                assertFalse("Found double registered bean : " + beanDefinedInWebCtx, applicationCtx.containsBean(beanDefinedInWebCtx));
        }
        for (String beanDefinedInApplicationCtx : applicationCtx.getBeanDefinitionNames()) {
            if (!stdSpringBeanNames(beanDefinedInApplicationCtx))
                assertFalse("Found double registered bean : " + beanDefinedInApplicationCtx, webappCtx.containsBeanDefinition(beanDefinedInApplicationCtx));
        }
    }

    /**
     * Implemement the missing method body in IndexController
     */
    @Test public void step_2() {
        IndexController controller = webappCtx.getBean(IndexController.class);
        List<AuctionForm> auctionForms = controller.auctionList();
        assertEquals(2, auctionForms.size());
    }

    /**
     * Register the AuctionController in auction-servlet.xml
     */
    @Test public void step_3() {
        assertNotNull(webappCtx.getBean(AuctionController.class));
    }

    /**
     * Implement the missing method body in AuctionController
     */
    @Test public void step_4() {
        AuctionController controller = webappCtx.getBean(AuctionController.class);
        ModelMap model = new ModelMap();
        controller.showDetails(1D, model);
        assertNotNull(model.get("auction"));
        assertEquals(AuctionForm.class,model.get("auction").getClass());
    }

    /**
     * Enable annotation based mapping for Spring MVC
     */
    @Test public void step_5() {
        assertNotNull(webappCtx.getBean(DefaultAnnotationHandlerMapping.class));
    }

    /**
     * Configure prefix and suffix for the viewresolver
     */
    @Test public void step_6() throws Exception {
        UrlBasedViewResolver viewResolver = webappCtx.getBean(UrlBasedViewResolver.class);
        JstlView view = (JstlView) viewResolver.resolveViewName("test", US);
        assertEquals("/WEB-INF/views/test.jspx", view.getUrl());
    }

    /**
     * Configure a message source that looks up properties from labels.properties
     * <p/>
     * TIP: register, and configure a ResourceBundleMessageSource
     */
    @Test public void step_7() {
        MessageSource messageSource = webappCtx.getBean(MessageSource.class);
        assertEquals("Lagre", messageSource.getMessage("form.store", new Object[0], US));
    }

    /**
     * Annotate the IndexController to respond to "/index.html"
     * <p/>
     * TIP: use the RequestMapping annotation
     */
    @Test public void step_8() {
        DefaultAnnotationHandlerMapping handlerMapping = webappCtx.getBean(DefaultAnnotationHandlerMapping.class);
        Map<String, Object> handlerMap = handlerMapping.getHandlerMap();
        assertTrue(handlerMap.containsKey("/index.html"));
        assertEquals(IndexController.class, handlerMap.get("/index.html").getClass());
    }

    /**
     * Annotate the AuctionController to respond to "/auctions/{auctionId}.html"
     */
    @Test public void step_9() {
        DefaultAnnotationHandlerMapping handlerMapping = webappCtx.getBean(DefaultAnnotationHandlerMapping.class);
        Map<String, Object> handlerMap = handlerMapping.getHandlerMap();
        assertTrue(handlerMap.containsKey("/auctions/{auctionId}.html"));
        assertEquals(AuctionController.class, handlerMap.get("/auctions/{auctionId}.html").getClass());
    }

    /**
     * Finally, configure the web.xml and configure:
     * A DispatcherServlet with the name auction  (to load the auction-servlet.xml)
     * A ContextLoaderListener                    (to load the applicationContext.xml)
     * A RequestContextListener                   (to enable session and request attributes)
     * A servletmapping from the auction servlet to "*.html"
     * <p/>
     * Start the webapplication by running the main method in AuctionApp
     * navigate to http://localhost:8080/
     * Look around, and inspect how the views work. Also inspect the jspx files
     * Then when everything is running nice, remove the fail() method call belov :)
     */
    @Test public void step_10() {
        fail();
    }


    private boolean stdSpringBeanNames(String beanDefinedInWebCtx) {
        return beanDefinedInWebCtx.startsWith("org.springframework") || beanDefinedInWebCtx.equals("messageSource");
    }
}