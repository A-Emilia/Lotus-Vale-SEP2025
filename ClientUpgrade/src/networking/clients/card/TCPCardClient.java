package networking.clients.card;

import communication.Request;
import communication.RequestType;
import communication.Response;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.RemoveCardRequest;
import model.entities.card.Card;
import networking.SocketClient;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class TCPCardClient implements CardClient {

  @Override
  public Response getCard(GetCardRequest cardRequest) throws SocketTimeoutException {
    Request request = new Request(RequestType.CARD, cardRequest);
    return SocketClient.sendRequest(request);
  }

  @Override
  public Response getCollection(GetCardRequest collectionRequest) throws SocketTimeoutException {
    Request request = new Request(RequestType.CARD, collectionRequest);
    return SocketClient.sendRequest(request);
  }

  @Override
  public Response addCard(AddCardRequest addCardRequest) throws SocketTimeoutException {
    Request request = new Request(RequestType.CARD, addCardRequest);
    return SocketClient.sendRequest(request);
  }

  @Override
  public Response removeCard(RemoveCardRequest removeCardRequest) throws SocketTimeoutException {
    Request request = new Request(RequestType.CARD, removeCardRequest);
    return SocketClient.sendRequest(request);
  }
}
