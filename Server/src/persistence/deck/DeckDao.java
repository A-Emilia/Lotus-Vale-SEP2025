package persistence.deck;

import communication.ResponseType;
import communication.requests.deck_requests.create_deck.CreateDeckRequest;
import communication.requests.deck_requests.create_deck.CreateStandardDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;

import java.util.ArrayList;

public interface DeckDao {
  ArrayList<Deck> getDecks(GetDecksRequest payload);
  ResponseType createDeck(CreateDeckRequest payload);
  ResponseType deleteDeck(DeleteDeckRequest payload);
}
