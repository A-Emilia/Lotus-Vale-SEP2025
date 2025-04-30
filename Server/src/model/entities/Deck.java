package model.entities;

import java.util.ArrayList;

public class Deck {
  private final int deckId;
  private final String owner;
  private final String deckName;
  private final ArrayList<Integer> cardIds;

  private Deck(Builder builder) {
    this.deckId = builder.deckId;
    // TODO Replace Owner with User later.
    this.owner = builder.owner;
    this.deckName = builder.deckName;
    this.cardIds = builder.cardIds;
  }

  public int getDeckId() {return deckId;}
  public String getOwner() {return owner;}
  public String getDeckName() {return deckName;}
  public ArrayList<Integer> getCardIds() {return cardIds;}

  public static class Builder {
    private final int deckId;
    private final String owner;
    private String deckName;
    private ArrayList<Integer> cardIds;

    public Builder(int deckId, String owner) {
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
