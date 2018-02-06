package Dao;


public interface BaseDao {

	public void flush();

	public void clear() ;

	public Object load(Class c, String id) ;

	public void save(Object bean) ;

	public void update(Object bean) ;

	public void delete(Object bean) ;
	
	public Object load(Class c,String name, String value);
	
	public Object getList(Class c,String name, String value);
	
}
