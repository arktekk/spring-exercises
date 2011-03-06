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
public class AuctionController {
    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public String showDetails(@PathVariable Double auctionId, ModelMap model) {
        // TIP: to get a auction object to become an auctionForm use the function defined here:
        // no.arktekk.training.spring.form.Transformations.asAuctionForm.apply(auction)
        return "auction/details";
    }
}
