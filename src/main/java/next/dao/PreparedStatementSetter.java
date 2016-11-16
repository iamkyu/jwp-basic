package next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-11-16
 */
public interface PreparedStatementSetter {
    void setParameters(PreparedStatement pstmt) throws SQLException;
}
