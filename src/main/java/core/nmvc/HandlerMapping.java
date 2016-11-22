package core.nmvc;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kj Nam
 * @since 2016-11-22
 */
public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
