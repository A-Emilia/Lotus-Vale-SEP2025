package communication.services.deck;

import communication.ResponseType;
import communication.requests.deck_requests.create_deck.CreateStandardDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;

import java.util.ArrayList;

public interface DeckService {

  // TODO Make return proper data types
  ArrayList<Deck> getDeck(GetDecksRequest req);
  ResponseType createDeck(CreateStandardDeckRequest req);
  ResponseType deleteDeck(DeleteDeckRequest req);
}
