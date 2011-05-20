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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listAuctions() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAuction(@PathVariable String auctionId) {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createAuction(@RequestBody String auctionXml) {
		return null;
	}

}
