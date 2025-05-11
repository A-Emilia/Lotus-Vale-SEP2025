package GUI.Search_Page;

import GUI.Shared.ViewController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchPageController implements ViewController {
  private final SearchPageVM vm;

  public SearchPageController(SearchPageVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public TextField searchField;
  public Button searchButton;

  public void initialize() {
    searchField.textProperty().bindBidirectional(vm.searchProperty());
  }



}
