package utilities.querying.deck;

import communication.requests.deck_requests.CreateDeckRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLCreateDeck implements QueryBuilder {
  private static final String sql = "INSERT INTO deck (owner_id, deck_name) VALUES (?, ?)";

  public static PreparedStatement build(Connection con, CreateDeckRequest request) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql);
    res.setInt(1, request.userId());

    if (request.deckName() != null && !request.deckName().isEmpty()) {
      res.setString(2, request.deckName());
    } else {
      res.setString(2, "Unnamed Deck");
    }

    return res;
  }
}
