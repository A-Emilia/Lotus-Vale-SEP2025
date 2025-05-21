package communication.services.card;

import communication.ResponseType;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.GetLotusRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;
import persistence.card.CardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CardServiceImpl implements CardService {
  private CardDao cardDao;

  public CardServiceImpl(CardDao cardDao) {
    this.cardDao = cardDao;
  }

  @Override
  public ArrayList<Card> getCard(GetCardRequest payload) throws NoSuchElementException {
    ArrayList<Card> res = cardDao.getCard(payload);
    // Convert to some sort of DTO? I feel I definitely do not need it.
    // Error handling.

    if (res.isEmpty()) {
      throw new NoSuchElementException("No cards found.");
    }

    return res;
  }

  @Override
  public ResponseType addCard(AddCardRequest payload) {
    cardDao.addCard(payload);
    return ResponseType.OK;
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
