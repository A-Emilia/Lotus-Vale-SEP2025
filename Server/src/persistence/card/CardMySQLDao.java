package persistence.card;

import communication.requests.card_requests.GetCardRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;
import utilities.querying.card.MySQLCardQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CardMySQLDao implements CardDao {

  @Override
  public ArrayList<Card> getCard(GetCardRequest payload) {

    MySQLCardQuery getQuery = MySQLCardQuery.getRequest(payload);

    try(Connection con = DatabaseConnector.getConnection()) {
      PreparedStatement sqlStatement = getQuery.build(con);

      ResultSet rs = sqlStatement.executeQuery();

      return Card.sqlToCards(rs);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
