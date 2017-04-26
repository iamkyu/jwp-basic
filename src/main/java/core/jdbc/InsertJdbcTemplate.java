package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2017-04-26
 */
public abstract class InsertJdbcTemplate {
    public abstract void setPreparedStatement(PreparedStatement pstmt) throws SQLException;

    public abstract String getQuery();

    public void insert() throws SQLException {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareCall(getQuery())) {

            setPreparedStatement(pstmt);
            pstmt.executeUpdate();
        }
    }
}
