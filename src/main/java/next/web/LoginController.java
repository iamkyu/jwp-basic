package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2016-10-09
 */
@WebServlet("/user/login")
public class LoginController extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = String.valueOf(req.getParameter("userId"));

        User anUser = DataBase.findUserById(userId);
        if (anUser != null) {
            log.debug("user : {}", anUser);
            req.setAttribute("user", anUser);

            String userPass = String.valueOf(req.getParameter("password"));
            if (anUser.getPassword().equals(userPass)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", anUser);
                resp.sendRedirect("/user/list");
            } else {
                resp.sendRedirect("/user/login_failed.html");
            }


        } else {
            resp.sendRedirect("/user/login_failed.html");
        }
    }
}
