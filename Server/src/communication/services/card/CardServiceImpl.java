package communication.services.card;

import communication.Response;
import communication.ResponseType;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.EditCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.RemoveCardRequest;
import model.entities.card.Card;
import persistence.card.CardDao;

import java.util.ArrayList;

public class CardServiceImpl implements CardService
{
  private final CardDao cardDao;

  public CardServiceImpl(CardDao cardDao) {
    this.cardDao = cardDao;
  }

  @Override public Response getCard(GetCardRequest payload) {
    ArrayList<Card> res = cardDao.getCard(payload);
    // Convert to some sort of DTO? I feel I definitely do not need it.
    // Error handling.

    if (res.isEmpty()) {
      return new Response(ResponseType.ERROR, "No Cards Found");
    }

    return new Response(ResponseType.OK, res);
  }

  @Override public Response addCard(AddCardRequest payload)
  {
    return null;
  }

  @Override public Response removeCard(RemoveCardRequest payload)
  {
    return null;
  }

  @Override public Response editCard(EditCardRequest payload)
  {
    return null;
  }
}
