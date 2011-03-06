package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.domain.Label;
import no.arktekk.training.spring.form.AlbumForm;
import no.arktekk.training.spring.form.AuctionForm;
import no.arktekk.training.spring.repository.impl.JdbcCategoryRepository;
import no.arktekk.training.spring.repository.impl.JdbcLabelRepository;
import no.arktekk.training.spring.service.AuctionService;
import no.arktekk.training.spring.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

import static no.arktekk.training.spring.form.Transformations.asAuction;
import static no.arktekk.training.spring.form.Transformations.asAuctionForm;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.servlet.view.UrlBasedViewResolver.REDIRECT_URL_PREFIX;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
@SessionAttributes(AuctionController.AUCTION_FORM_KEY)
public class AuctionController {
    private final AuctionService auctionService;
    private final JdbcCategoryRepository categoryRepository;
    private final JdbcLabelRepository labelRepository;

    public final static String AUCTION_FORM_KEY = "newAuction";
    public final static String ALBUM_FORM_KEY = "newAlbum";

    @Autowired
    public AuctionController(AuctionService auctionService,
                             JdbcCategoryRepository categoryRepository,
                             JdbcLabelRepository labelRepository) {
        this.auctionService = auctionService;
        this.categoryRepository = categoryRepository;
        this.labelRepository = labelRepository;
    }

    @RequestMapping("/auctions/{auctionId}")
    @View(value = "auction/details", modelAttribute = "auction")
    public AuctionForm showDetails(@PathVariable Double auctionId) {
        return asAuctionForm.apply(auctionService.findById(auctionId));

    }

    @RequestMapping("/forms/auction")
    public String prepareForm() {
        return "/auction/new";
    }

    @RequestMapping(value = "/forms/auction", method = POST)
    public String createAuction(@Valid @ModelAttribute(AUCTION_FORM_KEY) AuctionForm auction, BindingResult bindingResult, SessionStatus status) {
        if (bindingResult.hasErrors()) {
            return "auction/new";
        } else {
            auctionService.newAuction(asAuction.apply(auction));
            status.setComplete();
            return REDIRECT_URL_PREFIX + "/";
        }
    }

    @RequestMapping(value = "/forms/auction/album", method = POST)
    public String addAlbum(@ModelAttribute(AUCTION_FORM_KEY) AuctionForm auction,
                           @Valid @ModelAttribute(ALBUM_FORM_KEY) AlbumForm album,
                           BindingResult bindingResult,
                           ModelMap model) {
        if (!bindingResult.hasErrors()) {
            auction.getAlbums().add(album);
            model.addAttribute(AUCTION_FORM_KEY, auction);
            return REDIRECT_URL_PREFIX + "/forms/auction";
        } else {
            return "/auction/new";
        }
    }

    @ModelAttribute(AUCTION_FORM_KEY)
    public AuctionForm auctionForm() {
        return new AuctionForm();
    }

    @ModelAttribute(ALBUM_FORM_KEY)
    public AlbumForm album() {
        return new AlbumForm();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.list();
    }

    @ModelAttribute("labels")
    public List<Label> labels() {
        return labelRepository.list();
    }
}
