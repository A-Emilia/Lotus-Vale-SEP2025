package communication.request_handlers;

import communication.requests.collection_requests.GetCollectionRequest;
import communication.services.collection.CollectionService;

public class CollectionRequestHandler implements RequestHandler {
  private CollectionService collectionService;

  public CollectionRequestHandler(CollectionService collectionService) {
    this.collectionService = collectionService;
  }

  @Override
  public Object handle(String action, Object payload) {
    return switch (action) {
      case "get" -> collectionService.getCollection((GetCollectionRequest) payload);
      case "update" -> null;

      default -> throw new IllegalStateException("Unexpected value: " + action);
    };
  }
}
