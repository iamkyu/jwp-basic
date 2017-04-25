package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Question question = new Question(user.getUserId(), request.getParameter("title"),
                request.getParameter("contents"));
        log.debug("Question : {}", question);
        questionDao.insert(question);
        return jspView("redirect:/");
    }
}
