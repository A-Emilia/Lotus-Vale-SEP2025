package GUI.View.Search;

import communication.requests.card_requests.ColorSort;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import model.entities.card.components.CardSupertype;
import model.entities.card.components.CardType;

import java.net.SocketTimeoutException;
import java.util.Objects;

public class SearchController {
  private SearchVM vm;

  @FXML private FlowPane cardSubTypePane;
  @FXML private FlowPane cardSuperTypePane;
  @FXML private FlowPane cardTypePane;

  private ObservableList<ColorSort> manaSortOptions;
  private ObservableList<CardType> typeSortOptions;
  private ObservableList<CardSupertype> supertypeSortOptions;
  private final ObservableList<String> selectedCardSubTypes = FXCollections.observableArrayList();
  private final ObservableList<CardType> selectedCardTypes = FXCollections.observableArrayList();
  private final ObservableList<CardSupertype> selectedCardSupertypes = FXCollections.observableArrayList();

  public TextField nameSearchField;
  public TextField textSearchField;
  public Button searchButton;

  @FXML private TextField setCodeField;
  @FXML private ComboBox<CardSupertype> cardSuperType;
  @FXML private ComboBox<CardType> cardType;
  @FXML private TextField subtypeField;
  @FXML private CheckBox commanderCheckBox;
  @FXML private ComboBox<ColorSort> colorSort;
  @FXML private CheckBox whiteManaCheck;
  @FXML private CheckBox redManaCheck;
  @FXML private CheckBox blueManaCheck;
  @FXML private CheckBox blackManaCheck;
  @FXML private CheckBox colorlessManaCheck;
  @FXML private CheckBox greenManaCheck;

  /*
   * I am not sure if this is some of the worst or best code I have ever written.
   * The amount of code bloat is insane. Jesus fucking christ, man. Java.
   * Please let me go.
   */

  public void init(SearchVM searchVM) {
    this.vm = searchVM;

    setupTypeOptions();
    setupSupertypeOptions();
    setupTypeTags();
    setupManaSymbols();
    setupColorSortOptions();
    updateColorSortOptions();

    nameSearchField.textProperty().bindBidirectional(vm.nameSearchProperty());
    textSearchField.textProperty().bindBidirectional(vm.textSearchProperty());
    setCodeField.textProperty().bindBidirectional(vm.setCodeProperty());
    whiteManaCheck.selectedProperty().bindBidirectional(vm.whiteManaProperty());
    redManaCheck.selectedProperty().bindBidirectional(vm.redManaProperty());
    blueManaCheck.selectedProperty().bindBidirectional(vm.blueManaProperty());
    blackManaCheck.selectedProperty().bindBidirectional(vm.blackManaProperty());
    greenManaCheck.selectedProperty().bindBidirectional(vm.greenManaProperty());
    colorlessManaCheck.selectedProperty().bindBidirectional(vm.colorlessManaProperty());
    //TODO : MANA COST FIELD
    commanderCheckBox.selectedProperty().bindBidirectional(vm.commanderProperty());
    Bindings.bindContentBidirectional(selectedCardSubTypes, vm.subtypeProperty());
    Bindings.bindContentBidirectional(selectedCardTypes, vm.typeProperty());
    Bindings.bindContentBidirectional(selectedCardSupertypes, vm.supertypeProperty());
    colorSort.valueProperty().bindBidirectional(vm.colorSortProperty());
  }

  private void setupManaSymbols() {
    setCheckBoxGraphic(whiteManaCheck, "white_mana.png");
    setCheckBoxGraphic(blueManaCheck, "blue_mana.png");
    setCheckBoxGraphic(blackManaCheck, "black_mana.png");
    setCheckBoxGraphic(redManaCheck, "red_mana.png");
    setCheckBoxGraphic(greenManaCheck, "green_mana.png");
    setCheckBoxGraphic(colorlessManaCheck, "colorless_mana.png");
  }

  private void setupTypeTags() {
    subtypeField.setOnAction(e -> {
      String text = subtypeField.getText().trim();
      if (text.isEmpty()) return;

      if (selectedCardSubTypes.size() >= 4) {
        subtypeField.clear();
        return;
      }

      boolean alreadyExists = selectedCardSubTypes.stream().anyMatch(existing -> existing.equalsIgnoreCase(text));
      if (!alreadyExists) {
        selectedCardSubTypes.add(text);
        addTag(cardSubTypePane, text, () -> selectedCardSubTypes.remove(text));
      }
      subtypeField.clear();
    });

    cardType.setOnAction(e -> {
      CardType selected = cardType.getSelectionModel().getSelectedItem();
      if (selected != null && !selectedCardTypes.contains(selected)) {
        selectedCardTypes.add(selected);
        addTag(cardTypePane, selected.toString(), () -> selectedCardTypes.remove(selected));
      }
    });

    cardSuperType.setOnAction(e -> {
      CardSupertype selected = cardSuperType.getSelectionModel().getSelectedItem();
      if (selected != null && !selectedCardSupertypes.contains(selected)) {
          selectedCardSupertypes.add(selected);
          addTag(cardSuperTypePane, selected.toString(), () -> selectedCardSupertypes.remove(selected));
      }
    });
  }

  private void addTag(FlowPane pane, String text, Runnable onRemove) {
    Button tag = new Button("✕ " + text);
    tag.setId("tag_button");
    tag.setOnAction(e -> {
      pane.getChildren().remove(tag);
      onRemove.run();
    });
    tag.setFocusTraversable(false);
    pane.getChildren().add(tag);
  }

  private void setupColorSortOptions() {
    manaSortOptions = FXCollections.observableArrayList(ColorSort.values());
    colorSort.setItems(manaSortOptions);
  }

  private void setupSupertypeOptions() {
    supertypeSortOptions = FXCollections.observableArrayList(CardSupertype.values());
    cardSuperType.setItems(supertypeSortOptions);
  }

  private void setupTypeOptions() {
    typeSortOptions = FXCollections.observableArrayList(CardType.values());
    cardType.setItems(typeSortOptions);
  }

  private void updateColorSortOptions() {
    commanderCheckBox.selectedProperty().addListener((obs, oldVal, isChecked) -> {
      ColorSort currentlySelected = colorSort.getValue();
      ObservableList<ColorSort> newList = FXCollections.observableArrayList(manaSortOptions);

      if (isChecked) {newList.remove(ColorSort.AT_LEAST);}

      colorSort.setItems(newList);

      if (!newList.contains(currentlySelected)) {
        colorSort.getSelectionModel().selectFirst();
      } else {
        colorSort.getSelectionModel().select(currentlySelected);
      }
    });
  }

  private void setCheckBoxGraphic(CheckBox checkBox, String imagePath) {
    ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/Shared/mana_symbols/" + imagePath))));
    icon.setFitWidth(16);
    icon.setFitHeight(16);
    checkBox.setGraphic(icon);
    checkBox.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
  }

  public void searchButtonPressed(ActionEvent actionEvent) throws SocketTimeoutException {
    vm.search();
  }
}
