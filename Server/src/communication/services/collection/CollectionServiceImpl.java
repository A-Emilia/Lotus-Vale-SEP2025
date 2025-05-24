package communication.services.collection;

import communication.ResponseType;
import communication.requests.collection_requests.CreateSubCollectionRequest;
import communication.requests.collection_requests.DeleteSubCollectionRequest;
import model.entities.card.Card;
import persistence.collection.CollectionDao;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CollectionServiceImpl implements CollectionService {
  private final CollectionDao collectionDao;

  public CollectionServiceImpl(CollectionDao collectionDao) throws NoSuchElementException {
    this.collectionDao = collectionDao;
  }

  @Override
  public Object createSubCollection(CreateSubCollectionRequest req) {
    return null;
  }

  @Override
  public ResponseType deleteSubCollection(DeleteSubCollectionRequest req) {
    return null;
  }
}
