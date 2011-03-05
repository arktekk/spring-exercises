package no.arktekk.training.spring.mapper;

import no.arktekk.training.spring.domain.Album;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class DetailedAlbumMapper implements ResultSetExtractor<Album> {
    private final SimpleAlbumMapper simpleAlbumMapper;

    public DetailedAlbumMapper(SimpleAlbumMapper simpleAlbumMapper) {
        this.simpleAlbumMapper = simpleAlbumMapper;
    }

    @Override
    public Album extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Album album = null;
        while (resultSet.next()) {
            if (album == null) {
                album = simpleAlbumMapper.mapRow(resultSet, 0);
            }
            album.addTrack(new TrackMapper().mapRow(resultSet, 0));
        }
        return album;
    }
}
