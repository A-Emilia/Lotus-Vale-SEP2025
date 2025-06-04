package networking;

import communication.Request;
import communication.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketClient {
  private static final String HOST = "localhost";
  private static final int PORT = 4269;

  public static Object sendRequest(Request request) {
    try (Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress(HOST, PORT), 1000);
      socket.setSoTimeout(1000);

      try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          ObjectInputStream inc = new ObjectInputStream(socket.getInputStream())) {

        System.out.println("Socket Service Out: " + request);
        out.writeObject(request);

        Response response = (Response) inc.readObject();
        System.out.println("Socket Service Inc: " + response);

        return switch (response.type()) {
          case OK -> response.payload();
          case ERROR -> null;
        };
      }
    } catch (SocketTimeoutException e) {
      System.err.println("Operation timed out.");
      return null;
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Operation failed: " + e.getMessage());
      return null;
    }
  }
}
