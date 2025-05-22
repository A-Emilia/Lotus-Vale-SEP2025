package utilities.querying.deck;

import communication.requests.deck_requests.DeleteDeckRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDeleteDeck implements QueryBuilder {
  private static final String sql = "DELETE FROM deck d WHERE d.id = ?";

  public static PreparedStatement build(Connection con, DeleteDeckRequest request) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql);

    for (Integer deckId : request.deckIds()) {
      res.setInt(1, deckId);
      res.addBatch();
    }

    return res;
  }
}
