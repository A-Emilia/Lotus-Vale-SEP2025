package GUI.Prototyping;

import GUI.Shared.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class PrototypeController implements ViewController {
  private final PrototypeVM vm;

  public PrototypeController(PrototypeVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public TextField searchField;

  public void initialize() {
    searchField.textProperty().bindBidirectional(vm.searchProperty());

  }

  public void prototypeButtonPressed(ActionEvent actionEvent) {}

  public void searchButtonPressed(ActionEvent actionEvent) {
    // TODO This should send a request to the server that queries the database and sends back a card.
    vm.search();
  }
}
