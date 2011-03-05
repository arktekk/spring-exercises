package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.config.DatabaseConfig;
import no.arktekk.training.spring.config.TestDataPopulator;
import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.repository.impl.JdbcAlbumRepository;
import no.arktekk.training.spring.repository.impl.JdbcCategoryRepository;
import no.arktekk.training.spring.repository.impl.JdbcLabelRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class JdbcAlbumRepositoryTest {

    DatabaseConfig config = new DatabaseConfig();

    @Before
    public void testAssumtions() throws Exception {
        DataSource dataSource = config.dataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        new TestDataPopulator(dataSource).generateDatabaseIfEmpty();
        assertEquals(3, template.queryForInt("select count(*) from Albums"));
        assertEquals(3, template.queryForInt("select count(*) from Auctions"));
    }

    @Test
    public void loadAlbumsForAuction() throws Exception {
        DataSource dataSource = config.dataSource();
        JdbcAlbumRepository repository = new JdbcAlbumRepository(dataSource, new JdbcCategoryRepository(dataSource), new JdbcLabelRepository(dataSource));
        List<Album> albums = repository.listForAuction(1);
        assertEquals(2, albums.size());
    }

    @Test
    public void findById() throws Exception {
        DataSource dataSource = config.dataSource();
        JdbcAlbumRepository repository = new JdbcAlbumRepository(dataSource, new JdbcCategoryRepository(dataSource), new JdbcLabelRepository(dataSource));
        Album album = repository.findById(1);
        assertEquals(4, album.tracks().size());
        assertEquals("Cross Eyed Mary", album.tracks().get(1).name());
        assertEquals("Jethro Tull", album.artist());
        assertEquals("Aqualung", album.title());
    }
}
