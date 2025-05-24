package communication.requests.card_requests;

import communication.requests.card_requests.target.CollectionTarget;

import java.io.Serializable;
import java.util.ArrayList;

public record AddCardRequest(int userId, ArrayList<Integer> cardIds, CollectionTarget target) implements Serializable {
}
