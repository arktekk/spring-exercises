package no.arktekk.training.spring.mapper;


import no.arktekk.training.spring.domain.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Category(resultSet.getInt("id"), resultSet.getString("name"));
    }
}
