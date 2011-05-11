package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static no.arktekk.training.spring.form.Transformations.asAuctionForm;

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

    @RequestMapping("/auctions/{auctionId}.html")
    public String showDetails(@PathVariable String auctionId, ModelMap model) {
        model.addAttribute("auction", asAuctionForm.apply(auctionService.findById(auctionId)));
        return "auction/details";
    }
    
}
