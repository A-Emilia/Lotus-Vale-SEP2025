package persistence.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;

import java.util.ArrayList;

public interface DeckDao {

  // TODO Make return proper data types
  ArrayList<Deck> getDecks(GetDecksRequest payload);
  ResponseType createDeck(CreateDeckRequest payload);
  ResponseType deleteDeck(DeleteDeckRequest payload);
}
