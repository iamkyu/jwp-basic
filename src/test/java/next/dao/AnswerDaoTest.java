package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Answer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-11-20
 */
public class AnswerDaoTest {
    @Before
    public void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void 답변을_작성한다() {
        //given
        Answer expected = new Answer("tester", "my answer", 1L);
        AnswerDao dao = new AnswerDao();

        //when
        dao.insert(expected);
        List<Answer> answers = dao.findAllByQuestionId(1L);
        assertEquals(answers.size(), 1);
        Answer result = answers.get(0);

        //then
        assertThat(result.getWriter(), is("tester"));
        assertThat(result.getContents(), is("my answer"));

        //and then
        Answer anotherOne = dao.findByAnswerId(result.getAnswerId());
        assertEquals(result.getAnswerId(), anotherOne.getAnswerId());
        assertEquals(result.getWriter(), anotherOne.getWriter());
        assertEquals(result.getContents(), anotherOne.getContents());
    }
}