package communication.requests.deck_requests;

import java.io.Serializable;

public record DeleteDeckRequest(int userId) implements Serializable {
}
