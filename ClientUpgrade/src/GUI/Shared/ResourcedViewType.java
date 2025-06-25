package GUI.Shared;

public enum ResourcedViewType {
  SEARCH_RESULT("SearchResult/SearchResult.fxml");

  private final String fxmlPath;

  ResourcedViewType(String fxmlPath) {
    this.fxmlPath = fxmlPath;
  }

  public String getFxmlPath() {
    return "/GUI/View/" + fxmlPath;
  }
}
