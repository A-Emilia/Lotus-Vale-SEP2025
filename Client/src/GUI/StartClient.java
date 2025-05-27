package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartClient extends Application
{
  public static void main(String[] args)
  {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    ViewHandler.start(primaryStage);
  }
}
