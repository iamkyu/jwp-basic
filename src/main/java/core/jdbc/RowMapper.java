package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-11-16
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
