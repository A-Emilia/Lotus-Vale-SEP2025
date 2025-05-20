package model.entities.deck;

import model.entities.card.Card;
import model.entities.user.User;

import java.util.ArrayList;

public class Deck {
  private final int deckId;
  private final User owner;
  private final String deckName;
  private final ArrayList<Card> cards;

  private Deck(Builder builder) {
    this.deckId = builder.deckId;
    this.owner = builder.owner;
    this.deckName = builder.deckName;
    this.cards = builder.cards;
  }

  public int getDeckId() {return deckId;}
  public User getOwner() {return owner;}
  public String getDeckName() {return deckName;}
  public ArrayList<Card> getCards() {return cards;}
  public int getOwnerId() {return owner.getId();}

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

    public Deck build() {
      return new Deck(this);
    }
  }
}
