package no.arktekk.training.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class FrontPageController {

    @RequestMapping("/")
    public String prepareFrontPage() {
        return "frontpage";
    }

}
