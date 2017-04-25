package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kj Nam
 * @since 2017-04-24
 */
public class AddQuestionFormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return jspView("/user/login.jsp");
        }

        ModelAndView mav = jspView("/qna/form.jsp");
        mav.addObject("userId", user.getUserId());
        return mav;
    }
}
