package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }

            @Override
            public String getQuery() {
                return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            }
        };

        template.insert();
    }

    public void update(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }

            @Override
            public String getQuery() {
                return  "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
            }
        };

        template.insert();
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
            }

            @Override
            public String getQuery() {
                return "SELECT userId, password, name, email FROM USERS";
            }
        };

        return template.query();
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            public String getQuery() {
                return "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            }
        };

        return template.queryForObject();
    }
}
