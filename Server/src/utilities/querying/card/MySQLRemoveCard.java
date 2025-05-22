package utilities.querying.card;

import communication.requests.card_requests.RemoveCardRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLRemoveCard implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder();
  private final List<Object> cardParam = new ArrayList<>();

  public static MySQLRemoveCard remove(RemoveCardRequest request) {
    return new MySQLRemoveCard()
        .filterByCollection(request.target())
        .filterByUserId(request.userId())
        .filterByCardIds(request.cardIds());
  }

  // TODO Add target logic later
  public MySQLRemoveCard filterByCollection(String target) {
    if (target != null && !target.isEmpty()) {
      sql.append("DELETE FROM ").append("user_cards").append(" WHERE user_id = ?");
      return this;
    }
    sql.append("DELETE FROM ").append("user_cards").append(" WHERE user_id = ?");
    return this;
  }

  public MySQLRemoveCard filterByUserId(int userId) {
    cardParam.add(userId);
    return this;
  }

  public MySQLRemoveCard filterByCardIds(List<Integer> cardIds) {
    if (cardIds != null && !cardIds.isEmpty()) {
      sql.append(" AND card_id IN (");

      for (int i = 0; i < cardIds.size(); i++) {
        sql.append("?");
        if (i < cardIds.size()-1) {
          sql.append(", ");
        }
        cardParam.add(cardIds.get(i));
      }
      sql.append(")");
    }

    return this;
  }

  public PreparedStatement build(Connection con) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql.toString());

    for (int i = 0; i < cardParam.size(); i++) {
      res.setObject(i+1, cardParam.get(i));
    }
    return res;
  }
}
