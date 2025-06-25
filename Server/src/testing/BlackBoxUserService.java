package testing;

import communication.Response;
import communication.ResponseType;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import communication.services.user.UserServiceImpl;
import model.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.testng.annotations.BeforeMethod;
import persistence.user.UserDao;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlackBoxUserService {
  private UserServiceImpl userService;
  private UserDao mockDao;

  @BeforeEach
  void setup() {
    mockDao = mock(UserDao.class);
    userService = new UserServiceImpl(mockDao);
  }

  /*
   * ZERO ALSO EXCEPTION HANDLING
   */
  @Test
  void testLogin_invalidUser_throwsException() {
    LoginRequest request = new LoginRequest("invalid", "invalid");
    when(mockDao.login(request)).thenThrow(new NoSuchElementException("User not found"));

    assertThrows(NoSuchElementException.class, () -> userService.login(request));
  }

  /*
   * ONE ALSO IMPLIES MANY USERS EXIST
   */
  @Test
  void testLogin_validUser_returnsOkResponse() {
    LoginRequest request = new LoginRequest("uwu", "correct");
    User expectedUser = new User.Builder(4)
        .username("uwu")
        .password("correct")
        .build();
    Response expectedResponse = new Response(ResponseType.OK, expectedUser);

    when(mockDao.login(request)).thenReturn(expectedResponse);

    Response actual = userService.login(request);

    assertEquals(ResponseType.OK, actual.type());
    assertEquals(expectedUser, actual.payload());
  }

/*
 * BOUNDARY - SHORTEST PASSWORD
 */
  @Test
  void login_shortestPassword_valid_returnsOk() {
    LoginRequest input = new LoginRequest("uwu", "1"); // assuming 1 is the min
    User expectedUser = new User.Builder(2).username("uwu").password("1").build();

    when(mockDao.login(input)).thenReturn(new Response(ResponseType.OK, expectedUser));

    Response result = userService.login(input);
    assertEquals(ResponseType.OK, result.type());
    assertEquals(expectedUser, result.payload());
  }
}

