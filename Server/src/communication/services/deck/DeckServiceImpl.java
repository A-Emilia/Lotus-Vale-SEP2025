package communication.services.deck;

import persistence.deck.DeckDao;

public class DeckServiceImpl implements DeckService {
  private DeckDao deckDao;

  public DeckServiceImpl(DeckDao deckDao) {
    this.deckDao = deckDao;
  }
}
