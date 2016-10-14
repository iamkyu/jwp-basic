package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-10-14
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
