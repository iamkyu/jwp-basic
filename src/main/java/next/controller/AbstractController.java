package next.controller;

import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.JspView;
import core.mvc.ModelAndView;

/**
 * @author Kj Nam
 * @since 2016-11-20
 */
public abstract  class AbstractController implements Controller {
    protected ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());

    }
}
