package no.arktekk.training.spring.controller;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.domain.AuctionList;
import no.arktekk.training.spring.service.AuctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestAuctionController {
	
	private final AuctionService auctionService;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;
	
	@Autowired
	public RestAuctionController(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/auctions")
	public ModelAndView listAuctions() {
		List<Auction> auctions = auctionService.allRunningAuctions();
		ModelAndView mav = new ModelAndView("jaxbMarshallerView");
		mav.addObject("auctions", new AuctionList(auctions));
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/auctions/{auctionId}")
	public ModelAndView getAuction(@PathVariable String auctionId) {
		Auction auction = auctionService.findById(auctionId);
		ModelAndView mav = new ModelAndView("jaxbMarshallerView");
		mav.addObject(auction);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/auctions")
	public ModelAndView createAuction(@RequestBody String auctionXml) {
		
		Source source = new StreamSource(new StringReader(auctionXml));
		Auction auction = (Auction) jaxb2Marshaller.unmarshal(source);
		auctionService.newAuction(auction);
		
		ModelAndView mav = new ModelAndView("jaxbMarshallerView");
		mav.addObject(auction);
		return mav;
	}

}
