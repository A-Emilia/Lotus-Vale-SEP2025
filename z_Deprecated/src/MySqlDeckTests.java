import communication.requests.deck_requests.create_deck.CreateCommanderDeckRequest;
import communication.requests.deck_requests.create_deck.CreateStandardDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import communication.requests.deck_requests.target.DeckTarget;
import communication.requests.deck_requests.target.TargetType;
import model.entities.deck.Deck;
import persistence.deck.DeckMySQLDao;

import java.util.ArrayList;

public class MySqlDeckTests {

  public static void main(String[] args) {

    //createAsteroidDestroyer();
    //createAsteroidDestroyerTheCommander();
    //getAsteroidDestroyer();
    //destroyAsteroidDestroyer();



  }

  public static void createAsteroidDestroyer() {
    CreateStandardDeckRequest req = new CreateStandardDeckRequest(1, "Asteroid Destroyer");
    DeckMySQLDao dao = new DeckMySQLDao();
    dao.createDeck(req);
  }

  public static void createAsteroidDestroyerTheCommander() {
    ArrayList<Integer> meowCommander = new ArrayList<>();
    meowCommander.add(6969);
    CreateCommanderDeckRequest req = new CreateCommanderDeckRequest(1, "Asteroid Destroyer: The Commander", meowCommander);
    DeckMySQLDao dao = new DeckMySQLDao();
    dao.createDeck(req);
  }

  public static void getAsteroidDestroyer() {
    GetDecksRequest req = new GetDecksRequest(1, new DeckTarget(TargetType.PERSONAL, 1));
    DeckMySQLDao dao = new DeckMySQLDao();
    ArrayList<Deck> asteroidDestroyers = dao.getDecks(req);
    System.out.println(asteroidDestroyers.getFirst().getDeckName());
  }

  public static void destroyAsteroidDestroyer() {
    ArrayList<Integer> asteroidDestroyed = new ArrayList<>();
    asteroidDestroyed.add(2);
    DeleteDeckRequest req = new DeleteDeckRequest(1, asteroidDestroyed);
    DeckMySQLDao dao = new DeckMySQLDao();
    dao.deleteDeck(req);
  }
}
