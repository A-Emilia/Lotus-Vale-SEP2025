package GUI.Search_Result_Page;

import GUI.Shared.ViewController;
import GUI.Shared.ViewType;
import GUI.ViewHandler;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.card.Card;

import java.util.ArrayList;

public class SearchResultPageController implements ViewController {
  private final SearchResultPageVM vm;

  public SearchResultPageController(SearchResultPageVM vm) {
    this.vm = vm;
    // TODO Remove all additional copies of the same card.
  }

  /*---------------------------------------*/
  public Button loginButton;
  public Button homeButton;
  public Button searchMenuButton;
  public SplitMenuButton addButtonsMenu;
  public MenuItem addToCollectionButton;
  public Button myCardsMenuButton;
  public Button collectionsMenuButton;
  public Button decksMenuButton;

  public ImageView cardDisplay;
  public TableView<Card> resultTable;
  public TableColumn<Card, String> nameCol;
  public TableColumn<Card, String> setCol;


  public void initialize() {
    resultTable.setItems(vm.displayableCardsProperty());
    nameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("name"));
    setCol.setCellValueFactory(new PropertyValueFactory<Card, String>("setCode"));
    resultTable.getColumns().setAll(nameCol, setCol);

    resultTable.getSelectionModel().selectedItemProperty().addListener(this::cardToDisplay);
    resultTable.itemsProperty().addListener(this::cardToDisplay);

    /*
     Initializing things I am not talented enough at making persist across multiple views.
     */
    myCardsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    collectionsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    loginButton.textProperty().bind(vm.usernameProperty());
    loginButton.disableProperty().bind(vm.loggedInProperty());
  }

  public void cardToDisplay(Observable observable) {
    String selectedCardImg = resultTable.getSelectionModel().getSelectedItem().getImgUrl();
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

  public void addToCollectionButtonPressed(ActionEvent actionEvent) {
    ArrayList<Integer> selectedCardIds = new ArrayList<>();
    selectedCardIds.add(resultTable.getSelectionModel().getSelectedItem().getId());
    vm.addToCollection(selectedCardIds);
  }
}
