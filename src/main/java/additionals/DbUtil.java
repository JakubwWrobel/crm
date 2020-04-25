package additionals;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DbUtil {
    private DbUtil() {
    }

    private static final String ERROR = "ERROR";

    private static DataSource dataSource;
    private static ResultSet result;
    public static Connection getConnection() throws SQLException {
        Optional<DataSource> dataSource = getInstance();
        if (dataSource.isPresent()) {
            return dataSource.get().getConnection();
        } else {
            throw new SQLException("Cant obtain Database connection !!!");
        }
    }
    private static Optional<DataSource> getInstance() {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/crm");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(dataSource);
    }



}


