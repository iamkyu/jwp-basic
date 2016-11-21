package core.nmvc;

import core.mvc.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Kj Nam
 * @since 2016-11-21
 */
public class HandlerExecution {
    private static final Logger logger =
            LoggerFactory.getLogger(HandlerExecution.class);

    private Object declaredObject;
    private Method method;

    public HandlerExecution(Object declaredObject, Method method) {
        this.declaredObject = declaredObject;
        this.method = method;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            return (ModelAndView) method.invoke(declaredObject, request, response);
        } catch ( IllegalAccessException | InvocationTargetException e) {
            logger.error("{} method invoke faile. error messsage: {}", method, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
