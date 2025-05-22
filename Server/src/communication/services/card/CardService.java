package communication.services.card;

import communication.ResponseType;
import communication.requests.card_requests.*;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CardService {
  ArrayList<Card> getCard(GetCardRequest payload);
  ResponseType addCard(AddCardRequest payload);
  ResponseType removeCard(RemoveCardRequest payload);
  ArrayList<Card> editCard(EditCardRequest payload);
}
