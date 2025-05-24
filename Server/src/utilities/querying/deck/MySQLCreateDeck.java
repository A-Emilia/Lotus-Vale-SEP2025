package utilities.querying.deck;

import communication.requests.deck_requests.create_deck.CreateCommanderDeckRequest;
import communication.requests.deck_requests.create_deck.CreateDeckRequest;
import communication.requests.deck_requests.create_deck.CreateStandardDeckRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLCreateDeck implements QueryBuilder {
  private static final String sql = "INSERT INTO deck (owner_id, deck_name, format) VALUES (?, ?, ?)";

  public static PreparedStatement build(Connection con, CreateDeckRequest request) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    res.setInt(1, request.getUserId());

    if (request.getDeckName() != null && !request.getDeckName().isEmpty()) {
      res.setString(2, request.getDeckName());
    } else {
      res.setString(2, "Unnamed Deck");
    }

    String format = switch (request) {
      case CreateStandardDeckRequest ignored -> "standard";
      case CreateCommanderDeckRequest ignored -> "commander";

      default -> null;
    };

    res.setString(3, format);

    return res;
  }
}
