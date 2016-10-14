package next.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-10-14
 */
@FunctionalInterface
public interface PreparedStatementSetter {
    void setValues(PreparedStatement pstmt) throws SQLException;
}
