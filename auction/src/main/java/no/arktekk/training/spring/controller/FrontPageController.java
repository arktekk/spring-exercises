package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String auctionList(ModelMap model) {
        model.addAttribute("auctions", auctionService.allRunningAuctions());
        return "frontpage";
    }

}
