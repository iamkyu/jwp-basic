package next.dao;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import next.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Kj Nam
 * @since 2016-11-17
 */
public class QuestionDaoTest {
    private String writerForFixture = "expected1";
    private String titleForFixture = "My First Question";
    private String contentsForFixture = "Hello World";

    @Before
    public void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void 게시글을_작성한다() {
        //given
        deleteAllForTest();
        Question expected = createQuestionFixture();
        QuestionDao questionDao = new QuestionDao();

        //when
        questionDao.insert(expected);
        List<Question> questions = questionDao.findAll();

        //then
        assertThat(questions.size(), is(1)); //DDL script 삽입 8건 + 1건
        assertThat(questions.get(0).getQuestionId(), is(9L));
    }

    @Test
    public void 게시글_번호로_게시글을_조회한다() {
        //given
        deleteAllForTest();
        Question expected = createQuestionFixture();
        QuestionDao questionDao = new QuestionDao();

        //when
        questionDao.insert(expected);
        Question anQuestion = questionDao.findByQuestionId(9L);

        //then
        assertThat(anQuestion.getQuestionId(), is(9L));
        assertThat(anQuestion.getWriter(), is(writerForFixture));
        assertThat(anQuestion.getTitle(), is(titleForFixture));
        assertThat(anQuestion.getContents(), is(contentsForFixture));
    }

    @Test
    public void 게시글을_수정한다() {
        //given
        QuestionDao questionDao = new QuestionDao();
        Question anQuestion = questionDao.findByQuestionId(1L);
        assertNotNull(anQuestion);

        //when
        String newTitle = "newtitle";
        questionDao.update(new Question(anQuestion.getQuestionId(), anQuestion.getWriter(),
                newTitle, anQuestion.getContents(), anQuestion.getCreatedDate(),
                anQuestion.getCountOfAnswer()));
        Question updatedQuestion = questionDao.findByQuestionId(1L);

        //then
        assertThat(updatedQuestion.getTitle(), is(newTitle));
    }

    @Test
    public void 게시글_번호로_게시글을_삭제한다() {
        //given
        deleteAllForTest();
        Question expected = createQuestionFixture();
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);
        Question anQuestion = questionDao.findByQuestionId(9L);

        //when
        assertNotNull(anQuestion);
        questionDao.delete(anQuestion.getQuestionId());
        Question willNull = questionDao.findByQuestionId(9L);

        //then
        assertNull(willNull);
    }

    private Question createQuestionFixture() {
        return new Question(writerForFixture, titleForFixture, contentsForFixture);
    }

    private void deleteAllForTest() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "DELETE FROM QUESTIONS";
        jdbcTemplate.update(sql);
    }
}