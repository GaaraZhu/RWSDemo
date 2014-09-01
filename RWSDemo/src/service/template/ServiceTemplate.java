package service.template;

import java.util.List;

public interface ServiceTemplate<T> {

	public void add(T object);

	public T findById(int id);

	public List<T> findByExample(T object);

	public List<T> findAll();

	public void update(T object);

}
