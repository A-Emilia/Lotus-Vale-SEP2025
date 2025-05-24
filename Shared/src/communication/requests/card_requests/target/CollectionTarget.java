package communication.requests.card_requests.target;

import java.io.Serializable;

public record CollectionTarget(TargetType targetType, Integer targetId, Integer userId) implements Serializable {
}
