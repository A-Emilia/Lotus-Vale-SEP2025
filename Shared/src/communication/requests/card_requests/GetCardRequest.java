package communication.requests.card_requests;

import java.io.Serializable;

public record GetCardRequest(String name, String setCode, String textContains) implements Serializable {

  // TODO: FUCK MY BRAIN

}
