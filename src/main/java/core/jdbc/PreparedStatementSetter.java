package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2017-04-27
 */
public interface PreparedStatementSetter {
    void setValues(PreparedStatement pstmt) throws SQLException;
}
