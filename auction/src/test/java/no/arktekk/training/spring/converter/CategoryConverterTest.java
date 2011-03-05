package no.arktekk.training.spring.converter;

import no.arktekk.training.spring.config.DatabaseConfig;
import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.repository.impl.JdbcCategoryRepository;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import static java.lang.String.valueOf;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class CategoryConverterTest {

    @Test
    public void convertAKnownCategory() throws Exception {
        int id = Integer.MAX_VALUE;
        Category category = new Category(id, "Test Category");
        JdbcCategoryRepository repository = new JdbcCategoryRepository(new DatabaseConfig().dataSource());
        CategoryConverter converter = new CategoryConverter(repository);
        repository.addNew(category);
        Category convertedCategory = converter.convert(valueOf(id));
        assertEquals(category, convertedCategory);
        repository.delete(category);
    }


    @Test(expected = EmptyResultDataAccessException.class)
    public void convertAnUnKnownCategory() throws Exception {
        int id = Integer.MAX_VALUE;
        JdbcCategoryRepository repository = new JdbcCategoryRepository(new DatabaseConfig().dataSource());
        CategoryConverter converter = new CategoryConverter(repository);
        converter.convert(valueOf(id));
    }
}
