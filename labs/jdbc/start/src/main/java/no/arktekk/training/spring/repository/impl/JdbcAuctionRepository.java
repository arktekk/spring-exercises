package no.arktekk.training.spring.repository.impl;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static no.arktekk.training.spring.util.DatabaseUtils.no_NO;
import static no.arktekk.training.spring.util.DatabaseUtils.timeStampFormatter;


/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Repository
public class JdbcAuctionRepository implements AuctionRepository {
    private final DataSource dataSource;

    @Autowired
    public JdbcAuctionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Auction> listAllRunningAuctions() {
        List<Auction> auctions = new ArrayList<Auction>();
        String sql = "select * from Auctions where ? between starts and expires";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, timeStampFormatter.print(new DateTime().toDate(), no_NO));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                auctions.add(mapAuction(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occured while retreiving auction", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
        return auctions;
    }

    public Auction findById(Double auctionId) {
        Auction auction;
        String sql = "select * from Auctions where id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, auctionId);
            resultSet = preparedStatement.executeQuery();
            advanceResultSet(resultSet);
            auction = mapAuction(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occured while retreiving auction", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
        return auction;
    }

    private Auction mapAuction(ResultSet resultSet) throws SQLException {
        return new Auction(resultSet.getDouble("id"),
                resultSet.getDouble("minimumPrice"),
                resultSet.getString("description"),
                new DateTime(resultSet.getDate("starts")),
                new DateTime(resultSet.getDate("expires")));
    }

    private void advanceResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
