package service.template.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import service.template.ServiceTemplate;
import dao.template.DaoTemplate;

public abstract class AbstractServiceTemplate<T> implements ServiceTemplate<T> {

	@Override
	@Transactional(readOnly = false)
	public void add(T object) {
		getDao().push(object);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findByExample(T object) {
		return getDao().findByExample(object);
	}
	
	@Override
	@Transactional(readOnly = true)
	public T findById(int id) {
		return getDao().findById(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public void update(T object) {
		getDao().update(object);
	}

	protected abstract DaoTemplate<T> getDao();

}
