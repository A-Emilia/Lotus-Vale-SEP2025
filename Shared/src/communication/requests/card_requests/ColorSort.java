package communication.requests.card_requests;

public enum ColorSort {
  EXACT,
  AT_MOST,
  AT_LEAST;

  public String toString() {
    return switch(this) {
      case EXACT -> "Exactly these colors.";
      case AT_MOST -> "At most these colors.";
      case AT_LEAST -> "Including these colors.";
    };
  }
}
