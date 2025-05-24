package utilities.querying.collection;

import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLGetCollection implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder(
            "SELECT c.*, ci.multiverseId FROM user_cards uc " +
            "JOIN cards c ON uc.card_id = c.id " +
            "JOIN cardidentifiers ci ON c.id = ci.id " +
            "WHERE 1=1"
  );
  private final List<Object> collParam = new ArrayList<>();

  public MySQLGetCollection filterByUserId(int userId) {
    sql.append(" AND uc.user_id = ?");
    collParam.add(userId);
    return this;
  }

  public PreparedStatement build(Connection con) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql.toString());

    for (int i = 0; i < collParam.size(); i++) {
      res.setObject(i+1, collParam.get(i));
    }

    return res;
  }
}
