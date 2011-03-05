package no.arktekk.training.spring.mapper;

import no.arktekk.training.spring.domain.Duration;
import no.arktekk.training.spring.domain.Track;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class TrackMapper implements RowMapper<Track> {
    @Override
    public Track mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return new Track(
                resultSet.getDouble("id"),
                resultSet.getString("Name"),
                resultSet.getInt("number"),
                new Duration(resultSet.getInt("durationMinutes"), resultSet.getInt("durationSeconds")),
                resultSet.getBoolean("live"),
                resultSet.getString("Note")
        );
    }
}
