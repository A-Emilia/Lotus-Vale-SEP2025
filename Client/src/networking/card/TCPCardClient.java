package networking.card;

import communication.Request;
import communication.RequestType;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.GetCardRequest;
import model.entities.card.Card;
import networking.SocketService;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class TCPCardClient implements CardClient {
  @Override
  public ArrayList<Card> getCard(GetCardRequest cardRequest) {
    Request request = new Request(RequestType.CARD, cardRequest);

    Object inc = SocketService.sendRequest(request);

    if (inc instanceof ArrayList<?> res) {
      for (Object obj : res) {
        if (!(obj instanceof Card)) {
          throw new ClassCastException();
        }
      }
      // Fuck you Java. This is not unchecked.
      return (ArrayList<Card>) res;
    }
    return null;
  }

  @Override
  public ArrayList<Card> getCollection(GetCardRequest collectionRequest) {
    Request request = new Request(RequestType.CARD, collectionRequest);

    Object inc = SocketService.sendRequest(request);

    if (inc instanceof ArrayList<?> res) {
      for (Object obj : res) {
        if (!(obj instanceof Card)) {
          throw new ClassCastException();
        }
      }
      // Fuck you Java. This is not unchecked.
      return (ArrayList<Card>) res;
    }
    return null;
  }

  @Override
  public void addCard(AddCardRequest addCardRequest) {
    Request request = new Request(RequestType.CARD, addCardRequest);

    SocketService.sendRequest(request);
  }
}
