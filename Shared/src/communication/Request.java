package communication;

import java.io.Serializable;

public record Request(RequestType type, Object payload) implements Serializable {
}
