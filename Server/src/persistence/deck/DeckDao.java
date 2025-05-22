package persistence.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDeckRequest;

public interface DeckDao {

  // TODO Make return proper data types
  Object getDeck(GetDeckRequest payload);
  Object createDeck(CreateDeckRequest payload);
  ResponseType deleteDeck(DeleteDeckRequest payload);
}
