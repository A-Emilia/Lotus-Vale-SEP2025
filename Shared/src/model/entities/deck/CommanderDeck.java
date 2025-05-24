package model.entities.deck;

import model.entities.card.Card;
import model.entities.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommanderDeck implements Deck {
  private final int deckId;
  private final User owner;
  private final ArrayList<Card> commanders;
  private final String deckName;
  private final ArrayList<Card> cards;


  private CommanderDeck(Builder builder) {
    this.deckId = builder.deckId;
    this.owner = builder.owner;
    this.commanders = builder.commanders;
    this.deckName = builder.deckName;
    this.cards = builder.cards;
  }


  @Override public int getDeckId() {return deckId;}
  @Override public User getOwner() {return owner;}
  @Override public String getDeckName() {return deckName;}
  @Override public ArrayList<Card> getCards() {return cards;}
  @Override public int getOwnerId() {return owner.getId();}

  public ArrayList<Card> getCommanders() {return commanders;}

  public static ArrayList<CommanderDeck> sqlToDeck(ResultSet rs) throws SQLException {
    ArrayList<CommanderDeck> decks = new ArrayList<>();
    while (rs.next()) {
      int deckId = rs.getInt("id");
      int ownerId = rs.getInt("owner_id");

      String username = rs.getString("username");
      String deckName = rs.getString("deck_name");

      User user = new User.Builder(ownerId)
          .username(username)
          .build();

      // TODO FIGURE OUT WHAT TO DO WITH THE CARD THAT NEEDS TO BE ADDED
      CommanderDeck deck = new Builder(deckId, user, null)
          .deckName(deckName)
          .build();

      decks.add(deck);
    }

    return decks;
  }

  public static class Builder {
    private final int deckId;
    private final User owner;
    private final ArrayList<Card> commanders;
    private String deckName;
    private ArrayList<Card> cards;

    public Builder(int deckId, User owner, ArrayList<Card> deckCommanders) {
      this.deckId = deckId;
      this.owner = owner;
      this.commanders = new ArrayList<>();

      commanders.addAll(deckCommanders);
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

    public CommanderDeck build() {
      return new CommanderDeck(this);
    }
  }
}
