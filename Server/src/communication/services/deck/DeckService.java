package communication.services.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDeckRequest;

public interface DeckService {

  // TODO Make return proper data types
  Object getDeck(GetDeckRequest req);
  Object createDeck(CreateDeckRequest req);
  ResponseType deleteDeck(DeleteDeckRequest req);
}
