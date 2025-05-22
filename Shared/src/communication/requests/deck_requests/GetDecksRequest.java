package communication.requests.deck_requests;

import java.io.Serializable;

public record GetDecksRequest(Integer userId) implements Serializable {
}
