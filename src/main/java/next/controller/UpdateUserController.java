package next.controller;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2016-11-13
 */
@WebServlet("/user/update")
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);

        if (UserSessionUtils.isSameUser(req.getSession(), user)) {
            // is same user
        } else {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user/updateForm.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(userId, password, name, email);
        DataBase.addUser(user);

        resp.sendRedirect("/user/list");
    }
}
