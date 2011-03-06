package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.form.AuctionForm;
import no.arktekk.training.spring.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static no.arktekk.training.spring.form.Transformations.asAuctionForm;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class IndexController {
    private final AuctionService auctionService;

    @Autowired
    public IndexController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public @ModelAttribute("auctions") List<AuctionForm> auctionList() {
        List<AuctionForm> forms = new ArrayList<AuctionForm>();

        // TIP: to get a auction object to become an auctionForm use the function defined here:
        // no.arktekk.training.spring.form.Transformations.asAuctionForm.apply(auction)

        return forms;

    }
}
