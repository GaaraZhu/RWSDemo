package service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import service.EmployeeService;
import service.template.impl.AbstractServiceTemplate;
import dao.EmployeeDao;
import dao.template.DaoTemplate;
import domain.Employee;

@Service(value = "employeeService")
public class EmployeeServiceImpl extends AbstractServiceTemplate<Employee>
		implements EmployeeService {

	@Resource
	private EmployeeDao employeeDao;

	@Override
	protected DaoTemplate<Employee> getDao() {
		return employeeDao;
	}

}
