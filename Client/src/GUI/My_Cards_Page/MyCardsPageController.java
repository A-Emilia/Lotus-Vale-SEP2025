package GUI.My_Cards_Page;

import GUI.Shared.ViewController;
import GUI.Shared.ViewType;
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

public class MyCardsPageController implements ViewController {
  private final MyCardsPageVM vm;

  public MyCardsPageController(MyCardsPageVM vm) {this.vm = vm;}

  /*---------------------------------------*/
  public Button homeButton;
  public Button loginButton;
  public Button searchMenuButton;
  public TableView<Card> collectionTable;
  public TableColumn<Card, String> nameCol;
  public TableColumn<Card, String> setCol;
  public ImageView cardDisplay;
  public Button myCardsMenuButton;
  public Button collectionsMenuButton;
  public Button decksMenuButton;

  public void initialize() {
    collectionTable.setItems(vm.cardProperty());
    nameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("name"));
    setCol.setCellValueFactory(new PropertyValueFactory<Card, String>("setCode"));
    collectionTable.getColumns().setAll(nameCol, setCol);

    collectionTable.getSelectionModel().selectedItemProperty().addListener(this::cardToDisplay);
    collectionTable.itemsProperty().addListener(this::cardToDisplay);

    /*
     Initializing things I am not talented enough at making persist across multiple views.
     */
    myCardsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    collectionsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    loginButton.textProperty().bind(vm.usernameProperty());
    loginButton.disableProperty().bind(vm.loggedInProperty());
  }

  public void cardToDisplay(Observable observable) {
    String selectedCardImg = collectionTable.getSelectionModel().getSelectedItem().getImgUrl();
    Image image = new Image(selectedCardImg);
    cardDisplay.setImage(image);
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

