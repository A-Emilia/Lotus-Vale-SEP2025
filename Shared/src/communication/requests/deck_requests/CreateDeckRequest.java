package communication.requests.deck_requests;

import java.io.Serializable;

public record CreateDeckRequest(int userId) implements Serializable {
}
