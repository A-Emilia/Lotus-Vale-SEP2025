package persistence.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDeckRequest;

public class DeckMySQLDao implements DeckDao {
  @Override
  public Object getDeck(GetDeckRequest payload) {
    return null;
  }

  @Override
  public Object createDeck(CreateDeckRequest payload) {
    return null;
  }

  @Override
  public ResponseType deleteDeck(DeleteDeckRequest payload) {
    return null;
  }
}
