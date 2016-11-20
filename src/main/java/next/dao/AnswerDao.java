package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.Answer;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-11-20
 */
public class AnswerDao {
    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS(writer, contents, createdDate, questionId) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public List<Answer> findAllByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RowMapper<Answer> rowMapper = rs -> new Answer(
                rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getLong("questionId"));
        PreparedStatementSetter pss = pstmt -> pstmt.setLong(1, questionId);
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId = ?";
        return jdbcTemplate.query(sql, rowMapper, pss);
    }

    public Answer findByAnswerId(Long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RowMapper<Answer> rowMapper = rs -> new Answer(
                rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getLong("questionId"));
        PreparedStatementSetter pss = pstmt -> pstmt.setLong(1, answerId);
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ? " +
                "ORDER BY answerId DESC";
        return jdbcTemplate.queryForObject(sql, rowMapper, pss);
    }
}
