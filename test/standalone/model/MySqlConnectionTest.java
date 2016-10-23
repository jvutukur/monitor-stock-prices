package standalone.model;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MySqlConnectionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetConnection() {
		Connection con = mySqlConnection.getConnection();
		Assert.assertNotNull(con);
	}

}
