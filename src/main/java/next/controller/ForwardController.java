package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2017-04-18
 */
public class ForwardController implements Controller {
    private String path;

    public ForwardController(String path) {
        this.path = path;
        if (path == null) {
            throw new NullPointerException("올바른 URL을 입력하세요.");
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return path;
    }
}
