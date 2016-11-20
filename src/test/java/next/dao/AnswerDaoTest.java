package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Answer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

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
        Answer result = dao.insert(expected);
        List<Answer> answers = dao.findAllByQuestionId(1L);
        assertEquals(answers.size(), 1);

        //then
        assertEquals(result.getAnswerId(), answers.get(0).getAnswerId());
        assertEquals(result.getContents(), answers.get(0).getContents());
    }
}