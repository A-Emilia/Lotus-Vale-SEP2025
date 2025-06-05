package utilities.querying.card;

import communication.requests.card_requests.ColorSort;
import communication.requests.card_requests.GetCardRequest;
import model.entities.card.components.CardSupertype;
import model.entities.card.components.CardType;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLGetCard implements QueryBuilder {
  private final StringBuilder fromClause = new StringBuilder(
      " FROM cards c" +
          " JOIN cardidentifiers ci ON c.id = ci.id"
  );
  private final StringBuilder whereClause = new StringBuilder(" WHERE 1=1");
  private final List<Object> cardParam = new ArrayList<>();

  public static MySQLGetCard getRequest(GetCardRequest request) {
    MySQLGetCard query = new MySQLGetCard()
        .filterByName(request.name())
        .filterBySetCode(request.setCode())
        .filterByTextContains(request.textContains())
        .filterByMana(request.colorSort(), request.white(), request.blue(), request.black(), request.red(), request.green(), request.colorless())
        .getMultiverseId()
        .filterBySupertypes(request.supertypes())
        .filterByTypes(request.types())
        .filterBySubtypes(request.subtypes());

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
      whereClause.append(" AND c.name LIKE ?");
      cardParam.add("%" + name + "%");
    }
    return this;
  }

  public MySQLGetCard filterBySetCode(String setCode) {
    if (setCode != null && !setCode.isEmpty()) {
      whereClause.append(" AND c.setCode = ?");
      cardParam.add(setCode);
    }
    return this;
  }

  public MySQLGetCard filterByTextContains(String text) {
    if (text != null && !text.isEmpty()) {
      whereClause.append(" AND c.text LIKE ?");
      cardParam.add("%" + text + "%");
    }
    return this;
  }

  public MySQLGetCard filterByMana(ColorSort sortingOption, Boolean white, Boolean blue, Boolean black, Boolean red, Boolean green, Boolean colorless) {
    if (sortingOption == null) return this;

    List<String> selectedColors = new ArrayList<>();
    if (Boolean.TRUE.equals(white)) selectedColors.add("{W}");
    if (Boolean.TRUE.equals(blue)) selectedColors.add("{U}");
    if (Boolean.TRUE.equals(black)) selectedColors.add("{B}");
    if (Boolean.TRUE.equals(red)) selectedColors.add("{R}");
    if (Boolean.TRUE.equals(green)) selectedColors.add("{G}");
    if (Boolean.TRUE.equals(colorless)) selectedColors.add("{C}");

    String manaColumn = "c.manaCost";

    switch (sortingOption) {
      case EXACT -> {
        for (String symbol : selectedColors) {
          whereClause.append(" AND ").append(manaColumn).append(" LIKE ?");
          cardParam.add("%" + symbol + "%");
        }
        String[] allSymbols = {"{W}", "{U}", "{B}", "{R}", "{G}", "{C}"};
        for (String symbol : allSymbols) {
          if (!selectedColors.contains(symbol)) {
            whereClause.append(" AND ").append(manaColumn).append(" NOT LIKE ?");
            cardParam.add("%" + symbol + "%");
          }
        }
      }
      case AT_LEAST -> {
        for (String symbol : selectedColors) {
          whereClause.append(" AND ").append(manaColumn).append(" LIKE ?");
          cardParam.add("%" + symbol + "%");
        }
      }
      case AT_MOST -> {
        String[] allSymbols = {"{W}", "{U}", "{B}", "{R}", "{G}", "{C}"};
        for (String symbol : allSymbols) {
          if (!selectedColors.contains(symbol)) {
            whereClause.append(" AND ").append(manaColumn).append(" NOT LIKE ?");
            cardParam.add("%" + symbol + "%");
          }
        }
        if (!selectedColors.isEmpty()) {
          for (String symbol : selectedColors) {
            whereClause.append(" AND ").append(manaColumn).append(" LIKE ?");
            cardParam.add("%" + symbol + "%");
          }
        }
      }
    }

    return this;
  }

  public MySQLGetCard getMultiverseId() {
    whereClause.append(" AND c.id = ci.id ");
    return this;
  }

  public void filterByDeck(Integer deckId) {
    if (deckId != null) {
      whereClause.append(" AND c.id IN (SELECT card_id FROM card_in_deck WHERE deck_id = ?)");
      cardParam.add(deckId);
    }
  }

  public void filterByUserCollection(Integer userId) {
    if (userId != null) {
      whereClause.append(" AND c.id IN (SELECT card_id FROM user_cards WHERE user_id = ?)");
      cardParam.add(userId);
    }
  }

  public MySQLGetCard filterBySupertypes(List<CardSupertype> supertypes) {
    if (supertypes != null && !supertypes.isEmpty()) {
      fromClause.append(" JOIN card_supertypes cs ON cs.card_id = c.id");
      fromClause.append(" JOIN supertypes s ON cs.supertype_id = s.id");
      whereClause.append(" AND s.name IN (");
      appendPlaceholders(supertypes.size(), whereClause);
      whereClause.append(")");
      for (CardSupertype supertype : supertypes) {
        cardParam.add(supertype.toString());
      }
    }
    return this;
  }

  public MySQLGetCard filterByTypes(List<CardType> types) {
    if (types != null && !types.isEmpty()) {
      whereClause.append(" AND c.id IN (SELECT card_id FROM card_types ct JOIN types t ON ct.type_id = t.id WHERE t.name IN (");
      appendPlaceholders(types.size(), whereClause);
      whereClause.append("))");
      for (CardType type : types) {
        cardParam.add(type.toString());
      }
    }
    return this;
  }

  public MySQLGetCard filterBySubtypes(List<String> subtypes) {
    if (subtypes != null && !subtypes.isEmpty()) {
      fromClause.append(" JOIN card_subtypes cst ON cst.card_id = c.id");
      whereClause.append(" AND cst.subtype IN (");
      appendPlaceholders(subtypes.size(), whereClause);
      whereClause.append(")");
      cardParam.addAll(subtypes);
    }
    return this;
  }

  private void appendPlaceholders(int count, StringBuilder target) {
    for (int i = 0; i < count; i++) {
      target.append("?");
      if (i < count - 1) {
        target.append(", ");
      }
    }
  }

  public void filterBySubCollection(Integer subCollectionId) {
    if (subCollectionId != null) {
      // TODO Implement subcollection later.
      //whereClause.append(" AND c.id IN (SELECT card_id FROM sub_collection WHERE sub_collection_id = ?)");
      //cardParam.add(subCollectionId);
    }
  }

  public PreparedStatement build(Connection con) throws SQLException {
    String finalSql = "SELECT c.*, ci.multiverseId" + fromClause + whereClause;
    PreparedStatement res = con.prepareStatement(finalSql);

    for (int i = 0; i < cardParam.size(); i++) {
      res.setObject(i + 1, cardParam.get(i));
    }

    return res;
  }

  public List<Object> getCardParam() {
    return cardParam;
  }
}