package no.arktekk.training.spring.mapper;

import no.arktekk.training.spring.domain.Auction;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AuctionMapper implements RowMapper<Auction> {

    public Auction mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return new Auction(resultSet.getDouble("id"),
                resultSet.getDouble("minimumPrice"),
                resultSet.getString("description"),
                new DateTime(resultSet.getDate("starts")),
                new DateTime(resultSet.getDate("expires")));
    }
}
