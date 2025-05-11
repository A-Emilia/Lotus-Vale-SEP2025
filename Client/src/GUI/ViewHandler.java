package GUI;

import GUI.Login_Page.LoginPageController;
import GUI.Login_Page.LoginPageVM;
import GUI.Main_Page.MainPageController;
import GUI.Main_Page.MainPageVM;
import GUI.Prototyping.PrototypeController;
import GUI.Prototyping.PrototypeVM;
import GUI.Shared.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.card.TCPCardClient;
import networking.lotus.TCPLotusClient;

import java.io.IOException;

public class ViewHandler {

  public enum ViewType {
    MAIN,
    LOGIN,
    PROTOTYPE,
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
    initializeView("Prototyping/Prototype.fxml", new PrototypeController(new PrototypeVM(new TCPLotusClient())));
  }
}