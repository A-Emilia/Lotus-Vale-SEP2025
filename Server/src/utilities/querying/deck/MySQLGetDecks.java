package utilities.querying.deck;

import communication.requests.deck_requests.GetDecksRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLGetDecks implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder(
          "SELECT d.*, u.username FROM deck d " +
          "INNER JOIN user u ON d.owner_id = u.id " +
          "WHERE 1=1"
  );
  private final List<Object> deckParam = new ArrayList<>();

  public static MySQLGetDecks get(GetDecksRequest request) {
    return new MySQLGetDecks()
        .filterByCommander()
        .filterByUser(request.userId());
  }

  public MySQLGetDecks filterByCommander() {
    return this;
  }

  public MySQLGetDecks filterByUser(Integer userId) {
    if (userId != null) {
      sql.append(" AND d.owner_id = ?");
      deckParam.add(userId);
    }
    return this;
  }

  public PreparedStatement build(Connection con) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql.toString());

    for (int i = 0; i < deckParam.size(); i++) {
      res.setObject(i+1, deckParam.get(i));
    }

    return res;
  }
}
