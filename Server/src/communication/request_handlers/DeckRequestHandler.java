package communication.request_handlers;

import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import communication.services.deck.DeckService;

public class DeckRequestHandler implements RequestHandler {
  private final DeckService deckService;

  public DeckRequestHandler(DeckService deckService) {
    this.deckService = deckService;
  }

  @Override
  public Object handle(Object payload) {
    return switch (payload) {
      case GetDecksRequest req -> deckService.getDeck(req);
      case CreateDeckRequest req -> deckService.createDeck(req);
      case DeleteDeckRequest req -> deckService.deleteDeck(req);

      default ->
          throw new IllegalStateException("Unexpected value: " + payload);
    };
  }
}
