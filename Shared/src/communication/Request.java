package communication;

public class Request {
  // Needed?
  private String requestId;
  private RequestType type;
  private Object payload;

  public Request(String requestId, RequestType type, Object payload) {
    this.requestId = requestId;
    this.type = type;
    this.payload = payload;
  }

  public String getRequestId() {return requestId;}
  public RequestType getType() {return type;}
  public Object getPayload() {return payload;}
}
