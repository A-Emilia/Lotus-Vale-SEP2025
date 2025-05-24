package communication.request_handlers;

import communication.requests.collection_requests.CreateSubCollectionRequest;
import communication.requests.collection_requests.DeleteSubCollectionRequest;
import communication.services.collection.CollectionService;

public class CollectionRequestHandler implements RequestHandler {
  private final CollectionService collectionService;

  public CollectionRequestHandler(CollectionService collectionService) {
    this.collectionService = collectionService;
  }

  @Override
  public Object handle(Object payload) {
    return switch (payload) {
      case CreateSubCollectionRequest req -> collectionService.createSubCollection(req);
      case DeleteSubCollectionRequest req -> collectionService.deleteSubCollection(req);


      default -> throw new IllegalStateException("Error");
    };
  }
}
