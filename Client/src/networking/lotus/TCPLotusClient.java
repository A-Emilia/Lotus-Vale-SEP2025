package networking.lotus;

import communication.Request;
import communication.RequestType;
import communication.requests.card_requests.GetLotusRequest;
import model.entities.card.Card;
import networking.SocketService;

public class TCPLotusClient implements LotusClient {


  @Override
  public Card getLotus(GetLotusRequest lotusRequest) {
    Request request = new Request(RequestType.LOTUS, lotusRequest);
    System.out.println("TCPLotusClient: " + request);
    return (Card) SocketService.sendRequest(request);
  }
}
