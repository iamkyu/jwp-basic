package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kj Nam
 * @since 2016-10-12
 */
public interface Controller {
    String execute(HttpServletRequest req, HttpServletResponse resp)throws Exception;
}
