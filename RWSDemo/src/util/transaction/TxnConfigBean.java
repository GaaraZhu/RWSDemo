package util.transaction;

import java.util.List;

public class TxnConfigBean {

	private List<String> methodListOfReadTx;

	public List<String> getMethodListOfReadTx() {
		return methodListOfReadTx;
	}

	public void setMethodListOfReadTx(List<String> methodListOfReadTx) {
		this.methodListOfReadTx = methodListOfReadTx;
	}

}
