package utilities.querying.card;

import communication.requests.card_requests.GetCardRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLGetCard implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder(
      "SELECT c.*, ci.multiverseId" +
          " FROM cards c" +
          " JOIN cardidentifiers ci ON c.id = ci.id" +
          " WHERE 1=1"
  );
  private final List<Object> cardParam = new ArrayList<>();

  public static MySQLGetCard getRequest(GetCardRequest request) {
    MySQLGetCard query = new MySQLGetCard()
        .filterByName(request.name())
        .filterBySetCode(request.setCode())
        .filterByTextContains(request.textContains())
        .getMultiverseId();

    // If it is null, it is just for searching across all cards.
    if (request.target() != null) {
      switch (request.target().targetType()) {
        case MAIN_COLLECTION -> query.filterByUserCollection(request.target().userId());
        case SUB_COLLECTION -> query.filterBySubCollection(null);
        case DECK -> query.filterByDeck(request.target().targetId());
      }
    }

    return query;
  }

  public MySQLGetCard filterByName(String name) {
    if (name != null && !name.isEmpty()) {
      sql.append(" AND c.name LIKE ?");
      cardParam.add("%" + name + "%");
    }
    return this;
  }

  public MySQLGetCard filterBySetCode(String setCode) {
    if (setCode != null && !setCode.isEmpty()) {
      sql.append(" AND c.setCode = ?");
      cardParam.add(setCode);
    }
    return this;
  }

  public MySQLGetCard filterByTextContains(String text) {
    if (text != null && !text.isEmpty()) {
      sql.append(" AND c.text LIKE ?");
      cardParam.add("%" + text + "%");
    }
    return this;
  }

  public MySQLGetCard getMultiverseId() {
    sql.append(" AND c.id = ci.id ");
    return this;
  }

  public void filterByDeck(Integer deckId) {
    if (deckId != null) {
      sql.append(" AND c.id IN (SELECT card_id FROM card_in_deck WHERE deck_id = ?)");
      cardParam.add(deckId);
    }
  }

  public void filterByUserCollection(Integer userId) {
    if (userId != null) {
      sql.append(" AND c.id IN (SELECT card_id FROM user_cards WHERE user_id = ?)");
      cardParam.add(userId);
    }
  }

  public void filterBySubCollection(Integer subCollectionId) {
    if (subCollectionId != null) {
      // TODO Implement subcollection later.
      //sql.append(" AND c.id IN (SELECT card_id FROM sub_collection WHERE sub_collection_id = ?)");
      //cardParam.add(subCollectionId);
    }
  }

  // Add connection as input to the build method?
  public PreparedStatement build(Connection con) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql.toString());

    for (int i = 0; i < cardParam.size(); i++) {
      res.setObject(i+1, cardParam.get(i));
    }

    return res;
  }

  public List<Object> getCardParam() {
    return cardParam;
  }
}
