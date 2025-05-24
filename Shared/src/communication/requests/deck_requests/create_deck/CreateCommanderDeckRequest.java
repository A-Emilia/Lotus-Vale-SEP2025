package communication.requests.deck_requests.create_deck;

import java.io.Serializable;
import java.util.List;

public record CreateCommanderDeckRequest(int userId, String deckName, List<Integer> commanderCardIds) implements Serializable, CreateDeckRequest {
  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public String getDeckName() {
    return deckName;
  }
}
