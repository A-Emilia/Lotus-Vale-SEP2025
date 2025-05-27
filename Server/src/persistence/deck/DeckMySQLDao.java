package persistence.deck;

import communication.ResponseType;
import communication.requests.deck_requests.create_deck.CreateCommanderDeckRequest;
import communication.requests.deck_requests.create_deck.CreateDeckRequest;
import communication.requests.deck_requests.create_deck.CreateStandardDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;
import networking.DatabaseConnector;
import utilities.querying.deck.MySQLDeckFormats;
import utilities.querying.deck.MySQLCreateDeck;
import utilities.querying.deck.MySQLDeleteDeck;
import utilities.querying.deck.MySQLGetDecks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
    try (Connection con = DatabaseConnector.getConnection()) {
      con.setAutoCommit(false);

      int deckId;

      try (PreparedStatement createDeck = MySQLCreateDeck.build(con, payload)) {
        createDeck.executeUpdate();

        try (ResultSet keys = createDeck.getGeneratedKeys()) {
          if(!keys.next()) {
            throw new SQLException("Error");
          }
          deckId = keys.getInt(1);
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

      switch (payload) {
        case CreateStandardDeckRequest ignored -> {}
        case CreateCommanderDeckRequest commanderReq -> {
          Optional<PreparedStatement> commander = MySQLDeckFormats.createCommanderDeck(con, deckId, commanderReq);

          if (commander.isPresent()) {
            try (PreparedStatement res = commander.get()) {
              res.executeBatch();
            }
          }
        }
      }

      con.commit();
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
