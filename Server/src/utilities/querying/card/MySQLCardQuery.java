package utilities.querying.card;

import communication.requests.card_requests.GetCardRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCardQuery implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder("SELECT c.*, ci.multiverseId FROM cards c, cardidentifiers ci WHERE 1=1");
  private final List<Object> cardParam = new ArrayList<>();

  public static MySQLCardQuery getRequest(GetCardRequest request) {
    MySQLCardQuery res = new MySQLCardQuery()
        .filterByName(request.name())
        .filterBySetCode(request.setCode())
        .filterByTextContains(request.textContains())
        .getMultiverseId();

    return res;
  }

  public MySQLCardQuery filterByName(String name) {
    if (name != null && !name.isEmpty()) {
      sql.append(" AND c.name LIKE ?");
      cardParam.add("%" + name + "%");
    }
    return this;
  }

  public MySQLCardQuery filterBySetCode(String setCode) {
    if (setCode != null && !setCode.isEmpty()) {
      sql.append(" AND c.setCode = ?");
      cardParam.add(setCode);
    }
    return this;
  }

  public MySQLCardQuery filterByTextContains(String text) {
    if (text != null && !text.isEmpty()) {
      sql.append(" AND c.text LIKE ?");
      cardParam.add("%" + text + "%");
    }
    return this;
  }

  public MySQLCardQuery getMultiverseId() {
    sql.append(" AND c.id = ci.id ");
    return this;
  }

  // Add connection as input to the build method?
  public PreparedStatement build(Connection con) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql.toString());

    for (int i = 0; i < cardParam.size(); i++) {
      res.setString(i+1, (String) cardParam.get(i));
    }

    return res;
  }

  public List<Object> getCardParam() {
    return cardParam;
  }
}
