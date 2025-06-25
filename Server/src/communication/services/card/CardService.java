package communication.services.card;

import communication.Response;
import communication.requests.card_requests.AddCardRequest;
import communication.requests.card_requests.EditCardRequest;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.RemoveCardRequest;

public interface CardService
{
    Response getCard(GetCardRequest payload);
    Response addCard(AddCardRequest payload);
    Response removeCard(RemoveCardRequest payload);
    Response editCard(EditCardRequest payload);
}
