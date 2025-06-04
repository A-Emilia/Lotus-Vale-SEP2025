package communication.requests.card_requests;

public enum ColorSort {
  EXACT("Exactly these colors."),
  AT_MOST("At most these colors."),
  AT_LEAST("Including these colors.");

  private final String desc;

  ColorSort(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
