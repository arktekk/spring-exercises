package no.arktekk.training.spring.config;

import org.junit.Test;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class DatabaseConfigTest {

    @Test
    public void whenUsingTestDataSourceThenInmemoryDatabaseAreCreatedAndPopulatedWithTestDataOnce() {
        DatabaseConfig config = new DatabaseConfig();
        DataSource dataSource = config.dataSource();
        SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
        assertEquals("Expected one Auction after populating testdata", 1, template.queryForInt("select count(*) from Auction"));
        config.dataSource();
        assertEquals("Expected one Auction after populating testdata", 1, template.queryForInt("select count(*) from Auction"));
    }
}
