package no.arktekk.training.spring.config;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static no.arktekk.training.spring.util.DatabaseUtils.no_NO;
import static no.arktekk.training.spring.util.DatabaseUtils.timeStampFormatter;

/**
 * Will by default, if registered as a bean in the container, create neccecary
 * tables, then populate them with a small set of test data.
 * <p/>
 * <p/>
 * To prevent it to populate test data, set the createTestData property to false
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class TestDataPopulator {
    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;
    private boolean createTestData = true;
    private boolean testDataInitialized = false;

    @Autowired
    public TestDataPopulator(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    public void setCreateTestData(boolean createTestData) {
        this.createTestData = createTestData;
    }

    @PostConstruct
    public void generateDatabaseIfEmpty() {
        if (databaseEmpty()) {
            createTables();
            if (createTestData && !testDataInitialized) {
                insertTestData();
            }
        }
    }

    private boolean databaseEmpty() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return !connection.getMetaData().getTables(null, null, "AUCTIONS", null).next();
        } catch (SQLException e) {
            throw new RuntimeException("Could not get table metadata", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Do nothing :)
            }
        }

    }

    private void createTables() {
        template.update("CREATE TABLE Auctions(id DOUBLE PRIMARY KEY, minimumPrice DOUBLE, starts TIMESTAMP, expires TIMESTAMP, description VARCHAR)");
        template.update("CREATE TABLE Albums(id DOUBLE PRIMARY KEY, auctionId DOUBLE, title VARCHAR, artist VARCHAR , categoryId INT, labelId INT)");
        template.update("CREATE TABLE Tracks(id DOUBLE PRIMARY KEY, albumId DOUBLE, Name VARCHAR, number INT, durationMinutes INT, durationSeconds INT, live BIT, Note VARCHAR )");
        template.update("CREATE TABLE Categories(Id INT PRIMARY KEY, Name VARCHAR)");
        template.update("CREATE TABLE Labels(Id INT PRIMARY KEY, Name VARCHAR)");
    }

    private void insertTestData() {
        insertLabels();
        insertCategories();
        insertAuctions();
        insertAlbums();
        insertTracks();
        testDataInitialized = true;

    }

    private void insertLabels() {
        template.update("insert into Labels values(1,'Island records')");
        template.update("insert into Labels values(2,'Jive')");
        template.update("insert into Labels values(3,'Polydor')");
        template.update("insert into Labels values(4,'Warner')");
    }

    private void insertCategories() {
        template.update("insert into Categories values(1,'Progrock')");
        template.update("insert into Categories values(2,'Pop')");
        template.update("insert into Categories values(3,'Rock')");
        template.update("insert into Categories values(4,'Classical')");
        template.update("insert into Categories values(5,'Jazz')");
        template.update("insert into Categories values(6,'Punk')");
        template.update("insert into Categories values(7,'Heavy Metal')");
    }

    private void insertAuctions() {
        DateTime now = new DateTime();
        String starts = timeStampFormatter.print(now.minusDays(10).toDate(), no_NO);
        String ends = timeStampFormatter.print(now.plusDays(10).toDate(), no_NO);

        template.update("insert into Auctions values(1,1200,'" + starts + "','" + ends + "','Mint prog rock albums')");
        template.update("insert into Auctions values(2,40,'" + starts + "','" + ends + "','Boring pop records')");
        template.update("insert into Auctions values(3,40,'" + starts + "','" + starts + "','Expired auction')");
    }

    private void insertAlbums() {
        template.update("insert into Albums values(1,1,'Aqualung','Jethro Tull',1,1)");
        template.update("insert into Albums values(2,1,'Popol Vuh','Popol Vuh',1,3)");
        template.update("insert into Albums values(3,2,'In The Zone','Brittney Spears',2,2)");

    }

    private void insertTracks() {
        template.update("insert into Tracks values(1,1,'Aqualung',1,6,37,0,'')");
        template.update("insert into Tracks values(2,1,'Cross Eyed Mary',2,4,09,0,'')");
        template.update("insert into Tracks values(3,1,'Cheap Day Return',3,1,23,0,'')");
        template.update("insert into Tracks values(4,1,'Mother Goose',4,3,53,0,'')");


        template.update("insert into Tracks values(5,2,'Hunchback',1,6,37,0,'')");
        template.update("insert into Tracks values(6,2,'Joy & Pleasure',2,4,09,0,'')");
        template.update("insert into Tracks values(7,2,'All We Have is the Past',3,1,23,0,'')");
        template.update("insert into Tracks values(8,2,'Leavin Chigago',4,3,53,0,'')");


        template.update("insert into Tracks values(9,3,'Me Against The Music',1,6,37,0,'')");
        template.update("insert into Tracks values(10,3,'I Got That',2,4,09,0,'')");
        template.update("insert into Tracks values(11,3,'Showdown',3,1,23,0,'')");
        template.update("insert into Tracks values(12,3,'Toxic',4,3,53,0,'')");

    }
}
