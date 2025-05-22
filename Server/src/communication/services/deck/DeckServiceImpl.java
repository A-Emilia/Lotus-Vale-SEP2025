package communication.services.deck;

import communication.ResponseType;
import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDeckRequest;
import persistence.deck.DeckDao;

public class DeckServiceImpl implements DeckService {
  private final DeckDao deckDao;

  public DeckServiceImpl(DeckDao deckDao) {
    this.deckDao = deckDao;
  }

  @Override
  public Object getDeck(GetDeckRequest req) {
    return null;
  }

  @Override
  public Object createDeck(CreateDeckRequest req)
  {
    return null;
  }

  @Override
  public ResponseType deleteDeck(DeleteDeckRequest req)
  {
    return null;
  }
}
