package no.arktekk.training.spring.mapper;

import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.repository.impl.JdbcCategoryRepository;
import no.arktekk.training.spring.repository.impl.JdbcLabelRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AlbumMapper implements RowMapper<Album> {
    private final JdbcCategoryRepository categoryRepository;
    private final JdbcLabelRepository labelRepository;

    public AlbumMapper(JdbcCategoryRepository categoryRepository, JdbcLabelRepository labelRepository) {
        this.categoryRepository = categoryRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public Album mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Album(
                resultSet.getString("id"),
                resultSet.getString("title"),
                resultSet.getString("artist"),
                categoryRepository.find(resultSet.getInt("categoryId")),
                labelRepository.find(resultSet.getInt("labelId"))
        );
    }
}
