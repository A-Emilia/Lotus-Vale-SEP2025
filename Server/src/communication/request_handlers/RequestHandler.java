package communication.request_handlers;

public interface RequestHandler {
  Object handle(String action, Object payload);
}
