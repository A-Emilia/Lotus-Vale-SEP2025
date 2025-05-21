package communication.request_handlers;

import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.GetLotusRequest;
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
      case GetLotusRequest req ->  cardService.getLotus(req);
      case AddCardRequest req -> cardService.addCard(req);

      default -> throw new IllegalStateException("Error");
    };
  }
}
