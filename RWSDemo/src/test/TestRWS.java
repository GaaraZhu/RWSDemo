package test;

import service.EmployeeService;
import test.mainTest.BaseMainTest;
import domain.Employee;

public class TestRWS extends BaseMainTest {
	public static void main(String[] args) {
		EmployeeService employeeService = (EmployeeService) ctx
				.getBean("employeeService");
		Employee employee = new Employee();
		employee.setFirstName("Yinkan");
		employee.setLastName("Zhu");

//		employeeService.add(employee);
		employeeService.findByExample(employee);
	}
}
