package GUI.Search_Result_Page;

import GUI.Shared.ViewController;
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
  public Button collectionMenuButton;
  public SplitMenuButton addButtonsMenu;
  public MenuItem addToCollectionButton;

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
  }

  public void cardToDisplay(Observable observable) {
    String selectedCardImg = resultTable.getSelectionModel().getSelectedItem().getImgUrl();
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

  public void addToCollectionButtonPressed(ActionEvent actionEvent) {
    ArrayList<Integer> selectedCardIds = new ArrayList<>();
    selectedCardIds.add(resultTable.getSelectionModel().getSelectedItem().getId());
    vm.addToCollection(selectedCardIds);
  }
}
