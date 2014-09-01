package test.mainTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BaseMainTest {

	protected static ApplicationContext ctx = null;

	static {
		ctx = new FileSystemXmlApplicationContext("/src/applicationContext.xml");
	}

}
