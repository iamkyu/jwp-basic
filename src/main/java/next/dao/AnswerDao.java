package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.Answer;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-11-20
 */
public class AnswerDao {
    public Answer insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
            pstmt.setLong(4, answer.getQuestionId());
            return pstmt;
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return findById(keyHolder.getId());
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

    public Answer findById(Long answerId) {
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

    public void delete(Long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
        jdbcTemplate.update(sql, answerId);
    }
}
