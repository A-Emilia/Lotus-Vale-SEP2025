package communication.services.collection;

import communication.requests.collection_requests.GetCollectionRequest;
import model.entities.card.Card;

import java.util.ArrayList;

public interface CollectionService {
  ArrayList<Card> getCollection(GetCollectionRequest payload);
}
