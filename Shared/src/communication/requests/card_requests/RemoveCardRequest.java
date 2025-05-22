package communication.requests.card_requests;

import java.io.Serializable;
import java.util.ArrayList;

public record RemoveCardRequest(int userId, ArrayList<Integer> cardIds, String target) implements Serializable {
}
