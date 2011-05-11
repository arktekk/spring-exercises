package no.arktekk.training.spring.repository.impl;

import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.mapper.AlbumMapper;
import no.arktekk.training.spring.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Repository
public class JdbcAlbumRepository implements AlbumRepository {
    private final JdbcTemplate template;
    private final AlbumMapper albumMapper;

    @Autowired
    public JdbcAlbumRepository(DataSource dataSource, JdbcCategoryRepository categoryRepository, JdbcLabelRepository labelRepository) {
        this.template = new JdbcTemplate(dataSource);
        this.albumMapper = new AlbumMapper(categoryRepository, labelRepository);
    }

    @Override
    public Album findById(double id) {
        return template.queryForObject("select * from Albums", albumMapper);
    }

    @Override
    public List<Album> listForAuction(double auctionId) {
        return template.query("select * from Albums where auctionId = ?", albumMapper, auctionId);
    }
}
