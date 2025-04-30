package model.entities.card.components;

import java.util.ArrayList;

public enum CardSupertype {
  BASIC,
  LEGENDARY,
  SNOW,
  WORLD;
  /*
   * The supertype "Ongoing" exists, but is not part of standard MtG.
   * It is part of the Archenemy format, which will not be supported by this
   * project in any way, and thus it has been cut.
   */

  public String toString() {
    return switch(this) {
      case BASIC -> "Basic";
      case LEGENDARY -> "Legendary";
      case SNOW -> "Snow";
      case WORLD -> "World";
    };
  }

  public static CardSupertype toCardSuperType(String superTypeString) {
    return switch (superTypeString) {
      case "Basic" -> BASIC;
      case "Legendary" -> LEGENDARY;
      case "Snow" -> SNOW;
      case "World" -> WORLD;
      default -> throw new IllegalStateException("Unexpected value: " + superTypeString);
    };
  }

  public static ArrayList<CardSupertype> toTypeList(ArrayList<String> list) {
    ArrayList<CardSupertype> res = new ArrayList<>();

    for (String element : list) {
      res.add(toCardSuperType(element));
    }
    return res;
  }
}