package core.nmvc;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kj Nam
 * @since 2017-05-06
 */
public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
