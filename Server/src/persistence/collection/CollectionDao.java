package persistence.collection;

import communication.requests.collection_requests.GetCollectionRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CollectionDao {
  ArrayList<Card> getCollection(GetCollectionRequest payload);
}
