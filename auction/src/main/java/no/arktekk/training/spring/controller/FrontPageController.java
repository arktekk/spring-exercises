package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.service.AuctionService;
import no.arktekk.training.spring.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class FrontPageController {
    private final AuctionService auctionService;

    @Autowired
    public FrontPageController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @RequestMapping("/")
    @View(value = "frontpage", modelAttribute = "auctions")
    public List<Auction> auctionList() {
        return auctionService.allRunningAuctions();
    }
}
