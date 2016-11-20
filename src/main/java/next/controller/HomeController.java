package next.controller;

import core.mvc.ModelAndView;
import next.controller.user.AbstractController;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2016-11-13
 */

public class HomeController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao dao = new QuestionDao();
        req.setAttribute("questions", dao.findAll());
        return jspView("home.jsp");
    }
}
