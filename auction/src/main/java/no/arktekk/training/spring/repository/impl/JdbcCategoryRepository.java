package no.arktekk.training.spring.repository.impl;

import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.mapper.CategoryMapper;
import no.arktekk.training.spring.repository.BasicCrudRepository;
import no.arktekk.training.spring.util.Cache;
import no.arktekk.training.spring.util.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Responsible for storing, and loading data from the Categories table.
 * Created as a repository in order to add cache, since categories
 * are often accessed, to hit the database each time would become
 * an application bottleneck
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Repository
public class JdbcCategoryRepository implements BasicCrudRepository<Category> {
    private final CategoryMapper categoryMapper = new CategoryMapper();
    private final JdbcTemplate template;
    private final Cache<Category> cache;


    @Autowired
    public JdbcCategoryRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.cache = new Cache<Category>();
    }

    public JdbcCategoryRepository(DataSource dataSource, Cache<Category> cache) {
        this.template = new JdbcTemplate(dataSource);
        this.cache = cache;
    }

    @Override public Category find(final Object id) {
        return cache.get(id, new Command<Category>() {
            public Category execute() {
                return template.queryForObject("select * from Categories where id=?", categoryMapper, id);
            }
        });
    }

    @Override public List<Category> list() {
        return template.query("select * from Categories", categoryMapper);
    }

    @Override public void addNew(Category category) {
        template.update("insert into Categories values(?,?)", category.getId(), category.getName());
    }

    @Override public void update(Category category) {
        template.update("update Categories set name=? where id=?", category.getName(), category.getId());
        cache.evict(category.getId());
    }

    @Override public void delete(Category category) {
        template.update("delete from Categories where id=?", category.getId());
        cache.evict(category.getId());
    }
}
