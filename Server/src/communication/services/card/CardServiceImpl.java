package communication.services.card;

import model.entities.card.Card;
import persistence.card.CardDao;

public class CardServiceImpl implements CardService {
  private CardDao cardDao;

  public CardServiceImpl(CardDao cardDao) {
    this.cardDao = cardDao;
  }

  @Override
  public Card getCard(Object payload) {
    return null;
  }
}
