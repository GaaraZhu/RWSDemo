package dao.template;

import java.util.List; 

public interface DaoTemplate<T> {

	public void push(T object);

	public T findById(int id);

	public List<T> findByExample(T object);

	public List<T> findAll();

	public void update(T object);

}
