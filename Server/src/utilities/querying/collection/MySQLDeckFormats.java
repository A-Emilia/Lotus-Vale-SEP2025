package utilities.querying.collection;

import communication.requests.deck_requests.create_deck.CreateCommanderDeckRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MySQLDeckFormats implements QueryBuilder {

  public static Optional<PreparedStatement> createCommanderDeck(Connection con, int deckId, CreateCommanderDeckRequest req) throws SQLException {
    List<Integer> commanderIds = req.commanderCardIds();

    if (commanderIds == null || commanderIds.isEmpty()) {
      return Optional.empty();
    }

    PreparedStatement res = con.prepareStatement("INSERT INTO deck_commander (deck_id, card_id) VALUES (?, ?)");

    for (Integer commanderId : commanderIds) {
      res.setInt(1, deckId);
      res.setInt(2, commanderId);
      res.addBatch();
    }

    return Optional.of(res);
  }
}
