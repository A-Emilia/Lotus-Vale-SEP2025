package utilities.querying.card;

import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.target.CollectionTarget;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLAddCard implements QueryBuilder {

  public static String prepareQuery(CollectionTarget target) {
    return switch (target.targetType()) {
      case MAIN_COLLECTION -> "INSERT IGNORE INTO user_cards (user_id, card_id) VALUES (?, ?)";
      case DECK -> "INSERT IGNORE INTO card_in_deck (deck_id, card_id) VALUES (?, ?)";
      case SUB_COLLECTION -> null;
    };
  }

  public static PreparedStatement build(Connection con, AddCardRequest request) throws SQLException {
    String sql = prepareQuery(request.target());

    PreparedStatement res = con.prepareStatement(sql);

    Integer targetId = switch (request.target().targetType()) {
      case DECK -> request.target().targetId();
      case MAIN_COLLECTION -> request.userId();
      case SUB_COLLECTION -> null; // TODO
    };

    for (Integer cardId : request.cardIds()) {
      res.setInt(1, targetId);
      res.setInt(2, cardId);
      res.addBatch();
    }

    return res;
  }
}