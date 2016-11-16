package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-11-16
 */
@FunctionalInterface
public interface PreparedStatementSetter {
    void setParameters(PreparedStatement pstmt) throws SQLException;
}
