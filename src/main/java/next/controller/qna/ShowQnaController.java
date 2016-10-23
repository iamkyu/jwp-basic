package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2016-10-16
 */
public class ShowQnaController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.valueOf(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        return jspView("/qna/show.jsp")
                .addObject("question", questionDao.findByQuestionId(questionId))
                .addObject("answers", answerDao.findAllByQuestionId(questionId));
    }
}
