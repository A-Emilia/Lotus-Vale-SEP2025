package communication.requests.collection_requests;

import java.io.Serializable;

public record GetCollectionRequest(int userId) implements Serializable {
}
