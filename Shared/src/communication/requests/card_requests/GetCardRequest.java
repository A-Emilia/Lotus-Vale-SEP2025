package communication.requests.card_requests;

import communication.requests.card_requests.target.CollectionTarget;
import model.entities.card.components.CardSupertype;
import model.entities.card.components.CardType;

import java.io.Serializable;
import java.util.ArrayList;

public record GetCardRequest(CollectionTarget target,
                             String name,
                             String setCode,
                             String textContains,
                             ArrayList<String> subtypes,
                             ArrayList<CardType> types,
                             ArrayList<CardSupertype> supertypes,
                             ColorSort colorSort,
                             Boolean white,
                             Boolean blue,
                             Boolean black,
                             Boolean red,
                             Boolean green,
                             Boolean colorless,
                             Boolean commander) implements Serializable {
}
