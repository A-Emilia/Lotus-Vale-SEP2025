package model.entities.card;

import com.google.gson.Gson;
import networking.DatabaseConnector;

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



    Gson gson = new Gson();
    Card card2 = new Card.Builder(6969, "uwu").superType("Legendary").superType("Snow").cardType("Land").build();
    System.out.println("Original card before serialization: \n" + card2.toString());
    System.out.println(card2.getClass() + "\n");

    String card2Json = gson.toJson(card2);
    System.out.println("Serialized JSON string of Card2: \n" + card2Json + "\n");

    //card2Json = "{\"id\":6969,\"setCode\":\"uwu\",\"supertype\":[\"LEGENDARY\",\"SNOW\"],\"cardType\":[\"LAND\"],\"uwu\":\"meow\"}\n";

    Card card2Deserialized = gson.fromJson(card2Json, Card.class);

    System.out.println("Deserialized card from JSON string: \n" + card2Deserialized.toString());
    System.out.println(card2Deserialized.getClass());
  }
}
