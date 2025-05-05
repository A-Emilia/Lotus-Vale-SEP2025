package startup;

import java.io.IOException;

public class StartServer {
  public static void main(String[] args) throws IOException {
    ServiceProvider serviceLocator = new ServiceProvider();
    Server server = new Server(serviceLocator);
    server.start();
  }
}
