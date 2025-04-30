package model.entities.card;

import model.entities.card.components.CardSupertype;
import model.entities.card.components.CardType;

import java.util.ArrayList;
import java.util.List;

public class Card {
  private final int id;
  private final String setCode;
  private final String name;
  // TODO Mana Cost
  private final String text;
  private final ArrayList<CardSupertype> supertype;
  private final ArrayList<CardType> cardType;
  private final ArrayList<String> subtype;
  private final String imgUrl;

  private Card(Builder builder) {
    this.id = builder.id;
    this.setCode = builder.setCode;
    // TODO Mana cost
    this.name = builder.name;
    this.text = builder.text;

    // Types
    this.supertype = builder.supertype;
    this.cardType = builder.cardType;
    this.subtype = builder.subtype;
    this.imgUrl = builder.imgUrl;
  }

  public int getId() {return id;}
  public String getSetCode() {return setCode;}
  public String getName() {return name;}
  public String getText() {return text;}
  public ArrayList<CardSupertype> getSupertype() {return supertype;}
  public ArrayList<CardType> getCardType() {return cardType;}
  public ArrayList<String> getSubtype() {return subtype;}
  public String getImgUrl() {return imgUrl;}




  public static class Builder {
    private final int id;
    private final String setCode;
    private String name;
    // TODO Mana Cost
    private String text;
    private ArrayList<CardSupertype> supertype;
    private ArrayList<CardType> cardType;
    private ArrayList<String> subtype;
    private String imgUrl;

    public Builder(int id, String setCode) {
      this.id = id;
      this.setCode = setCode;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder manaCost() {
      // TODO
      return this;
    }

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public Builder superType(ArrayList<String> superType) {
      this.supertype = CardSupertype.toTypeList(superType);
      return this;
    }

    public Builder cardType(ArrayList<String> cardType) {
      this.cardType = CardType.toTypeList(cardType);
      return this;
    }

    public Builder subtype(ArrayList<String> subtype) {
      this.subtype = subtype;
      return this;
    }

    public Builder imgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
      return this;
    }

    public Card build() {
      return new Card(this);
    }

    /*
    public Builder(int id,
        String setCode,
        String name,
        // TODO Mana Cost
        String text,
        ArrayList<String> superType,
        ArrayList<String> cardType,
        ArrayList<String> subtype) {
      this.id = id;
      this.setCode = setCode;
      this.name = name;
      // TODO Mana Cost
      this.text = text;

      // Types
      this.supertype = CardSupertype.toTypeList(superType);
      this.cardType = CardType.toTypeList(cardType);
      this.subtype = subtype;
    }
     */
  }
}