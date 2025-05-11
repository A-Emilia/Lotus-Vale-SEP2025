package communication.services.card;

import communication.requests.card_requests.GetLotusRequest;
import model.entities.card.Card;

public interface CardService {
  Card getCard(Object payload);
  Card getLotus(GetLotusRequest request);

}
