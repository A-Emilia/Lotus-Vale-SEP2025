package communication.requests.deck_requests;

import java.io.Serializable;

public record CreateDeckRequest(int userId, String deckName) implements Serializable {
}
