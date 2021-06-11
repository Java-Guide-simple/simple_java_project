package in.stack.eStore.productTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import in.stack.eStore.connection.DBConnection;
@Tag("Secondary")
public class ConnectionTest {

	DBConnection db = new DBConnection();

	@Test
	public void getConnectionTest() throws Exception {
		Connection con = db.getConnections();
		assertNotNull(con);
		assertTrue(con.isValid(0));
		con.close();
	}

	public void getStatementTest() throws Exception {
		Statement stmnt = db.getConnection();
		assertNotNull(stmnt);
		stmnt.close();
	}
}
