package communication.requests.collection_requests;

import java.io.Serializable;

public record CreateSubCollectionRequest(int userID) implements Serializable {
}
