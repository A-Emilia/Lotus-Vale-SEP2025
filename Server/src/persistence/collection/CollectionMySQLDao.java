package persistence.collection;

import communication.requests.collection_requests.GetCollectionRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;
import utilities.querying.collection.MySQLCollectionQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectionMySQLDao implements CollectionDao {


  @Override
  public ArrayList<Card> getCollection(GetCollectionRequest payload) {

    MySQLCollectionQuery getQuery = MySQLCollectionQuery.getCollection(payload);

    try (Connection con = DatabaseConnector.getConnection()) {
      PreparedStatement sqlStatement = getQuery.build(con);

      ResultSet rs = sqlStatement.executeQuery();

      return Card.sqlToCards(rs);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
