package core.mvc;

/**
 * @author Kj Nam
 * @since 2016-10-23
 */
public abstract class AbstractController implements Controller {
    protected ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
