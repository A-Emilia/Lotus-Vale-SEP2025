package communication.services.card;

import com.google.gson.Gson;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.GetLotusRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;
import persistence.card.CardDao;
import utilities.querying.CardQueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardServiceImpl implements CardService {
  private CardDao cardDao;

  public CardServiceImpl(CardDao cardDao) {
    this.cardDao = cardDao;
  }

  @Override
  public ArrayList<Card> getCard(GetCardRequest payload) {

    CardQueryBuilder getQuery = CardQueryBuilder.getRequest(payload);

    try(Connection con = DatabaseConnector.getConnection()) {
      PreparedStatement sqlStatement = con.prepareStatement(getQuery.build());
      List<Object> cardParams = getQuery.getCardParam();

      for (int i = 0; i < cardParams.size(); i++) {
        sqlStatement.setString(i+1, (String) cardParams.get(i));
      }

      ResultSet rs = sqlStatement.executeQuery();

      return Card.sqlToCards(rs);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Card getLotus(GetLotusRequest request) {

    try (Connection con = DatabaseConnector.getConnection()) {
      PreparedStatement lotusStatement = con.prepareStatement(
          "SELECT c.name, c.id, c.text, c.setCode\r\n" + //
              "FROM cards c, sets s\r\n" + //
              "WHERE c.name = '" + request.cardName() + "'\r\n" + //
              "AND c.setCode = '" + request.set_code() +"'\r\n" + //
              "AND s.keyruneCode = '" + request.set_code() +"';"
      );

      ResultSet rs = lotusStatement.executeQuery();

      List<Card> cards = new ArrayList<>();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String setCode = rs.getString("setCode");
        String text = rs.getString("text");
        Card card = new Card.Builder(id, setCode).name(name).text(text).build();
        cards.add(card);
      }

      return cards.getFirst();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
