package utilities.querying.card;

import communication.requests.card_requests.AddCardRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLAddCard implements QueryBuilder {
  private static final String sql = "INSERT IGNORE INTO user_cards (user_id, card_id) VALUES (?, ?)";

  public static PreparedStatement build(Connection con, AddCardRequest request) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql);

    for (Integer cardId : request.cardIds()) {
      res.setInt(1, request.userId());
      res.setInt(2, cardId);
      res.addBatch();
    }

    return res;
  }
}
