package networking.clients.card;

import communication.Response;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.RemoveCardRequest;
import model.entities.card.Card;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

public interface CardClient {
  Response getCard(GetCardRequest cardRequest) throws SocketTimeoutException;
  Response getCollection(GetCardRequest collectionRequest) throws SocketTimeoutException;
  Response addCard(AddCardRequest addCardRequest) throws SocketTimeoutException;
  Response removeCard(RemoveCardRequest removeCardRequest) throws SocketTimeoutException;
}
