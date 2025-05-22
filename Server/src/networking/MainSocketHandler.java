package networking;

import com.google.gson.Gson;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.request_handlers.RequestHandler;
import startup.ServiceProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainSocketHandler implements Runnable {
  private final Socket clientSocket;

  public MainSocketHandler(Socket clientSocket, ServiceProvider serviceProvider) {
    this.clientSocket = clientSocket;
  }

  @Override
  public void run() {
    try {
      ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
      ObjectInputStream inc = new ObjectInputStream(clientSocket.getInputStream());
      handleRequest(inc, out);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void handleRequest(ObjectInputStream inc, ObjectOutputStream out) throws IOException, ClassNotFoundException {
    Request request = (Request) inc.readObject();
    System.out.println("Main Socket Handler Inc: " + request);

    RequestHandler handler = switch (request.type()) {
      case CARD -> ServiceProvider.getCardRequestHandler();
      case USER -> ServiceProvider.getUserRequestHandler();
      case DECK -> ServiceProvider.getDeckRequestHandler();
      case COLLECTION -> ServiceProvider.getCollectionRequestHandler();
    };

    Object res = handler.handle(request.payload());
    Response response = new Response(ResponseType.OK, res);
    out.writeObject(response);
  }
}
