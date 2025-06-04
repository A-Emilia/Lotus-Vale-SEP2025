package GUI.View.Search;

import communication.requests.card_requests.ColorSort;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class SearchController {
  public TextField nameSearchField;
  public TextField textSearchField;
  public Button searchButton;

  @FXML private ComboBox<String> colorSort;
  @FXML private CheckBox whiteManaCheck;
  @FXML private CheckBox redManaCheck;
  @FXML private CheckBox blueManaCheck;
  @FXML private CheckBox blackManaCheck;
  @FXML private CheckBox colorlessManaCheck;
  @FXML private CheckBox greenManaCheck;

  public void init(SearchVM searchVM) {
    setCheckBoxGraphic(whiteManaCheck, "white_mana.png");
    setCheckBoxGraphic(blueManaCheck, "blue_mana.png");
    setCheckBoxGraphic(blackManaCheck, "black_mana.png");
    setCheckBoxGraphic(redManaCheck, "red_mana.png");
    setCheckBoxGraphic(greenManaCheck, "green_mana.png");
    setCheckBoxGraphic(colorlessManaCheck, "colorless_mana.png");

    ObservableList<String> data = FXCollections.observableArrayList(ColorSort.EXACT.getDesc(), ColorSort.AT_LEAST.getDesc(), ColorSort.AT_MOST.getDesc());
    colorSort.setItems(data);
    colorSort.getSelectionModel().selectFirst();
  }

  private void setCheckBoxGraphic(CheckBox checkBox, String imagePath) {
    ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/Shared/mana_symbols/" + imagePath))));
    icon.setFitWidth(16);
    icon.setFitHeight(16);
    checkBox.setGraphic(icon);
    checkBox.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
  }

  public void searchButtonPressed(ActionEvent actionEvent)
  {
  }
}
