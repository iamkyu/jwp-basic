package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.Question;

import java.util.Date;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-11-17
 */
public class QuestionDao {


    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES(?,?,?,?,?)";

        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(),
                new Date(), 0);
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));
        PreparedStatementSetter pss = pstmt -> pstmt.setLong(1, questionId);

        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer " +
                "FROM QUESTIONS WHERE questionId=?";
        return jdbcTemplate.queryForObject(sql, rowMapper, pss);
    }

    public void update(Question question){
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        String sql = "UPDATE QUESTIONS set writer=?, title=?, contents=?, createdDate=?, countOfAnswer=? " +
                "WHERE questionId=?";
        //FIXME 답변수 매직넘버 제거
        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(),
                new Date(), 0, question.getQuestionId());
    }


    public void delete(Long questionId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
        PreparedStatementSetter pss = pstmt -> pstmt.setLong(1, questionId);

        String sql = "DELETE FROM QUESTIONS WHERE questionId=?";
        jdbcTemplate.update(sql, pss);
    }}
