package state;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entities.user.User;

public class AppState {
  private static final AppState instance = new AppState();
  private final ObjectProperty<User> currentUser = new SimpleObjectProperty<>();

  public AppState() {}

  public static AppState getInstance() {return instance;}
  public ObjectProperty<User> userProperty() {return currentUser;}
  public User getUser() {return currentUser.get();}
  public void login(User user) {currentUser.set(user);}
  public void logout() {currentUser.set(null);}
  public boolean isLoggedIn() {return currentUser.get() != null;}
}
