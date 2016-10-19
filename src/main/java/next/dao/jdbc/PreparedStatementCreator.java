package next.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-10-16
 */
public interface PreparedStatementCreator {
        PreparedStatement createPreparedStatement(Connection con) throws SQLException;
}
