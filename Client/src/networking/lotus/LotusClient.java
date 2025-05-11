package networking.lotus;

import communication.requests.card_requests.GetLotusRequest;
import model.entities.card.Card;

public interface LotusClient {
  Card getLotus(GetLotusRequest lotusRequest);
}
