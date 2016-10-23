package next.controller.user;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Kj Nam
 * @since 2016-10-16
 */
public class UpdateQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateQuestionController.class);

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(Long.valueOf(req.getParameter("questionId")));

        Question updateQuestion = new Question(
                Long.valueOf(req.getParameter("questionId")),
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                new Date(),
                Integer.valueOf(req.getParameter("countOfAnswer")));
        log.debug("Question: {}", question);

        questionDao.update(updateQuestion);


        return jspView("redirect:/qna/show?questionId=" + req.getParameter("questionId"));
    }
}
