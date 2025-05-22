package communication.requests.deck_requests;

import java.io.Serializable;
import java.util.ArrayList;

public record DeleteDeckRequest(int userId, ArrayList<Integer> deckIds) implements Serializable {
}
