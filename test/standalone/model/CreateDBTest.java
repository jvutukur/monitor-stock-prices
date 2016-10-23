package standalone.model;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class CreateDBTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		CreateDB createDB = new CreateDB();
		boolean successMessage = createDB.createSchema();
		Assert.assertEquals(true, successMessage);
	}

}
