package persistence.card;

import communication.ResponseType;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.EditCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.RemoveCardRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;
import utilities.querying.card.MySQLAddCard;
import utilities.querying.card.MySQLGetCard;
import utilities.querying.card.MySQLRemoveCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CardMySQLDao implements CardDao {

  @Override
  public ArrayList<Card> getCard(GetCardRequest payload) {

    MySQLGetCard getQuery = MySQLGetCard.getRequest(payload);

    try(Connection con = DatabaseConnector.getConnection()) {
      PreparedStatement sqlStatement = getQuery.build(con);

      ResultSet rs = sqlStatement.executeQuery();

      return Card.sqlToCards(rs);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseType addCard(AddCardRequest req) {
    try (Connection con = DatabaseConnector.getConnection();
    PreparedStatement addCardQuery = MySQLAddCard.build(con, req)) {
      addCardQuery.executeBatch();
      return ResponseType.OK;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseType removeCard(RemoveCardRequest payload) {

    // Removing duplicate entries from the list.
    ArrayList<Integer> cardsToRemove = (ArrayList<Integer>) payload.cardIds().stream().distinct().toList();
    RemoveCardRequest request = new RemoveCardRequest(payload.userId(), cardsToRemove, payload.target());

    try (Connection con = DatabaseConnector.getConnection();
    PreparedStatement removeQuery = MySQLRemoveCard.remove(request).build(con)) {
      removeQuery.executeUpdate();
      return ResponseType.OK;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ArrayList<Card> editCard(EditCardRequest payload) {
    return null;
  }
}
