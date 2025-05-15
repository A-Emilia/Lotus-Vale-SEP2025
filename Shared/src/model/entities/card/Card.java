package model.entities.card;

import model.entities.card.components.CardSupertype;
import model.entities.card.components.CardType;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Card implements Serializable {
  private final int id;
  private final String setCode;
  private final String name;
  // TODO Mana Cost
  private final String text;
  private final ArrayList<CardSupertype> supertype;
  private final ArrayList<CardType> cardType;
  private final ArrayList<String> subtype;
  private final Integer multiverseId;
  private final String imgUrl;

  private Card(Builder builder) {
    this.id = builder.id;
    this.setCode = builder.setCode;
    // TODO Mana cost
    this.name = builder.name;
    this.text = builder.text;
    this.multiverseId = builder.multiverseId;

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
  public int getMultiverseId() {return multiverseId;}
  public String getImgUrl() {return imgUrl;}

  @Override public String toString() {
    return "Card{" + "id=" + id + ", setCode='" + setCode + '\'' + ", name='"
        + name + '\'' + ", text='" + text + '\'' + ", supertype=" + supertype
        + ", cardType=" + cardType + ", subtype=" + subtype + ", multiverseId="
        + multiverseId + ", imgUrl='" + imgUrl + '\'' + '}';
  }

  public static ArrayList<Card> sqlToCards(ResultSet rs) throws SQLException {
    ArrayList<Card> cards = new ArrayList<>();

    while (rs.next()) {
      int id = rs.getInt("id");
      String setCode = rs.getString("setCode");
      String name = rs.getString("name");
      // Mana Cost
      String text = rs.getString("text");
      // CardSuperType
      // CardType
      // Subtype
      // url
      Integer multiverseId = rs.getInt("multiverseId");

      Card card = new Card.Builder(id, setCode)
          .name(name)
          //.manaCost()
          .text(text)
          //.superType()
          //.cardType()
          //.subtype()
          //.url()
          .multiverseId(multiverseId)
          .gathererImgUrl()
          .build();
      cards.add(card);
    }

    return cards;
  }

  public static class Builder {
    private final int id;
    private final String setCode;
    private String name;
    // TODO Mana Cost
    private String text;
    private ArrayList<CardSupertype> supertype;
    private ArrayList<CardType> cardType;
    private ArrayList<String> subtype;
    private Integer multiverseId;
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

    public Builder superTypeList(ArrayList<String> superType) {
      this.supertype = CardSupertype.toTypeList(superType);
      return this;
    }

    public Builder superType(String supertype) {
      if(this.supertype == null) {
        this.supertype = new ArrayList<>();
      }
      this.supertype.add(CardSupertype.toCardSuperType(supertype));
      return this;
    }

    public Builder cardTypeList(ArrayList<String> cardType) {
      this.cardType = CardType.toTypeList(cardType);
      return this;
    }

    public Builder cardType(String cardType) {
      if(this.cardType == null) {
        this.cardType = new ArrayList<>();
      }
      this.cardType.add(CardType.toCardType(cardType));
      return this;
    }

    public Builder subtypeList(ArrayList<String> subtype) {
      this.subtype = subtype;
      return this;
    }

    public Builder subtype(String subtype) {
      if(this.subtype == null) {
        this.subtype = new ArrayList<>();
      }
      this.subtype.add(subtype);
      return this;
    }

    public Builder multiverseId(int multiverseId) {
      this.multiverseId = multiverseId;
      return this;
    }

    public Builder imgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
      return this;
    }

    public Builder gathererImgUrl() {
      if (this.multiverseId == null) {multiverseId = 0;}
      this.imgUrl = "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + this.multiverseId + "&type=card";
      return this;
    }

    public Card build() {return new Card(this);}

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