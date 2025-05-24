package utilities.querying.deck;

import communication.requests.deck_requests.GetDecksRequest;
import communication.requests.deck_requests.target.DeckTarget;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLGetDecks implements QueryBuilder {

  public static MySQLGetDecks get(GetDecksRequest request) {
    return new MySQLGetDecks()
        .prepareQuery(request.targetType())
        .filterByUser(request.userId());
  }

  public MySQLGetDecks prepareQuery(DeckTarget target) {
    switch (target.targetType()) {
      case PERSONAL -> {}
      case STANDARD -> this.filterByFormat("standard");
      case COMMANDER -> this.filterByFormat("commander");
    }

    return this;
  }

  private MySQLGetDecks filterByFormat(String format) {
    sql.append(" AND d.format = ?");
    deckParam.add(format);
    return this;
  }

  private final StringBuilder sql = new StringBuilder(
          "SELECT d.*, u.username FROM deck d " +
          "INNER JOIN user u ON d.owner_id = u.id " +
          "WHERE 1=1"
  );
  private final List<Object> deckParam = new ArrayList<>();

  public MySQLGetDecks filterByCommander() {
    // TODO
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
