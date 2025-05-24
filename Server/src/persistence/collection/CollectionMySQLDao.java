package persistence.collection;

import communication.ResponseType;
import communication.requests.collection_requests.CreateSubCollectionRequest;
import communication.requests.collection_requests.DeleteSubCollectionRequest;
import model.entities.card.Card;
import networking.DatabaseConnector;
import utilities.querying.collection.MySQLGetCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectionMySQLDao implements CollectionDao {

  @Override
  public Object createSubCollection(CreateSubCollectionRequest payload) {
    return null;
  }

  @Override
  public ResponseType deleteSubCollection(DeleteSubCollectionRequest payload) {
    return null;
  }
}
