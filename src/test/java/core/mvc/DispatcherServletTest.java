package core.mvc;

import core.db.DataBase;
import next.controller.UserSessionUtils;
import next.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-10-13
 */
public class DispatcherServletTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    DispatcherServlet dispatcherServlet;

    @Before
    public void setUp() throws ServletException {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 루트_URL_접근시_home_jsp_로_포워드한다() throws ServletException, IOException {
        //given


        request.setRequestURI("/");
        request.setMethod("GET");

        //when
        dispatcherServlet.service(request, response);

        //then
        assertThat(response.getStatus(), is(200));
        assertThat(response.getForwardedUrl(), is("home.jsp"));
    }

    @Test
    public void 회원가입_페이지_GET_요청() throws ServletException, IOException {
        //given
        request.setRequestURI("/users/form");
        request.setMethod("GET");

        //when
        dispatcherServlet.service(request, response);

        //then
        assertThat(response.getStatus(), is(200));
        assertThat(response.getForwardedUrl(), is("/user/form.jsp"));
    }

    @Test
    public void 회원가입_POST_요청() throws ServletException, IOException {
        //given
        request.setRequestURI("/users/create");
        request.setMethod("POST");
        String userId = "helloworld";
        String userPass = "pass";
        request.setParameter("userId", userId);
        request.setParameter("password", userPass);
        request.setParameter("name", "tester");
        request.setParameter("email", "test@daum.net");

        //when
        dispatcherServlet.service(request, response);

        //then
        assertThat(response.getStatus(), is(302)); // found
        assertThat(response.getRedirectedUrl(), is("/"));
        assertThat(DataBase.findUserById(userId).getPassword(), is(userPass));
    }

    @Test
    public void 로그인_페이지_GET_요청() throws ServletException, IOException {
        //given
        request.setRequestURI("/users/loginForm");
        request.setMethod("GET");

        //when
        dispatcherServlet.service(request, response);

        //then
        assertThat(response.getStatus(), is(200));
        assertThat(response.getForwardedUrl(), is("/user/login.jsp"));
    }

    @Test
    public void 로그인_POST_요청() throws ServletException, IOException {
        //given
        request.setRequestURI("/users/login");
        request.setMethod("POST");
        User anUser = new User("hello", "world", null, null);
        DataBase.addUser(anUser);
        request.setParameter("userId", "hello");
        request.setParameter("password", "world");


        //when
        request.setSession(session);
        dispatcherServlet.service(request, response);

        //then
        User sessionUserInfo = (User) session.getAttribute(UserSessionUtils.USER_SESSION_KEY);
        assertThat(sessionUserInfo.getUserId(), is(anUser.getUserId()));
        assertThat(sessionUserInfo.getPassword(), is(anUser.getPassword()));
    }
}