package communication.services.collection;

import persistence.collection.CollectionDao;

public class CollectionServiceImpl implements CollectionService {
  private CollectionDao collectionDao;

  public CollectionServiceImpl(CollectionDao collectionDao) {
    this.collectionDao = collectionDao;
  }
}
