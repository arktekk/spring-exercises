package no.arktekk.training.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static no.arktekk.training.spring.util.DatabaseUtils.no_NO;
import static no.arktekk.training.spring.util.DatabaseUtils.timeStampFormatter;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = createDataSource();
        if (databaseEmpty(dataSource)) {
            createDatabase(dataSource);
            insertTestData(dataSource);
        }
        return dataSource;
    }

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:auction");
        dataSource.setUsername("sa");
        return dataSource;
    }

    private boolean databaseEmpty(BasicDataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return !connection.getMetaData().getTables(null, null, "AUCTION", null).next();
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

    private void createDatabase(BasicDataSource dataSource) {
        SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
        template.update("CREATE TABLE Auction(id DOUBLE PRIMARY KEY, minimumPrice DOUBLE, starts TIMESTAMP, expires TIMESTAMP, description VARCHAR)");
        template.update("CREATE TABLE Category(ID INT PRIMARY KEY, NAME VARCHAR)");
    }

    private void insertTestData(BasicDataSource dataSource) {
        SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
        insertAuctions(template);
        insertCategories(template);
    }

    private void insertAuctions(SimpleJdbcTemplate template) {
        DateTime now = new DateTime();
        String starts = timeStampFormatter.print(now.minusDays(10).toDate(), no_NO);
        String ends = timeStampFormatter.print(now.plusDays(10).toDate(), no_NO);

        template.update("insert into Auction values(1,1200,'" + starts + "','" + ends + "','My favourite progrock records')");
        template.update("insert into Auction values(2,40,'" + starts + "','" + ends + "','Boring pop records')");
        template.update("insert into Auction values(3,40,'" + starts + "','" + starts + "','Expired auction')");
    }

    private void insertCategories(SimpleJdbcTemplate template) {
        template.update("insert into Category values(1,'Progrock')");
        template.update("insert into Category values(2,'Pop')");
        template.update("insert into Category values(3,'Rock')");
        template.update("insert into Category values(4,'Classical')");
        template.update("insert into Category values(5,'Jazz')");
        template.update("insert into Category values(6,'Punk')");
        template.update("insert into Category values(7,'Heavy Metal')");
    }
}
