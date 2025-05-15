package model.entities;

public class User {
  private final int id;
  private final String username;
  // Gets removed later
  private final String password;

  private User(Builder builder) {
    this.id = builder.id;
    this.username = builder.username;
    this.password = builder.password;
  }

  public int getId() {return id;}
  public String getUsername() {return username;}
  public String getPassword() {return password;}

  @Override public String toString() {
    return "User{" + "id=" + id + ", username='" + username + '\''
        + ", password='" + password + '\'' + '}';
  }

  // Delegate everything here to the view model.
  // All types of collections should store the ID of the user if not the user itself.
  // Main Collection. Delegate to view model
  // Sub Collections
  // Decks. Probably should be part of the view model.
  // Minimize the amount of things that the User contains.

  public static class Builder {
    private final int id;
    private String username;
    private String password;

    public Builder(int id) {
      this.id = id;
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public User build() {return new User(this);}
  }
}
