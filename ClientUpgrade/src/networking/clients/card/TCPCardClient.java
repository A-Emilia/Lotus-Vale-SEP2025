package networking.clients.card;

import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.RemoveCardRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public class TCPCardClient implements CardClient {
  @Override public ArrayList<Card> getCard(GetCardRequest cardRequest)
  {
    return null;
  }

  @Override public ArrayList<Card> getCollection(
      GetCardRequest collectionRequest)
  {
    return null;
  }

  @Override public void addCard(AddCardRequest addCardRequest)
  {

  }

  @Override public void removeCard(RemoveCardRequest removeCardRequest)
  {

  }
}
