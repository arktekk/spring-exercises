package no.arktekk.training.spring.controller;

import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.domain.Label;
import no.arktekk.training.spring.form.AlbumForm;
import no.arktekk.training.spring.form.AuctionForm;
import no.arktekk.training.spring.form.TrackForm;
import no.arktekk.training.spring.repository.impl.JdbcCategoryRepository;
import no.arktekk.training.spring.repository.impl.JdbcLabelRepository;
import no.arktekk.training.spring.service.AuctionService;
import no.arktekk.training.spring.util.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

import static no.arktekk.training.spring.form.Transformations.asAuctionForm;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
@SessionAttributes("newAuction")
public class AuctionController {
    private final AuctionService auctionService;
    private final JdbcCategoryRepository categoryRepository;
    private final JdbcLabelRepository labelRepository;

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
    public String createAuction(@Valid @ModelAttribute("newAuction") AuctionForm auction, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auction/new";
        } else {
            return "forward:/";
        }
    }

    @RequestMapping(value = "/forms/auction/album", method = POST)
    @View("auction/new")
    public void addAlbum(@ModelAttribute("newAuction") AuctionForm auction,
                         @ModelAttribute("newAlbum") AlbumForm album) {
        auction.getAlbums().add(album);
    }

    @RequestMapping(value = "/forms/auction/album/track", method = POST)
    @View("auction/new")
    public void addTrack(@ModelAttribute("newAlbum") AlbumForm album,
                         @ModelAttribute("newTrack") TrackForm track) {
        album().addTrack(track);
    }

    @ModelAttribute("newAuction")
    public AuctionForm auction() {
        return new AuctionForm();
    }

    @ModelAttribute("newAlbum")
    public AlbumForm album() {
        return new AlbumForm();
    }

    @ModelAttribute("newTrack")
    public TrackForm track() {
        return new TrackForm();
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
