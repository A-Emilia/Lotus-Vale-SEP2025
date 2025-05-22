package communication.requests.collection_requests;

import java.io.Serializable;

public record DeleteSubCollectionRequest(int userId) implements Serializable {
}
