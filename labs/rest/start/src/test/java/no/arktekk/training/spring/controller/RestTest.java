package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.domain.AuctionList;
import no.arktekk.training.spring.form.AuctionForm;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.repository.impl.JdbcAuctionRepository;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import static java.util.Locale.US;
import static org.junit.Assert.*;

public class RestTest {
	ApplicationContext applicationCtx = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
	ApplicationContext webappCtx = new FileSystemXmlApplicationContext(new String[] { "/src/main/webapp/WEB-INF/rest-servlet.xml" },
			applicationCtx);
	
	/**
	 * TODO: In the rest-servlet.xml add the RestAuction controller as bean
	 * either with xml or with component scanning. If you Component scan, this
	 * test will check for duplicates.
	 */
	@Test
	public void step_1() {
		assertNotNull(webappCtx.getBean(RestAuctionController.class));
		assertFalse(applicationCtx.containsBean("restAuctionController"));
		for (String beanDefinedInWebCtx : webappCtx.getBeanDefinitionNames()) {
			if (!stdSpringBeanNames(beanDefinedInWebCtx))
				assertFalse("Found double registered bean : " + beanDefinedInWebCtx, applicationCtx.containsBean(beanDefinedInWebCtx));
		}
		for (String beanDefinedInApplicationCtx : applicationCtx
				.getBeanDefinitionNames()) {
			if (!stdSpringBeanNames(beanDefinedInApplicationCtx))
				assertFalse("Found double registered bean : " + beanDefinedInApplicationCtx, webappCtx .containsBeanDefinition(beanDefinedInApplicationCtx));
		}
	}

	/**
	 * TODO: Wire up a bean of type org.springframework.web.servlet.view.xml.MarshallingView
	 * in rest-servlet.xml. Give it the id 'jaxbMarshallerView'.
	 * This needs to be constructor injected with a jaxbMarshaller - one already exists
	 * in applicationContext.xml and is called 'jaxb2Marshaller'. Inspect this configuration,
	 * and also inspect the Jaxb annotated classes Auction and AuctionList.
	 */
	@Test
	public void step_1b() {
		assertNotNull(webappCtx.getBean("jaxbMarshallerView"));
		assertTrue(webappCtx.getBean("jaxbMarshallerView") instanceof MarshallingView);
	}
	
	/**
	 * TODO: Implement the listAuctions method in RestAuctionController. It
	 * should ask the service for a list of Auctions and then populate an
	 * AuctionList object. The view name used should be 'jaxbMarshallerView'.
	 */
	@Test
	public void step_2() {
		RestAuctionController controller = webappCtx.getBean(RestAuctionController.class);
		ModelAndView modelAndView = controller.listAuctions();
		assertEquals("jaxbMarshallerView", modelAndView.getViewName());
		assertEquals(1, modelAndView.getModel().size());
		String key = modelAndView.getModel().keySet().iterator().next();
		assertTrue(modelAndView.getModel().get(key) instanceof AuctionList);
	}

	/**
	 * TODO: Annotate the RestAuctionController.listAuctions to respond to
	 * "/auctions"
	 */
	@Test
	public void step_3() {
		DefaultAnnotationHandlerMapping handlerMapping = webappCtx.getBean(DefaultAnnotationHandlerMapping.class);
		Map<String, Object> handlerMap = handlerMapping.getHandlerMap();
		assertTrue(handlerMap.containsKey("/auctions"));
		assertEquals(RestAuctionController.class, handlerMap.get("/auctions").getClass());
	}

	/**
	 * TODO: Implement the getAuction method in RestAuctionController. It should
	 * ask the service an Auction based on the auctionId parameter and then put
	 * the recieved Auction object in the Model. The view name used should be
	 * 'jaxbMarshallerView'.
	 */
	@Test
	public void step_4() {
		RestAuctionController controller = webappCtx
				.getBean(RestAuctionController.class);
		ModelAndView modelAndView = controller.getAuction("1");
		assertEquals("jaxbMarshallerView", modelAndView.getViewName());
		assertEquals(1, modelAndView.getModel().size());
		String key = modelAndView.getModel().keySet().iterator().next();
		assertTrue(modelAndView.getModel().get(key) instanceof Auction);
	}

	/**
	 * Annotate the RestAuctionController.getAuction to respond to
	 * "/auctions/{auctionId}"
	 */
	@Test
	public void step_5() {
		DefaultAnnotationHandlerMapping handlerMapping = webappCtx.getBean(DefaultAnnotationHandlerMapping.class);
		Map<String, Object> handlerMap = handlerMapping.getHandlerMap();
		assertTrue(handlerMap.containsKey("/auctions/{auctionId}"));
		assertEquals(RestAuctionController.class,handlerMap.get("/auctions/{auctionId}").getClass());
	}

	/**
	 * TODO: Implement the createAuction method in RestAuctionController. It
	 * should call the service to create a new Auction based on the auctionId
	 * parameter and then put the received Auction object in the Model. The view
	 * name used should be 'jaxbMarshallerView'.
	 * 
	 * Hint you will need to unmarshall the recieved xml: This code might come in handy:
	 * 
	 * Source source = new StreamSource(new StringReader(auctionXml));
	 * Auction auction = (Auction) jaxb2Marshaller.unmarshal(source);
	 * 
	 * Note that you need to dependency-inject the marshaller 'jaxb2Marshaller'.
	 */
	@Test
	public void step_7() {
		RestAuctionController controller = webappCtx.getBean(RestAuctionController.class);
		AuctionRepository repo = applicationCtx.getBean(AuctionRepository.class);
		int noOfAuctionBeforeCreate = repo.listAllRunningAuctions().size();
		
		String xml = "<auction>" +
				"<minimumPrice>1200.0</minimumPrice>" +
				"<description>Mint prog rock albums</description>" +
				"<expiresDateTime>2020-05-13 12:00:00</expiresDateTime>" +
				"<startDateTime>2011-04-23 12:00:00</startDateTime>" +
				"<albums/>" +
				"</auction>";
		ModelAndView modelAndView = controller.createAuction(xml);
		assertEquals("jaxbMarshallerView", modelAndView.getViewName());
		assertEquals(1, modelAndView.getModel().size());
		String key = modelAndView.getModel().keySet().iterator().next();
		assertTrue(modelAndView.getModel().get(key) instanceof Auction);
		
		assertEquals(noOfAuctionBeforeCreate + 1, repo.listAllRunningAuctions().size());
	}

	/**
	 * Finally, view the web.xml and uncomment configuration for a new
	 * Dispatcher servlet called 'rest'.
	 * 
	 * Start the webapplication by running the main method in AuctionApp
	 * and try the different GET urls:
	 * http://localhost:8080/restapi/auctions
	 * http://localhost:8080/restapi/auctions/1
	 * 
     * In order to test POST-ing (creating auctions) you need to use a ReST client.
     * The instructor will assist in installing an appropriate plugin to Chrome if needed.
     * A good one for Chrome is RESTConsole
     * For Firefox Poster works fine
     * For IE.. well you are on you own :)
	 * 
	 * When everything is running nice,
	 * remove the fail() method call belov :)
	 */
	@Test
	public void step_8() {
		fail();
	}

	private boolean stdSpringBeanNames(String beanDefinedInWebCtx) {
		return beanDefinedInWebCtx.startsWith("org.springframework")
				|| beanDefinedInWebCtx.equals("messageSource");
	}
}