package next.controller.user;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2016-10-16
 */
public class UpdateFormQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.valueOf(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(questionId);
        if (question == null) {
            throw new NullPointerException("질문을 찾을 수 없습니다.");
        }

        return jspView("/qna/updateForm.jsp").addObject("question", question);
    }
}
