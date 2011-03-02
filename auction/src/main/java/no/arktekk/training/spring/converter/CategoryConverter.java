package no.arktekk.training.spring.converter;

import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.repository.impl.JdbcCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class CategoryConverter implements Converter<String, Category> {

    private final JdbcCategoryRepository repository;

    @Autowired
    public CategoryConverter(JdbcCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category convert(String id) {
        return repository.find(id);
    }
}
