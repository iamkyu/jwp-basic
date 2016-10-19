package next.controller.qna;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kj Nam
 * @since 2016-10-15
 */
public class QnaFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }

        HttpSession session = req.getSession();
        User user = UserSessionUtils.getUserFromSession(session);
        req.setAttribute("user", user);


        return "/qna/form.jsp";
    }
}
