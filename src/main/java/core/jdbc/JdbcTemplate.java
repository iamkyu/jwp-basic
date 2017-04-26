package core.jdbc;

import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-04-26
 */
public abstract class JdbcTemplate {

    public void insert(PreparedStatementSetter pss) throws SQLException {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareCall(getQuery())) {

            pss.setValues(pstmt);
            pstmt.executeUpdate();
        }
    }

    public List<User> query() throws SQLException {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareCall(getQuery())) {

            ResultSet rs = pstmt.executeQuery();
            List<User> users = getUsers(rs);

            return users;
        }
    }

    public List<User> query(PreparedStatementSetter pss) throws SQLException {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareCall(getQuery())) {

            pss.setValues(pstmt);

            ResultSet rs = pstmt.executeQuery();
            List<User> users = getUsers(rs);

            return users;
        }
    }

    public User queryForObject(PreparedStatementSetter pss) throws SQLException {
        return query(pss).get(0);
    }

    private List<User> getUsers(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        if (rs.next()) {
            User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                    rs.getString("email"));
            users.add(user);
        }
        return users;
    }

    public abstract String getQuery();
}
