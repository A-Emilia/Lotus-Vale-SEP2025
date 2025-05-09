package startup;

import communication.request_handlers.CardRequestHandler;
import communication.request_handlers.RequestHandler;
import communication.request_handlers.UserRequestHandler;
import communication.services.card.CardService;
import communication.services.card.CardServiceImpl;
import communication.services.user.UserService;
import communication.services.user.UserServiceImpl;
import persistence.card.CardDao;
import persistence.card.CardMySQLDao;
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
}
