package entities.DAO;

import model.entities.card.Card;

import java.util.ArrayList;
import java.util.List;

public interface CardDao {
  void add(Card card);
  Card getCardById(int id);
  ArrayList<Card> getAll();
  void update(Card card);
  void delete(int id);
}
