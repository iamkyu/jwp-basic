package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2017-04-17
 */
public interface Controller {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
