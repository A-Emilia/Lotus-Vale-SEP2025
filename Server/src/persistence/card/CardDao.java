package persistence.card;

import communication.requests.card_requests.GetCardRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CardDao {
  ArrayList<Card> getCard(GetCardRequest card);
}
