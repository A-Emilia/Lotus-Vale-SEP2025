package communication.requests.deck_requests.create_deck;

public sealed interface CreateDeckRequest permits CreateStandardDeckRequest, CreateCommanderDeckRequest {
  int getUserId();
  String getDeckName();
}
