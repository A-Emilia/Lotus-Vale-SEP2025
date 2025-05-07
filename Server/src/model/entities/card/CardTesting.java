package model.entities.card;

import com.google.gson.Gson;
import communication.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class CardTesting {
  public static void main(String[] args)
  {
    Card card = new Card.Builder(600, "A").name("Jodah, the Sexist").build();
    Card card2 = new Card.Builder(6969, "uwu").superType("Legendary").superType("Snow").cardType("Land").build();
    System.out.println(card.getName());
    System.out.println(card2.getSupertype());
    System.out.println(card2.getCardType());

    try(Connection con = DatabaseConnector.getConnection()) {
      System.out.println("Connected to MySQL.");
    } catch(SQLException e) {
      e.printStackTrace();
    }

    Gson gson = new Gson();
    String jsonString = gson.toJson(card2);
    System.out.println(jsonString);

    System.out.println(card2.getClass());

    Card card3 = gson.fromJson(jsonString, Card.class);
    System.out.println(card2.toString());
    System.out.println(card3.toString());
    System.out.println(card3.getClass());

  }
}
