package communication.requests.deck_requests.target;

import java.io.Serializable;

public record DeckTarget(TargetType targetType, Integer targetId) implements Serializable {
}
