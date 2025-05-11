package networking.card;

import communication.Request;
import communication.RequestType;
import communication.requests.card_requests.GetCardRequest;
import model.entities.card.Card;
import networking.SocketService;

import java.util.ArrayList;

public class TCPCardClient implements CardClient {
  @Override
  public ArrayList<Card> getCard(GetCardRequest cardRequest) {
    Request request = new Request(RequestType.CARD, "get", cardRequest);

    ArrayList<Card> res = new ArrayList<>();
    // TODO Add checks
    res = (ArrayList<Card>) SocketService.sendRequest(request);

    return res;
  }
}
