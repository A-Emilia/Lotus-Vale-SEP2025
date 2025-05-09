package communication.services.card;

import persistence.card.CardDao;

public class CardServiceImpl implements CardService {
  private CardDao cardDao;

  public CardServiceImpl(CardDao cardDao) {
    this.cardDao = cardDao;
  }
}
