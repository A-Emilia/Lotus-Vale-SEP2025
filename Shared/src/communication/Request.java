package communication;

public record Request(RequestType type, String action, Object payload) {
}
