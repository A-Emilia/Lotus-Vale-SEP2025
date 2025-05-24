package model.entities.deck;

import model.entities.card.Card;
import model.entities.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StandardDeck implements Deck {
  private final int deckId;
  private final User owner;
  private final String deckName;
  private final ArrayList<Card> cards;

  private StandardDeck(Builder builder) {
    this.deckId = builder.deckId;
    this.owner = builder.owner;
    this.deckName = builder.deckName;
    this.cards = builder.cards;
  }

  @Override public int getDeckId() {return deckId;}
  @Override public User getOwner() {return owner;}
  @Override public String getDeckName() {return deckName;}
  @Override public ArrayList<Card> getCards() {return cards;}
  @Override public int getOwnerId() {return owner.getId();}

  public static ArrayList<StandardDeck> sqlToDeck(ResultSet rs) throws SQLException {
    ArrayList<StandardDeck> decks = new ArrayList<>();
    while (rs.next()) {
      int deckId = rs.getInt("id");
      int ownerId = rs.getInt("owner_id");
      String username = rs.getString("username");
      String deckName = rs.getString("deck_name");

      User user = new User.Builder(ownerId)
          .username(username)
          .build();

      StandardDeck deck = new Builder(deckId, user)
          .deckName(deckName)
          .build();

      decks.add(deck);
    }

    return decks;
  }

  public static class Builder {
    private final int deckId;
    private final User owner;
    private String deckName;
    private ArrayList<Card> cards;

    public Builder(int deckId, User owner) {
      this.deckId = deckId;
      this.owner = owner;
    }

    public Builder deckName(String deckName) {
      this.deckName = deckName;
      return this;
    }

    public Builder card(Card card) {
      if(this.cards == null) {
        this.cards = new ArrayList<>();
      }
      cards.add(card);
      return this;
    }

    public Builder cardList(ArrayList<Card> cards) {
      this.cards = cards;
      return this;
    }

    public StandardDeck build() {
      return new StandardDeck(this);
    }
  }
}
