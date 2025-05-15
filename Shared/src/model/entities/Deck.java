package model.entities;

import java.util.ArrayList;

public class Deck {
  private final int deckId;
  private final User owner;
  private final String deckName;
  private final ArrayList<Integer> cardIds;

  private Deck(Builder builder) {
    this.deckId = builder.deckId;
    // TODO Maybe replace with User ID
    this.owner = builder.owner;
    this.deckName = builder.deckName;
    this.cardIds = builder.cardIds;
  }

  public int getDeckId() {return deckId;}
  public User getOwner() {return owner;}
  public String getDeckName() {return deckName;}
  public ArrayList<Integer> getCardIds() {return cardIds;}
  public int getOwnerId() {return owner.getId();}

  public static class Builder {
    private final int deckId;
    private final User owner;
    private String deckName;
    private ArrayList<Integer> cardIds;

    public Builder(int deckId, User owner) {
      this.deckId = deckId;
      this.owner = owner;
    }

    public Builder deckName(String deckName) {
      this.deckName = deckName;
      return this;
    }

    public Builder cardIds(ArrayList<Integer> cardIds) {
      this.cardIds = cardIds;
      return this;
    }

    public Deck build() {
      return new Deck(this);
    }
  }
}
