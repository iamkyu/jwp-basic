package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2016-11-19
 */
public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        QuestionDao dao = new QuestionDao();
        Question quesiton = dao.findByQuestionId(Long.valueOf(questionId));
        req.setAttribute("question", quesiton);

        return "/qna/show.jsp";
    }
}
