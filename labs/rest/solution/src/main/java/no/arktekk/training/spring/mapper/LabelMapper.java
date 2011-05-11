package no.arktekk.training.spring.mapper;


import no.arktekk.training.spring.domain.Label;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class LabelMapper implements RowMapper<Label> {

    @Override
    public Label mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Label(resultSet.getInt("id"), resultSet.getString("name"));
    }
}
