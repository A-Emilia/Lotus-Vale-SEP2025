package GUI.Prototyping;

import GUI.Shared.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PrototypeController implements ViewController {
  private final PrototypeVM vm;


  public PrototypeController(PrototypeVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public TextField searchField;
  public Button searchButton;
  public ImageView cardDisplay;


  public void initialize() {
    searchField.textProperty().bindBidirectional(vm.searchProperty());

  }

  public void prototypeButtonPressed(ActionEvent actionEvent) {}

  public void searchButtonPressed(ActionEvent actionEvent) {
    // TODO This should send a request to the server that queries the database and sends back a card.
    vm.search();
    cardDisplay.setImage(new Image("https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=600&type=card"));
  }
}
