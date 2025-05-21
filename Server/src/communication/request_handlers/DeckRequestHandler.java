package communication.request_handlers;

import communication.services.deck.DeckService;

public class DeckRequestHandler implements RequestHandler {
  private DeckService deckService;

  public DeckRequestHandler(DeckService deckService) {
    this.deckService = deckService;
  }

  @Override
  public Object handle(Object payload) {
    return null;
  }
}
