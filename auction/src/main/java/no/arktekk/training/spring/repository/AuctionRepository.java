package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface AuctionRepository {
    List<Auction> listAllRunningAuctions();
}
