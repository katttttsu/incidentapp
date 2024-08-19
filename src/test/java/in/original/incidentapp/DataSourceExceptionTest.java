package in.original.incidentapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessResourceFailureException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DataSourceExceptionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSourceConnectionFailure() {
        assertThrows(DataAccessResourceFailureException.class, () -> {
            try (Connection connection = dataSource.getConnection()) {
                // Attempting to connect to the database
            } catch (SQLException e) {
                // If there is an SQLException, wrap it in a DataAccessResourceFailureException
                throw new DataAccessResourceFailureException("Failed to connect to the database", e);
            }
        });
    }
}
