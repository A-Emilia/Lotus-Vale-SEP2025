package communication.request_handlers;

import communication.requests.card_requests.*;
import communication.services.card.CardService;

public class CardRequestHandler implements RequestHandler {
private final CardService cardService;

  public CardRequestHandler(CardService cardService) {
    this.cardService = cardService;
  }

  @Override
  public Object handle(Object payload) {

    return switch (payload) {
      case GetCardRequest req -> cardService.getCard(req);
      case AddCardRequest req -> cardService.addCard(req);
      case RemoveCardRequest req -> cardService.removeCard(req);
      case EditCardRequest req -> cardService.editCard(req);

      default -> throw new IllegalStateException("Error");
    };
  }
}
