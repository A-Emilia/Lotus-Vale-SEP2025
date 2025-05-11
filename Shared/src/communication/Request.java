package communication;

import java.io.Serializable;

public record Request(RequestType type, String action, Object payload) implements Serializable {
}
