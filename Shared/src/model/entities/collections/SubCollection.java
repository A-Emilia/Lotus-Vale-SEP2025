package model.entities.collections;

import model.entities.card.Card;

import java.io.Serializable;
import java.util.ArrayList;

public class SubCollection implements Serializable, CardCollection {

  // TODO Consider only making SubCollection constructable from Main_Collection.
  // TODO Not sure what to do with subcollections of subcollections then though.
  // TODO Add logic for parent collection ID and for storing subcollections.

  private final ArrayList<Card> cards;
  private ArrayList<SubCollection> subCollections;
  // Rule
  // Parent

  private SubCollection(Builder builder) {
    this.cards = builder.cards;
    this.subCollections = null;
  }

  public ArrayList<Card> getCards() {return cards;}

  @Override public void addSubCollection(CardCollection child) {
    if(this.subCollections == null) {
      this.subCollections = new ArrayList<>();
    }
    subCollections.add((SubCollection) child);
  }

  public static class Builder {
    private final ArrayList<Card> cards;
    private final CardCollection parent;

    public Builder(CardCollection parent) {
      this.parent = parent;
      this.cards = new ArrayList<>();
    }

    public Builder card(Card card) {
      cards.add(card);
      return this;
    }

    public Builder cardList(ArrayList<Card> cards) {
      this.cards.addAll(cards);
      return this;
    }

    public SubCollection build() {
      SubCollection res = new SubCollection(this);
      parent.addSubCollection(res);

      return res;
    }
  }
}
