package GUI.Search_Page;

import GUI.Shared.ViewController;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class SearchPageController implements ViewController {
  private final SearchPageVM vm;

  public SearchPageController(SearchPageVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public Button searchButton;
  public Button registerButton;
  public Button homeButton;
  public Button loginButton;
  public Button prototypeButton;
  public Button searchMenuButton;
  public TextField nameSearchField;
  public TextField textSearchField;

  public void initialize() {
    nameSearchField.textProperty().bindBidirectional(vm.nameSearchProperty());
    textSearchField.textProperty().bindBidirectional(vm.textSearchProperty());
  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.LOGIN);
  }

  public void prototypeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.PROTOTYPE);
  }

  public void registerButtonPressed(ActionEvent actionEvent) {
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.SEARCH);
  }

  public void searchButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showViewWithResource(ViewHandler.ViewTypeWithResource.SEARCH_RESULT, vm.search());
  }
}
