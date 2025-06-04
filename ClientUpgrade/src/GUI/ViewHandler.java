package GUI;

import GUI.Shared.ViewType;
import GUI.View.Collection.CollectionController;
import GUI.View.Collection.CollectionVM;
import GUI.View.Deck.DeckController;
import GUI.View.Deck.DeckVM;
import GUI.View.Login.LoginController;
import GUI.View.Login.LoginVM;
import GUI.View.Main.MainController;
import GUI.View.Main.MainVM;
import GUI.View.Profile.ProfileController;
import GUI.View.Profile.ProfileVM;
import GUI.View.Search.SearchController;
import GUI.View.Search.SearchVM;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import networking.user.TCPUserClient;
import state.AppState;

import java.io.IOException;
import java.util.Objects;

public class ViewHandler {
  private static BorderPane rootLayout;
  private static Stage primaryStage;
  private static ToolBar toolBar;

  private static HBox authSection = new HBox(10);

  private static Button homeButton;
  private static Button profileButton;
  private static Button logoutButton;
  private static Button loginButton;
  private static Button searchButton;

  private static Button collectionButton;
  private static Button deckButton;



  public static void start(Stage stage) throws IOException {
    primaryStage = stage;

    rootLayout = new BorderPane();
    toolBar = createToolBar();

    rootLayout.setTop(toolBar);
    rootLayout.setBackground(new Background(createBackground()));

    switchView(ViewType.MAIN);

    Scene scene = new Scene(rootLayout, 1280, 720);
    scene.getStylesheets().add(Objects.requireNonNull(ViewHandler.class.getResource("/GUI/Shared/style.css")).toExternalForm());

    primaryStage.setScene(scene);
    primaryStage.setTitle("Lotus Vale");
    primaryStage.getIcons().add(new Image("/GUI/Shared/Planeswalker_Icon.png"));
    primaryStage.show();

  }


  public static void switchView(ViewType view) {
   try {
     FXMLLoader loader = new FXMLLoader(ViewHandler.class.getResource(view.getFxmlPath()));
     Parent parentView = loader.load();

     initializeController(view, loader);

     rootLayout.setCenter(parentView);
   }
   catch (IOException | RuntimeException e) {
     e.printStackTrace();
     Label errorLabel = new Label("Failed to load view: " + view + "\n" + e.getMessage());
     rootLayout.setCenter(errorLabel);
   }
  }

  private static void initializeController(ViewType type, FXMLLoader loader) {
    switch (type) {
      case LOGIN -> {
        LoginController controller = loader.getController();
        controller.init(new LoginVM(new TCPUserClient()));
      }
      case MAIN -> {
        MainController controller = loader.getController();
        controller.init(new MainVM());
      }
      case COLLECTION -> {
        CollectionController controller = loader.getController();
        controller.init(new CollectionVM());
      }
      case DECK -> {
        DeckController controller = loader.getController();
        controller.init(new DeckVM());
      }
      case PROFILE -> {
        ProfileController controller = loader.getController();
        controller.init(new ProfileVM());
      }
      case SEARCH -> {
        SearchController controller = loader.getController();
        controller.init(new SearchVM());
      }
    }
  }



  public static BackgroundImage createBackground() {
    return new BackgroundImage(
        new Image(
            "/GUI/Shared/Black_Lotus.jpg",
            1920, 1080,
            true,
            true),
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(
            BackgroundSize.AUTO,
            BackgroundSize.AUTO,
            false,
            false,
            false,
            true
        )
    );
  }

  private static ToolBar createToolBar() {
    homeButton = createNavButton("Home", () -> switchView(ViewType.MAIN), false);
    searchButton = createNavButton("Search", () -> switchView(ViewType.SEARCH), false);
    deckButton = createNavButton("Decks", () -> switchView(ViewType.DECK), false);
    collectionButton = createNavButton("Collection", () -> switchView(ViewType.COLLECTION), true);

    profileButton = createProfileButton();
    logoutButton = createLogoutButton();
    loginButton = createNavButton("Login", () -> switchView(ViewType.LOGIN), false);

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    ToolBar res = new ToolBar(
        homeButton,
        searchButton,
        collectionButton,
        deckButton,
        spacer,
        authSection
    );

    res.getStyleClass().add("toolbar");

    AppState.getInstance().userProperty().addListener((obs, oldUser, newUser) -> updateAuthSection());
    updateAuthSection();

    return res;
  }

  private static void updateAuthSection() {
    authSection.getChildren().clear();

    if (AppState.getInstance().isLoggedIn()) {
      profileButton.setText(AppState.getInstance().getUser().getUsername());
      authSection.getChildren().addAll(logoutButton, profileButton);
    } else {
      authSection.getChildren().add(loginButton);
    }
  }

  private static Button createNavButton(String label, Runnable action, boolean requiresLogin) {
    Button button = new Button(label);
    button.setOnAction(e -> action.run());

    if (requiresLogin) {
      button.disableProperty().bind(AppState.getInstance().userProperty().isNull());
    }

    return button;
  }

  private static Button createProfileButton() {
    Button button = new Button();
    button.setOnAction(e -> switchView(ViewType.PROFILE));
    return button;
  }

  private static Button createLogoutButton() {
    Button button = new Button("Logout");
    button.setOnAction(e-> {
      AppState.getInstance().logout();
      switchView(ViewType.MAIN);
    });

    return button;
  }
}