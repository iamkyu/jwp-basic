package core.mvc;

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
 * @since 2016-10-15
 */
public class QnaControllerTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    DispatcherServlet dispatcherServlet;

    @Before
    public void setUp() throws ServletException, IOException {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        request.setSession(session);
        dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();

        //H2 실행상태여야함
        request.setRequestURI("/users/login");
        request.setMethod("POST");
        request.setParameter("userId", "admin");
        request.setParameter("password", "password");

        dispatcherServlet.service(request, response);
    }

    @Test
    public void 질문_등록_페이지_GET_요청() throws ServletException, IOException {
        //given
        request.setRequestURI("/qna/form");
        request.setMethod("GET");

        //when
        response = new MockHttpServletResponse();
        dispatcherServlet.service(request, response);

        //then
        assertThat(response.getStatus(), is(200));
        assertThat(response.getForwardedUrl(), is("/qna/form.jsp"));
        assertNotNull(request.getAttribute("user"));
    }

    @Test
    public void 질문을_등록한다() throws ServletException, IOException {
        //given
        request.setRequestURI("/qna/create");
        request.setMethod("POST");
        request.setParameter("writer", "자바지기");
        request.setParameter("title", "HelloWorld");
        request.setParameter("contents", "My First Question");

        //when
        response = new MockHttpServletResponse();
        dispatcherServlet.service(request, response);

        //then
        assertThat(response.getStatus(), is(302)); // found
    }

    @Test
    public void 질문을_수정한다() {
        //given

        //when

        //then
    }

    @Test
    public void 질문을_삭제한다() {
        //given

        //when

        //then
    }

    @Test
    public void 질문을_전체_조회한다() {
        //given

        //when

        //then
    }

    @Test
    public void 질문_하나를_상세_조회한다() {
        //given

        //when

        //then
    }
}
