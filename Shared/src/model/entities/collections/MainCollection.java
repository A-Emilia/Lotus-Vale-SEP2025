package model.entities.collections;

import model.entities.card.Card;
import model.entities.user.User;

import java.io.Serializable;
import java.util.ArrayList;

public class MainCollection implements Serializable, CardCollection {
  private final User owner;
  private final ArrayList<Card> cards;
  private final ArrayList<SubCollection> subCollections;

  MainCollection(Builder builder) {
    this.owner = builder.owner;
    this.cards = builder.cards;
    this.subCollections = builder.subCollections;
  }

  public User getOwner() {return owner;}
  public ArrayList<Card> getCards() {return cards;}
  public int getOwnerId() {return owner.getId();}
  public int getTotalCards() {return cards.size();}
  public ArrayList<SubCollection> getSubCollections() {return subCollections;}
  public int getSubCollectionAmount() {return subCollections.size();}

  // TODO Functions for getting a specific subcollection.
  // TODO Function for removing a card and removing it from all subcollections.

  @Override public String toString() {
    return "MainCollection{" + "owner=" + owner + ", cards=" + cards + '}';
  }

  @Override public void addSubCollection(CardCollection child) {
    subCollections.add((SubCollection) child);
  }

  public static class Builder {
    final User owner;
    ArrayList<Card> cards;
    ArrayList<SubCollection> subCollections;

    public Builder(User owner) {
      this.owner = owner;
      this.cards = new ArrayList<>();
      this.subCollections = new ArrayList<>();
    }

    public Builder card(Card card) {
      this.cards.add(card);
      return this;
    }

    public Builder cardList(ArrayList<Card> cards) {
      this.cards = cards;
      return this;
    }

    public MainCollection build() {return new MainCollection(this);}
  }
}


