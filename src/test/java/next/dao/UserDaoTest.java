package next.dao;

import next.model.User;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Ignore @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        userDao.insert(expected);

        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }

}
