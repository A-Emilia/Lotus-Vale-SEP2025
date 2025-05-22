package communication.services.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;
import persistence.deck.DeckDao;

import java.util.ArrayList;

public class DeckServiceImpl implements DeckService {
  private final DeckDao deckDao;

  public DeckServiceImpl(DeckDao deckDao) {
    this.deckDao = deckDao;
  }

  @Override
  public ArrayList<Deck> getDeck(GetDecksRequest req) {
    return deckDao.getDecks(req);
  }

  @Override
  public ResponseType createDeck(CreateDeckRequest req) {
    return deckDao.createDeck(req);
  }

  @Override
  public ResponseType deleteDeck(DeleteDeckRequest req) {
    return deckDao.deleteDeck(req);
  }
}
