import communication.requests.deck_requests.CreateDeckRequest;
import communication.requests.deck_requests.DeleteDeckRequest;
import communication.requests.deck_requests.GetDecksRequest;
import model.entities.deck.Deck;
import persistence.deck.DeckMySQLDao;

import java.util.ArrayList;

public class MySqlDeckTests {

  public static void main(String[] args) {

    //createAsteroidDestroyer();
    //getAsteroidDestroyer();
    //destroyAsteroidDestroyer();



  }

  public static void createAsteroidDestroyer() {
    CreateDeckRequest req = new CreateDeckRequest(1, "Asteroid Destroyer");
    DeckMySQLDao dao = new DeckMySQLDao();
    dao.createDeck(req);
  }

  public static void getAsteroidDestroyer() {
    GetDecksRequest req = new GetDecksRequest(1);
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
