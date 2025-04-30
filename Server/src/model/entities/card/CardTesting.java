package model.entities.card;

import communication.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class CardTesting {
  public static void main(String[] args)
  {
    Card card = new Card.Builder(600, "A").name("Jodah, the Sexist").build();
    System.out.println(card.getName());

    try(Connection con = DatabaseConnector.getConnection()) {
      System.out.println("Connected to MySQL.");
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }
}
