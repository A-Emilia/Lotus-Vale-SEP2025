package GUI.Collections_Page;

import GUI.Deck_Page.DeckPageVM;
import GUI.Shared.ViewController;
import GUI.Shared.ViewType;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class CollectionsPageController implements ViewController {
  private final CollectionsPageVM vm;

  public CollectionsPageController(CollectionsPageVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public Button loginButton;
  public Button homeButton;
  public Button searchMenuButton;
  public Button myCardsMenuButton;
  public Button collectionsMenuButton;
  public Button decksMenuButton;

  public void initialize() {

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
}
