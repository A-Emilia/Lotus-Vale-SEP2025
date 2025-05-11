package utilities.querying;

import communication.requests.card_requests.GetCardRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardQueryTest {
  public static void main(String[] args) throws SQLException
  {

    Connection con = DatabaseConnector.getConnection();
    GetCardRequest request = new GetCardRequest("Black Lotus", null, null);

    CardQueryBuilder query = CardQueryBuilder.getRequest(request);

    String sql = query.build();
    List<Object> params = query.getCardParam();


    PreparedStatement sqlStatement = con.prepareStatement(sql);

    for (int i = 0; i < params.size(); i++) {
      // If something is a different datatype it does not take care of that. I am not sure if it matters though.
      sqlStatement.setString(i+1, (String) params.get(i));
    }

    ResultSet rs = sqlStatement.executeQuery();

    ArrayList<Card> cards = Card.sqlToCards(rs);

    for (Card card : cards) {
      System.out.println(card.toString());
    }
  }
}
