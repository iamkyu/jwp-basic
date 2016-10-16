package next.dao;

import next.dao.jdbc.JdbcTemplate;
import next.dao.jdbc.RowMapper;
import next.model.Question;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-10-16
 */
public class QuestionDao {
    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO QUESTIONS(writer, title, contents, createdDate, countOfAnswer) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(),
                question.getCreatedDate(), question.getCountOfAnswer());

    }

    public void update(Question question) {

    }

    public List<Question> findAll() {
        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";
        return jdbcTemplate.query(sql, rm);
    }

    public Question findByQuestionId(Long questionId) {
        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?";
        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }
}
