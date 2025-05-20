package communication.requests.user_requests;

import java.io.Serializable;

public record RegisterRequest(String username, String password) implements Serializable {
}
