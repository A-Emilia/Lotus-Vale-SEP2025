package communication.request_handlers;

import communication.services.card.CardService;

public class CardRequestHandler implements RequestHandler {
private final CardService cardService;

  public CardRequestHandler(CardService cardService) {
    this.cardService = cardService;
  }

  @Override
  public Object handle(String action, Object payload) {

    switch (action) {
      case "get" -> cardService.getCard(payload);

    }

    return null;
  }
}
