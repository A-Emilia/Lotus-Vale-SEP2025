package GUI.Search_Page;

import GUI.Shared.ViewController;
import GUI.Shared.ViewType;
import GUI.Shared.ViewTypeWithResource;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchPageController implements ViewController {
  private final SearchPageVM vm;

  public SearchPageController(SearchPageVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public Button searchButton;
  public Button homeButton;
  public Button loginButton;
  public Button searchMenuButton;
  public TextField nameSearchField;
  public TextField textSearchField;
  public Button myCardsMenuButton;
  public Button collectionsMenuButton;
  public Button decksMenuButton;

  public void initialize() {
    nameSearchField.textProperty().bindBidirectional(vm.nameSearchProperty());
    textSearchField.textProperty().bindBidirectional(vm.textSearchProperty());

    /*
     Initializing things I am not talented enough at making persist across multiple views.
     */
    myCardsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    collectionsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    loginButton.textProperty().bind(vm.usernameProperty());
    loginButton.disableProperty().bind(vm.loggedInProperty());
  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.LOGIN);
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.SEARCH);
  }

  public void myCardsMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.MY_CARDS);
  }

  public void collectionsMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.COLLECTIONS);
  }

  public void decksMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.DECKS);
  }

  public void searchButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showViewWithResource(ViewTypeWithResource.SEARCH_RESULT, vm.search());
  }
}
