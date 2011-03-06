package no.arktekk.training.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Main container for the Auction application.
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        return new TestDataSourceFactoryBean().getObject();
    }
}
