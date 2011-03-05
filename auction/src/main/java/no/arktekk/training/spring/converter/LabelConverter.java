package no.arktekk.training.spring.converter;

import no.arktekk.training.spring.domain.Label;
import no.arktekk.training.spring.repository.impl.JdbcLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class LabelConverter implements Converter<String, Label> {

    private final JdbcLabelRepository repository;

    @Autowired
    public LabelConverter(JdbcLabelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Label convert(String id) {
        return repository.find(id);
    }
}
