package dao.impl;

import org.springframework.stereotype.Repository;

import dao.EmployeeDao;
import dao.template.impl.AbstractDaoTemplate;
import domain.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDaoTemplate<Employee> implements
		EmployeeDao {

}
