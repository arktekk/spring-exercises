package no.arktekk.training.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;

/**
 * Creates by default a H2 inmemory datasource.
 * <p/>
 * Could be configured to use a different database with the provided
 * properties.
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class TestDataSourceFactoryBean implements FactoryBean<DataSource> {
    private String url = "jdbc:h2:mem:auction";
    private String driverClassName = org.h2.Driver.class.getName();
    private String userName = "sa";
    private String password = null;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public DataSource getObject() throws Exception {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        if (null != password) {
            dataSource.setPassword(password);
        }
        return dataSource;

    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
