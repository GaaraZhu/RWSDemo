package test.mainTest;

import util.transaction.TestDefinitionInjection;

public class TestInjection extends BaseMainTest {
	public static void main(String[] args) {
		TestDefinitionInjection definition = (TestDefinitionInjection) ctx
				.getBean("testDefinition");

			for (String value : definition.getMethodListOfReadTx()) {
				System.out.println(value);
			}

	}
}
