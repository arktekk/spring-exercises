package no.arktekk.training.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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
        template.update("CREATE TABLE Auction(ID INT PRIMARY KEY, NAME VARCHAR)");
    }

    private void insertTestData(BasicDataSource dataSource) {
        SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
        template.update("insert into Auction values(1,'Aqualong')");
    }
}
