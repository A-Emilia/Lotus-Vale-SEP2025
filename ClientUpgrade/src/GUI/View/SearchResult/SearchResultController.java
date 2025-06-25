package GUI.View.SearchResult;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.card.Card;

import java.util.ArrayList;

public class SearchResultController {
  private SearchResultVM vm;

  @FXML private ImageView cardDisplay;
  @FXML private MenuItem addToCollectionButton;
  @FXML private SplitMenuButton addButtonsMenu;
  @FXML private TableColumn<Card, String> setCol;
  @FXML private TableColumn<Card, String> nameCol;
  @FXML private TableView<Card> resultTable;

  public void init(SearchResultVM vm) {
    this.vm = vm;

    resultTable.setItems(vm.displayableCardsProperty());
    nameCol.setCellValueFactory(new PropertyValueFactory<Card, String>("name"));
    setCol.setCellValueFactory(new PropertyValueFactory<Card, String>("setCode"));
    resultTable.getColumns().setAll(nameCol, setCol);
    resultTable.getSelectionModel().selectedItemProperty().addListener(this::cardToDisplay);
    resultTable.itemsProperty().addListener(this::cardToDisplay);
  }

  private void cardToDisplay(Observable observable) {
    String selectedCardImg = resultTable.getSelectionModel().getSelectedItem().getImgUrl();
    Image image = new Image(selectedCardImg);
    cardDisplay.setImage(image);
  }

  public void addToCollectionButtonPressed(ActionEvent actionEvent) {
  }
}
