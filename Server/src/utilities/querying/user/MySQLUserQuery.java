package utilities.querying.user;

import utilities.querying.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class MySQLUserQuery implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder("SELECT u.* FROM users u WHERE 1=1");
  private final List<Object> cardParam = new ArrayList<>();


}
