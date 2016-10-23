package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kj Nam
 * @since 2016-10-15
 */
public class QnaFormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/");
        }

        HttpSession session = req.getSession();

        return jspView("/qna/form.jsp").addObject("user", UserSessionUtils.getUserFromSession(session));
    }
}
