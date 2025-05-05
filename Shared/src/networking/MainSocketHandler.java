package networking;

import startup.ServiceProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainSocketHandler implements Runnable {

  private final Socket clientSocket;
  private final ServiceProvider serviceProvider;

  public MainSocketHandler(Socket clientSocket, ServiceProvider serviceProvider) {
    this.clientSocket = clientSocket;
    this.serviceProvider = serviceProvider;
  }

  @Override public void run() {
    try {
      ObjectInputStream inc = new ObjectInputStream(clientSocket.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
