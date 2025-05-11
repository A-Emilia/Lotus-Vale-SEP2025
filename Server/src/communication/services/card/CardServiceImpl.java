package communication.services.card;

import com.google.gson.Gson;
import communication.requests.card_requests.GetLotusRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;
import persistence.card.CardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CardServiceImpl implements CardService {
  private CardDao cardDao;

  public CardServiceImpl(CardDao cardDao) {
    this.cardDao = cardDao;
  }

  @Override
  public Card getCard(Object payload) {
    return null;
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
