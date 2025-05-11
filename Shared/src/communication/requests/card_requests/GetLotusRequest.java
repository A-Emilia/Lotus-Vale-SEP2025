package communication.requests.card_requests;

import java.io.Serializable;

public record GetLotusRequest(String cardName, String set_code) implements Serializable {
}
