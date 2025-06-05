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

  public static Response sendRequest(Request request) throws SocketTimeoutException {
    try (Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress(HOST, PORT), 2000);
      socket.setSoTimeout(2000);

      try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          ObjectInputStream inc = new ObjectInputStream(socket.getInputStream())) {

        System.out.println("Socket Service Out: " + request);
        out.writeObject(request);

        Response response = (Response) inc.readObject();
        System.out.println("Socket Service Inc: " + response);

        return response;
      }
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Operation failed: " + e.getMessage());
      return null;
    }
  }
}
