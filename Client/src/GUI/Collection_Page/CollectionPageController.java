package GUI.Collection_Page;

import GUI.Shared.ViewController;
import GUI.ViewHandler;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.card.Card;

public class CollectionPageController implements ViewController {
  private final CollectionPageVM vm;

  public CollectionPageController(CollectionPageVM vm) {this.vm = vm;}

  /*---------------------------------------*/
  public Button homeButton;
  public Button loginButton;
  public Button searchMenuButton;
  public Button collectionMenuButton;
  public TableView<Card> collectionTable;
  public TableColumn<Card, String> nameCol;
  public TableColumn<Card, String> setCol;
  public ImageView cardDisplay;

  public void initialize() {
    collectionTable.setItems(vm.cardProperty());
    nameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("name"));
    setCol.setCellValueFactory(new PropertyValueFactory<Card, String>("setCode"));
    collectionTable.getColumns().setAll(nameCol, setCol);

    collectionTable.getSelectionModel().selectedItemProperty().addListener(this::cardToDisplay);
    collectionTable.itemsProperty().addListener(this::cardToDisplay);
  }

  public void cardToDisplay(Observable observable) {
    String selectedCardImg = collectionTable.getSelectionModel().getSelectedItem().getImgUrl();
    Image image = new Image(selectedCardImg);
    cardDisplay.setImage(image);
  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.LOGIN);
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.SEARCH);
  }

  public void collectionMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.COLLECTION);
  }
}

