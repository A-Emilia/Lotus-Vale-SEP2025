package communication.services.collection;

import communication.ResponseType;
import communication.requests.collection_requests.CreateSubCollectionRequest;
import communication.requests.collection_requests.DeleteSubCollectionRequest;
import communication.requests.collection_requests.GetCollectionRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CollectionService {
  ArrayList<Card> getCollection(GetCollectionRequest payload);

  // TODO Make return appropriate object later.
  Object createSubCollection(CreateSubCollectionRequest payload);
  ResponseType deleteSubCollection(DeleteSubCollectionRequest payload);
}
