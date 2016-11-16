package next.dao;

import next.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
       };

        String sql =  "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql);
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };

        String sql =  "UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?";
        jdbcTemplate.update(sql);
    }

    public User findByUserId(String userId) throws SQLException {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return (User) jdbcTemplate.queryForObject(sql);
    }

    public List<User> findAll() throws SQLException {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {

            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.query(sql);
    }
}
