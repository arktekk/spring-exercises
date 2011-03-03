package no.arktekk.training.spring.config;

import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.repository.impl.StubAuctionRepository;
import no.arktekk.training.spring.service.AuctionService;
import no.arktekk.training.spring.service.impl.DefaultAuctionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Configuration
public class AuctionContainer {

    @Bean
    public AuctionRepository auctionRepository() {
        return new StubAuctionRepository();
    }

    @Bean
    public AuctionService auctionService() {
        return new DefaultAuctionService(auctionRepository());
    }


}
