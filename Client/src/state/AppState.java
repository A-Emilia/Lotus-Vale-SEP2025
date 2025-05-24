package state;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.entities.user.User;

public class AppState {
  private static final ObjectProperty<User> loggedInUser = new SimpleObjectProperty<>();

  public static ObjectProperty<User> loggedInUserProperty() {return loggedInUser;}
  public static User getLoggedInUser() {return loggedInUser.get();}
  public static int getLoggedInUserId() {return loggedInUser.get().getId();}
  public static String getLoggedInUsername() {return loggedInUser.get().getUsername();}

  public static void setLoggedInUser(User user) {loggedInUser.set(user);}
}
