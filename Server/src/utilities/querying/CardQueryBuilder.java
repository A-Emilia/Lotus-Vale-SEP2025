package utilities.querying;

import communication.requests.card_requests.GetCardRequest;

import java.util.ArrayList;
import java.util.List;

public class CardQueryBuilder implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder("SELECT * FROM cards c WHERE 1=1");
  private final List<Object> cardParam = new ArrayList<>();

  public static CardQueryBuilder getRequest(GetCardRequest request) {
    CardQueryBuilder res = new CardQueryBuilder()
        .filterByName(request.name())
        .filterBySetCode(request.setCode())
        .filterByTextContains(request.textContains());

    return res;
  }

  public CardQueryBuilder filterByName(String name) {
    if (name != null && !name.isEmpty()) {
      sql.append(" AND c.name = ?");
      cardParam.add(name);
    }
    return this;
  }

  public CardQueryBuilder filterBySetCode(String setCode) {
    if (setCode != null && !setCode.isEmpty()) {
      sql.append(" AND c.setCode = ?");
      cardParam.add(setCode);
    }
    return this;
  }

  public CardQueryBuilder filterByTextContains(String text) {
    if (text != null && !text.isEmpty()) {
      sql.append(" AND c.text LIKE ?");
      cardParam.add("%" + text + "%");
    }
    return this;
  }

  public String build() {
    return sql.toString();
  }

  public List<Object> getCardParam() {
    return cardParam;
  }

}
