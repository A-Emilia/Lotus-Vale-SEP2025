package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

  public enum ViewType {
    MAIN,
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
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void showMainView() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource("Main_Page/Main_Page.fxml"));

    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Main");
    stage.setScene(scene);
  }
}
