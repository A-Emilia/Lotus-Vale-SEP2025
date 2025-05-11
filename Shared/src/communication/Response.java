package communication;

import java.io.Serializable;

public record Response(ResponseType type, Object payload) implements Serializable {
}
