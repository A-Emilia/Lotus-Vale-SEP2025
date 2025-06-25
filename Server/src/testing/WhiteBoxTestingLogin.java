package testing;

import communication.Response;
import communication.ResponseType;
import communication.requests.user_requests.LoginRequest;
import communication.services.user.UserServiceImpl;
import model.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.user.UserDao;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WhiteBoxTestingLogin {
  private UserServiceImpl userService;
  private UserDao mockDao;

  @BeforeEach
  void setup() {
    mockDao = mock(UserDao.class);
    userService = new UserServiceImpl(mockDao);
  }

  /*
   * SUCCESSFUL LOGIN
   */
  @Test
  void login_validUser_callsDaoAndReturnsResponse() {
    LoginRequest request = new LoginRequest("uwu", "uwu");
    User user = new User.Builder(1)
        .username("uwu")
        .password("uwu")
        .build();
    Response response = new Response(ResponseType.OK, user);

    when(mockDao.login(request)).thenReturn(response);

    Response result = userService.login(request);

    assertEquals(ResponseType.OK, result.type());
    assertEquals(user, result.payload());

    verify(mockDao, times(1)).login(request);
  }

  /*
   * NON-EXISTENT USER
   */
  @Test
  void login_invalidUser_throwsAndCallsDaoOnce() {
    LoginRequest request = new LoginRequest("meow", "meow");

    when(mockDao.login(request)).thenThrow(new NoSuchElementException("User not found"));

    assertThrows(NoSuchElementException.class, () -> userService.login(request));

    verify(mockDao, times(1)).login(request);
  }

  /*
   * CHECKS THE IDENTITY OF THE RETURNED OBJECT
   */
  @Test
  void login_daoReturnsExactResponse_instanceMatches() {
    LoginRequest request = new LoginRequest("uwu", "uwu");

    User user = new User.Builder(1)
        .username("uwu")
        .password("uwu")
        .build();

    Response expectedResponse = new Response(ResponseType.OK, user);
    when(mockDao.login(request)).thenReturn(expectedResponse);
    Response result = userService.login(request);

    assertSame(expectedResponse, result); // Exact match. Same field values != Same instance of that object
    verify(mockDao, times(1)).login(request);
  }
}
