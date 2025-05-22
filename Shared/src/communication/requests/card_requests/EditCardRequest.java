package communication.requests.card_requests;

import java.io.Serializable;

public record EditCardRequest(int userId, int cardId, int editToId) implements Serializable {
}
