package communication.requests.user_requests;

import java.io.Serializable;

public record LoginRequest(String username, String password) implements Serializable {
}
