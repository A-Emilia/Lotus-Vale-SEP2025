import networking.MainSocketHandler;
import startup.ServiceProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerObj
{

  private final ServiceProvider serviceProvider;

  public ServerObj(ServiceProvider serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(6969);

    while (true) {
      Socket socket = serverSocket.accept();
      MainSocketHandler socketHandler = new MainSocketHandler(socket, serviceProvider);
      Thread socketThread = new Thread(socketHandler);
      socketThread.start();
    }
  }
}
