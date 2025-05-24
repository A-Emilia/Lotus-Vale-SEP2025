package communication.requests.card_requests;

import communication.requests.card_requests.target.CollectionTarget;

import java.io.Serializable;

public record GetCardRequest(CollectionTarget target, String name, String setCode, String textContains) implements Serializable {

  // TODO: FUCK MY BRAIN

}
