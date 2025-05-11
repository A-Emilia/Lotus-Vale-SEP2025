package communication.request_handlers;

import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.GetLotusRequest;
import communication.services.card.CardService;

public class CardRequestHandler implements RequestHandler {
private final CardService cardService;

  public CardRequestHandler(CardService cardService) {
    this.cardService = cardService;
  }

  @Override
  public Object handle(String action, Object payload) {

    return switch (action) {
      case "get" -> cardService.getCard((GetCardRequest) payload);
      case "lotus" ->  cardService.getLotus((GetLotusRequest) payload);

      default -> throw new IllegalStateException("Unexpected value: " + action);
    };
  }
}
