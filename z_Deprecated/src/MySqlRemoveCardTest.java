import networking.DatabaseConnector;
import utilities.querying.card.MySQLRemoveCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlRemoveCardTest {
  public static void main(String[] args) throws SQLException
  {
    // Wall of fire ID = 692
    ArrayList<Integer> wallOfFuckYou = new ArrayList<>();
    wallOfFuckYou.add(692);

    //RemoveCardRequest fuckWallOfFire = new RemoveCardRequest(1, wallOfFuckYou, "meow");
    //MySQLRemoveCard kill = MySQLRemoveCard.remove(fuckWallOfFire);

    //try (Connection con = DatabaseConnector.getConnection();
    //PreparedStatement execute = kill.build(con)) {
    //  execute.executeUpdate();
    //}

  }
}
