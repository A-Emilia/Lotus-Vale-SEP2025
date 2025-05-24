package communication.requests.deck_requests;

import communication.requests.deck_requests.target.DeckTarget;

import java.io.Serializable;

public record GetDecksRequest(Integer userId, DeckTarget targetType) implements Serializable {
}
