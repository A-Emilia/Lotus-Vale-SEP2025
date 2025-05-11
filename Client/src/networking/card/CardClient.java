package networking.card;

import communication.requests.card_requests.GetCardRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CardClient {
  ArrayList<Card> getCard(GetCardRequest cardRequest);
}
