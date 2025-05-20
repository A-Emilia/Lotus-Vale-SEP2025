package communication.services.collection;

import communication.requests.collection_requests.GetCollectionRequest;
import model.entities.card.Card;
import persistence.collection.CollectionDao;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CollectionServiceImpl implements CollectionService {
  private CollectionDao collectionDao;

  public CollectionServiceImpl(CollectionDao collectionDao) throws NoSuchElementException {
    this.collectionDao = collectionDao;
  }

  @Override
  public ArrayList<Card> getCollection(GetCollectionRequest payload) {
    ArrayList<Card> res = collectionDao.getCollection(payload);

    if (res.isEmpty()) {
      throw new NoSuchElementException("No cards found.");
    }

    return res;
  }
}
