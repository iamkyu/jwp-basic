package next.web;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2016-10-08
 */
@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        String url;
        if (value != null) {
            User user = (User) value;
            log.debug("user : {}", user);
            req.setAttribute("user", user);
            url = "/user/updateForm.jsp";
        } else {
            url = "/user/login";
        }

        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        log.debug("user : {}", user);
        DataBase.addUser(user);
        resp.sendRedirect("/user/list");
    }
}
