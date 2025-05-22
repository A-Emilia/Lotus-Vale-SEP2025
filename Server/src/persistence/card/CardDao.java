package persistence.card;

import communication.ResponseType;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.EditCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.RemoveCardRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CardDao {
  ArrayList<Card> getCard(GetCardRequest req);
  ResponseType addCard(AddCardRequest req);
  ResponseType removeCard(RemoveCardRequest payload);
  ArrayList<Card> editCard(EditCardRequest payload);
}
