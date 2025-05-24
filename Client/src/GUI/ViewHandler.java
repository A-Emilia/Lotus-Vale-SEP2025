package GUI;

import GUI.Collections_Page.CollectionsPageController;
import GUI.Collections_Page.CollectionsPageVM;
import GUI.Deck_Page.DeckPageController;
import GUI.Deck_Page.DeckPageVM;
import GUI.My_Cards_Page.MyCardsPageController;
import GUI.My_Cards_Page.MyCardsPageVM;
import GUI.Login_Page.LoginPageController;
import GUI.Login_Page.LoginPageVM;
import GUI.Main_Page.MainPageController;
import GUI.Main_Page.MainPageVM;
import GUI.Search_Page.SearchPageController;
import GUI.Search_Page.SearchPageVM;
import GUI.Search_Result_Page.SearchResultPageController;
import GUI.Search_Result_Page.SearchResultPageVM;
import GUI.Shared.ViewController;
import GUI.Shared.ViewType;
import GUI.Shared.ViewTypeWithResource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.card.Card;
import networking.card.TCPCardClient;
import networking.user.TCPUserClient;

import java.io.IOException;
import java.util.ArrayList;

public class ViewHandler {
  private static Stage stage;

  public static void start(Stage s) {
    stage = s;
    stage.setWidth(1920);
    stage.setHeight(1080);
    showView(ViewType.MAIN);
    stage.show();
  }

  public static void showView(ViewType view) {
    try {
      switch (view) {
        case MAIN -> showMainView();
        case LOGIN -> showLoginView();
        case SEARCH -> showSearchPageView();
        case MY_CARDS -> showMyCardsPageView();
        case COLLECTIONS -> showCollectionsPageView();
        case DECKS -> showDecksPageView();
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void showViewWithResource(ViewTypeWithResource view, Object resource) {
    try {
      switch (view) {
        // TODO Add check
        case SEARCH_RESULT -> showSearchResultView((ArrayList<Card>) resource);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void initializeView(String viewPath, ViewController controller) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(viewPath));
    fxmlLoader.setControllerFactory(ignore -> controller);

    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Lotus Vale");
    stage.setScene(scene);
  }

  private static void showMainView() throws IOException {
    initializeView("Main_Page/Main_Page.fxml", new MainPageController(new MainPageVM()));
  }

  private static void showLoginView() throws IOException {
    initializeView("Login_Page/Login_Page.fxml", new LoginPageController(new LoginPageVM(new TCPUserClient())));
  }

  private static void showSearchPageView() throws IOException {
    initializeView("Search_Page/Search_Page.fxml", new SearchPageController(new SearchPageVM(new TCPCardClient())));
  }

  private static void showSearchResultView(ArrayList<Card> res) throws IOException {
    initializeView("Search_Result_Page/Search_Result_Page.fxml", new SearchResultPageController(new SearchResultPageVM(res, new TCPCardClient())));
  }

  private static void showMyCardsPageView() throws IOException {
    initializeView("My_Cards_Page/My_Cards_Page.fxml", new MyCardsPageController(new MyCardsPageVM(new TCPCardClient())));
  }

  private static void showDecksPageView() throws IOException {
    initializeView("Deck_Page/Deck_Page.fxml", new DeckPageController(new DeckPageVM()));
  }

  private static void showCollectionsPageView() throws IOException {
    initializeView("Collections_Page/Collections_Page.fxml", new CollectionsPageController(new CollectionsPageVM()));
  }
}