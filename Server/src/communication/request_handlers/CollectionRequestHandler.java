package communication.request_handlers;

import communication.requests.collection_requests.GetCollectionRequest;
import communication.services.collection.CollectionService;

public class CollectionRequestHandler implements RequestHandler {
  private CollectionService collectionService;

  public CollectionRequestHandler(CollectionService collectionService) {
    this.collectionService = collectionService;
  }

  @Override
  public Object handle(Object payload) {
    return switch (payload) {
      case GetCollectionRequest req -> collectionService.getCollection(req);

      default -> throw new IllegalStateException("Error");
    };
  }
}
