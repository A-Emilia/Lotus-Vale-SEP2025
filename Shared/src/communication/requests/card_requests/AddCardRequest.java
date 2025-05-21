package communication.requests.card_requests;

import java.io.Serializable;
import java.util.ArrayList;

public record AddCardRequest(int userId, ArrayList<Integer> cardIds, String target) implements Serializable {
}
