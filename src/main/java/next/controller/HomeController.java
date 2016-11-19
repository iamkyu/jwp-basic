package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2016-11-13
 */

public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao dao = new QuestionDao();
        req.setAttribute("questions", dao.findAll());
        return "home.jsp";
    }
}
