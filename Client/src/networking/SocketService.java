package networking;

import communication.Request;
import communication.Response;
import communication.ResponseType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketService {

  public static Object sendRequest(Request request) {
    /*
     * This is some of the worst syntax I have ever seen.
     * TODO - Re-evaluate if I should put the initialization outside the try.
     *
     * Jan comments:
     * Because you are trying to call fallible code (e.g. creating a Socket)
     * you either need to make the function itself also fallible or
     * infinitely loop until it doesn't error.
     */
    try (Socket socket = new Socket("localhost", 4269);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inc = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("Socket Service Out: " + request);
      out.writeObject(request);
      Response response = (Response) inc.readObject();
      System.out.println("Socket Service Inc: " + response);

      switch (response.type()) {
        case OK -> {return response.payload();}
        case ERROR -> {return null;}
      }

    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Operation failed: " + e.getMessage());
      e.printStackTrace();
    }


    return null;
  }
}
