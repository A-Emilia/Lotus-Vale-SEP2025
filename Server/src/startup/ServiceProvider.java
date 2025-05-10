package startup;

import communication.request_handlers.*;
import communication.services.card.CardService;
import communication.services.card.CardServiceImpl;
import communication.services.collection.CollectionService;
import communication.services.collection.CollectionServiceImpl;
import communication.services.deck.DeckService;
import communication.services.deck.DeckServiceImpl;
import communication.services.user.UserService;
import communication.services.user.UserServiceImpl;
import persistence.card.CardDao;
import persistence.card.CardMySQLDao;
import persistence.collection.CollectionDao;
import persistence.collection.CollectionMySQLDao;
import persistence.deck.DeckDao;
import persistence.deck.DeckMySQLDao;
import persistence.user.UserDao;
import persistence.user.UserMySQLDao;

public class ServiceProvider {

  public static RequestHandler getCardRequestHandler() {
    return new CardRequestHandler(getCardService());
  }

  private static CardService getCardService() {
    return new CardServiceImpl(getCardDao());
  }

  private static CardDao getCardDao() {
    return new CardMySQLDao();
  }



  public static RequestHandler getUserRequestHandler() {
    return new UserRequestHandler(getUserService());
  }

  private static UserService getUserService() {
    return new UserServiceImpl(getUserDao());
  }

  private static UserDao getUserDao() {
    return new UserMySQLDao();
  }



  public static RequestHandler getDeckRequestHandler() {
    return new DeckRequestHandler(getDeckService());
  }

  private static DeckService getDeckService() {
    return new DeckServiceImpl(getDeckDao());
  }

  public static DeckDao getDeckDao() {
    return new DeckMySQLDao();
  }



  public static RequestHandler getCollectionRequestHandler() {
    return new CollectionRequestHandler(getCollectionService());
  }

  private static CollectionService getCollectionService() {
    return new CollectionServiceImpl(getCollectionDao());
  }

  private static CollectionDao getCollectionDao() {
    return new CollectionMySQLDao();
  }
}
