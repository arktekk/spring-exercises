package no.arktekk.training.spring.config;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static no.arktekk.training.spring.util.DatabaseUtils.no_NO;
import static no.arktekk.training.spring.util.DatabaseUtils.timeStampFormatter;

/**
 * Will by default, if registered as a bean in the container, create neccecary
 * tables, then populate them with a small set of test data.
 * <p/>
 * <p/>
 * To prevent it to populate test data, set the createTestData property to false
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Component
public class TestDataPopulator {
    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;
    private boolean testDataInitialized = false;

    @Autowired
    public TestDataPopulator(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @PostConstruct
    public void generateDatabaseIfEmpty() {
        if (databaseEmpty()) {
            createTables();
            if (!testDataInitialized) {
                insertTestData();
            }
        }
    }

    private boolean databaseEmpty() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return !connection.getMetaData().getTables(null, null, "AUCTIONS", null).next();
        } catch (SQLException e) {
            throw new RuntimeException("Could not get table metadata", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Do nothing :)
            }
        }

    }

    private void createTables() {
        template.update("CREATE TABLE Auctions(id DOUBLE PRIMARY KEY, minimumPrice DOUBLE, starts TIMESTAMP, expires TIMESTAMP, description VARCHAR)");
    }

    private void insertTestData() {
        insertAuctions();
        testDataInitialized = true;
    }

    private void insertAuctions() {
        DateTime now = new DateTime();
        String starts = timeStampFormatter.print(now.minusDays(10).toDate(), no_NO);
        String ends = timeStampFormatter.print(now.plusDays(10).toDate(), no_NO);

        template.update("insert into Auctions values(1,1200,'" + starts + "','" + ends + "','Mint prog rock albums')");
        template.update("insert into Auctions values(2,40,'" + starts + "','" + ends + "','Boring pop records')");
        template.update("insert into Auctions values(3,40,'" + starts + "','" + starts + "','Expired auction')");
    }
}
