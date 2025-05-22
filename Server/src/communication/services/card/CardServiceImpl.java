package communication.services.card;

import communication.ResponseType;
import communication.requests.card_requests.*;
import model.entities.card.Card;
import networking.DatabaseConnector;
import persistence.card.CardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CardServiceImpl implements CardService {
  private final CardDao cardDao;

  public CardServiceImpl(CardDao cardDao) {
    this.cardDao = cardDao;
  }

  @Override
  public ArrayList<Card> getCard(GetCardRequest payload) throws NoSuchElementException {
    ArrayList<Card> res = cardDao.getCard(payload);
    // Convert to some sort of DTO? I feel I definitely do not need it.
    // Error handling.

    if (res.isEmpty()) {
      throw new NoSuchElementException("No cards found.");
    }

    return res;
  }

  @Override
  public ResponseType addCard(AddCardRequest payload) {
    return cardDao.addCard(payload);
  }

  @Override public
  ResponseType removeCard(RemoveCardRequest payload) {
    return cardDao.removeCard(payload);
  }

  @Override
  public ArrayList<Card> editCard(EditCardRequest payload) {
    return null;
  }
}
