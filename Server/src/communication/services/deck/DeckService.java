package communication.services.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;

import java.util.ArrayList;

public interface DeckService {

  // TODO Make return proper data types
  ArrayList<Deck> getDeck(GetDecksRequest req);
  ResponseType createDeck(CreateDeckRequest req);
  ResponseType deleteDeck(DeleteDeckRequest req);
}
