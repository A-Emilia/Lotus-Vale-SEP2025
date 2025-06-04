package GUI.Shared;

public enum ViewType {
  LOGIN("Login/Login.fxml"),
  MAIN("Main/Main.fxml"),
  COLLECTION("Collection/Collection.fxml"),
  DECK("Deck/Deck.fxml"),
  PROFILE("Profile/Profile.fxml"),
  SEARCH("Search/Search.fxml");


  private final String fxmlPath;

  ViewType(String fxmlPath) {
    this.fxmlPath = fxmlPath;
  }

  public String getFxmlPath() {
    return "/GUI/View/" + fxmlPath;
  }
}
