package GUI;

import GUI.Login_Page.LoginPageController;
import GUI.Login_Page.LoginPageVM;
import GUI.Main_Page.MainPageController;
import GUI.Main_Page.MainPageVM;
import GUI.Prototyping.PrototypeController;
import GUI.Prototyping.PrototypeVM;
import GUI.Search_Page.SearchPageController;
import GUI.Search_Page.SearchPageVM;
import GUI.Search_Result_Page.SearchResultPageController;
import GUI.Search_Result_Page.SearchResultPageVM;
import GUI.Shared.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.card.Card;
import networking.card.TCPCardClient;
import networking.lotus.TCPLotusClient;

import java.io.IOException;
import java.util.ArrayList;

public class ViewHandler {

  public enum ViewType {
    MAIN,
    LOGIN,
    PROTOTYPE,
    SEARCH,
  }

  public enum ViewTypeWithResource {
    SEARCH_RESULT,
  }

  private static Stage stage;

  public static void start(Stage s) {
    stage = s;
    stage.setWidth(1280);
    stage.setHeight(720);
    showView(ViewType.MAIN);
    stage.show();
  }

  public static void showView(ViewType view) {
    try {
      switch (view) {
        case MAIN -> showMainView();
        case LOGIN -> showLoginView();
        case PROTOTYPE -> showPrototypeView();
        case SEARCH -> showSearchPageView();}
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
    initializeView("Login_Page/Login_Page.fxml", new LoginPageController(new LoginPageVM()));
  }

  private static void showPrototypeView() throws IOException {
    initializeView("Prototyping/Prototype.fxml", new PrototypeController(new PrototypeVM(new TCPCardClient())));
  }

  private static void showSearchPageView() throws IOException {
    initializeView("Search_Page/Search_Page.fxml", new SearchPageController(new SearchPageVM(new TCPCardClient())));
  }

  private static void showSearchResultView(ArrayList<Card> res) throws IOException {
    initializeView("Search_Result_Page/Search_Result_Page.fxml", new SearchResultPageController(new SearchResultPageVM(), res));
  }
}