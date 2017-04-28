import core.ref.Student;
import next.model.Question;
import next.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import next.model.Question;
import next.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            logger.debug(method.getName());
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            logger.debug(field.getName());
        }
    }
    
    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
    }
    
    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Student student = new Student();
        Field name  = student.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(student, "myname");

        Field age  = student.getClass().getDeclaredField("age");
        age.setAccessible(true);
        age.set(student, 8);

        Assert.assertThat(student.getName(), is("myname"));
        Assert.assertThat(student.getAge(), is(8));
    }
}
