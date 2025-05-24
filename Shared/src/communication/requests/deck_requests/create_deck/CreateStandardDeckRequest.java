package communication.requests.deck_requests.create_deck;

import communication.requests.deck_requests.target.TargetType;

import java.io.Serializable;

public record CreateStandardDeckRequest(int userId, String deckName) implements Serializable, CreateDeckRequest {
  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public String getDeckName() {
    return deckName;
  }
}
