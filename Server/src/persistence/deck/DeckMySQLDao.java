package persistence.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;
import networking.DatabaseConnector;
import utilities.querying.deck.MySQLCreateDeck;
import utilities.querying.deck.MySQLDeleteDeck;
import utilities.querying.deck.MySQLGetDecks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeckMySQLDao implements DeckDao {

  @Override
  public ArrayList<Deck> getDecks(GetDecksRequest payload) {

    MySQLGetDecks getDecks = MySQLGetDecks.get(payload);

    try (Connection con = DatabaseConnector.getConnection()) {
      PreparedStatement sqlStatement = getDecks.build(con);

      ResultSet rs = sqlStatement.executeQuery();

      return Deck.sqlToDeck(rs);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseType createDeck(CreateDeckRequest payload) {

    try (Connection con = DatabaseConnector.getConnection();
        PreparedStatement createDeck = MySQLCreateDeck.build(con, payload)) {

      createDeck.executeUpdate();
      return ResponseType.OK;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseType deleteDeck(DeleteDeckRequest payload) {

    try (Connection con = DatabaseConnector.getConnection();
        PreparedStatement deleteDeck = MySQLDeleteDeck.build(con, payload)) {

      deleteDeck.executeBatch();

      return ResponseType.OK;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
