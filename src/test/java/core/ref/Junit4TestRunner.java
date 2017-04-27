package core.ref;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(MyTest.class);
            if (annotation != null) {
                method.invoke(clazz.newInstance());
            }
        }

    }
}
