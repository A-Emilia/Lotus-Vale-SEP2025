package state;

import model.entities.user.User;

public class AppState {
  private static User loggedInUser;
  public static User getLoggedInUser() {return loggedInUser;}
  public static void setLoggedInUser(User loggedInUser) {AppState.loggedInUser = loggedInUser;}
}
