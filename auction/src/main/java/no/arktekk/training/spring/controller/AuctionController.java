package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.form.AuctionForm;
import no.arktekk.training.spring.service.AuctionService;
import no.arktekk.training.spring.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class AuctionController {
    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @RequestMapping("/auctions/{auctionId}")
    @View("auction/details")
    public Auction details(@PathVariable Double auctionId) {
        return auctionService.findById(auctionId);

    }

    @RequestMapping(value = "/auctions/new", method = GET)
    @View(value = "auction/new", modelAttribute = "auction")
    public AuctionForm prepareNewAuctionForm() {
        return new AuctionForm();
    }

    @RequestMapping(value = "/auctions/new", method = POST)
    @View("auction/new")
    public void submitNewAuction(@ModelAttribute("auction") AuctionForm auction) {
        System.out.println("auction = " + auction);

    }
}
