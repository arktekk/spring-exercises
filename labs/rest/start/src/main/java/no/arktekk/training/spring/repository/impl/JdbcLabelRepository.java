package no.arktekk.training.spring.repository.impl;

import no.arktekk.training.spring.domain.Label;
import no.arktekk.training.spring.mapper.LabelMapper;
import no.arktekk.training.spring.repository.BasicCrudRepository;
import no.arktekk.training.spring.util.Cache;
import no.arktekk.training.spring.util.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Responsible for storing, and loading data from the Labels table.
 * Created as a repository in order to add cache, since labels
 * are often accessed, to hit the database each time would become
 * an application bottleneck
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Repository
public class JdbcLabelRepository implements BasicCrudRepository<Label> {
    private final LabelMapper labelMapper = new LabelMapper();
    private final JdbcTemplate template;
    private final Cache<Label> cache;


    @Autowired
    public JdbcLabelRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.cache = new Cache<Label>();
    }

    public JdbcLabelRepository(DataSource dataSource, Cache<Label> cache) {
        this.template = new JdbcTemplate(dataSource);
        this.cache = cache;
    }

    @Override
    public Label find(final Object id) {
        return cache.get(id, new Command<Label>() {
            public Label execute() {
                return template.queryForObject("select * from Labels where id=?", labelMapper, id);
            }
        });
    }

    @Override
    public List<Label> list() {
        return template.query("select * from Labels", labelMapper);
    }

    @Override
    public void addNew(Label category) {
        template.update("insert into Labels values(?,?)", category.getId(), category.getName());
    }

    @Override
    public void update(Label category) {
        template.update("update Labels set name=? where id=?", category.getName(), category.getId());
        cache.evict(category.getId());
    }

    @Override
    public void delete(Label category) {
        template.update("delete from Labels where id=?", category.getId());
        cache.evict(category.getId());
    }
}
