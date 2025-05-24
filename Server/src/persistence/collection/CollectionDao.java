package persistence.collection;

import communication.ResponseType;
import communication.requests.collection_requests.CreateSubCollectionRequest;
import communication.requests.collection_requests.DeleteSubCollectionRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CollectionDao {

  // TODO Make return appropriate object later
  Object createSubCollection(CreateSubCollectionRequest payload);
  ResponseType deleteSubCollection(DeleteSubCollectionRequest payload);
}
