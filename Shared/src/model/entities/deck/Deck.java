package model.entities.deck;

import model.entities.card.Card;
import model.entities.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Deck {
  int getDeckId();
  User getOwner();
  String getDeckName();
  ArrayList<Card> getCards();
  int getOwnerId();
  static ArrayList<Deck> sqlToDeck(ResultSet rs) {return null;}
}
