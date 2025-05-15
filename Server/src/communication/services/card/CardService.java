package communication.services.card;

import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.GetLotusRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CardService {
  ArrayList<Card> getCard(GetCardRequest payload);
  Card getLotus(GetLotusRequest payload);

}
