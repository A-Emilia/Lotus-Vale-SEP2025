package communication.request_handlers;

import communication.services.collection.CollectionService;

public class CollectionRequestHandler implements RequestHandler {
  private CollectionService collectionService;

  public CollectionRequestHandler(CollectionService collectionService) {
    this.collectionService = collectionService;
  }

  @Override
  public Object handle(String action, Object payload) {
    return null;
  }
}
