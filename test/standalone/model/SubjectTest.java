package standalone.model;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class SubjectTest {

	@Test
	public void test() {
		Subject subject = Subject.getSubject();
		Assert.assertNotNull(subject);
	}

}
