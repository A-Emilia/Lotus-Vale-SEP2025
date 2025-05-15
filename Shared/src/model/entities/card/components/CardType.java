package model.entities.card.components;

import java.util.ArrayList;

public enum CardType {
  LAND,
  CREATURE,
  ARTIFACT,
  ENCHANTMENT,
  PLANESWALKER,
  BATTLE,
  // Non-Permanent Types
  INSTANT,
  SORCERY,
  KINDRED;

  public String toString() {
    return switch(this) {
      case LAND -> "Land";
      case CREATURE -> "Creature";
      case ARTIFACT -> "Artifact";
      case ENCHANTMENT -> "Enchantment";
      case PLANESWALKER -> "Planeswalker";
      case BATTLE -> "Battle";
      case INSTANT -> "Instant";
      case SORCERY -> "Sorcery";
      case KINDRED -> "Kindred";
    };
  }

  public static CardType toCardType(String cardTypeString) {
    return switch(cardTypeString) {
      case "Land" -> LAND;
      case "Creature" -> CREATURE;
      case "Artifact" -> ARTIFACT;
      case "Enchantment" -> ENCHANTMENT;
      case "Planeswalker" -> PLANESWALKER;
      case "Battle" -> BATTLE;
      case "Instant" -> INSTANT;
      case "Sorcery" -> SORCERY;
      case "Kindred" -> KINDRED;
      default -> throw new IllegalStateException("Unexpected value: " + cardTypeString);
    };
  }

  public static ArrayList<CardType> toTypeList (ArrayList<String> list) {
    ArrayList<CardType> res = new ArrayList<>();

    for (String element : list) {
      res.add(toCardType(element));
    }
    return res;
  }
}