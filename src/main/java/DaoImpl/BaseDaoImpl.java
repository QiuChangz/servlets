package DaoImpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Dao.BaseDao;
import util.HibernateUtil;

public class BaseDaoImpl implements BaseDao{

	public void flush() {
		// TODO Auto-generated method stub
		HibernateUtil.getSession().flush();
	}

	public void clear() {
		// TODO Auto-generated method stub
		HibernateUtil.getSession().clear();
	}

	@SuppressWarnings("rawtypes")
	public Object load(Class c, String id) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Object o=session.get(c, id);
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public void save(Object bean) {
		// TODO Auto-generated method stub
		try {
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.merge(bean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Object bean) {
		// TODO Auto-generated method stub
		try {
			//	System.out.println("ready to getsession");	
				Session session =HibernateUtil.getSession() ;
				Transaction tx=session.beginTransaction();
				session.update(bean);
				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
			} 
	}

	public void delete(Object bean) {
		// TODO Auto-generated method stub
		try {
			//	System.out.println("ready to getsession");	
				Session session =HibernateUtil.getSession() ;
				Transaction tx=session.beginTransaction();
				session.delete(bean);;
				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
			} 
	}

	@SuppressWarnings("rawtypes")
	public Object load(Class c, String name, String value) {
		// TODO Auto-generated method stub
		try {
			//	System.out.println("ready to getsession");	
				Session session =HibernateUtil.getSession() ;
				Transaction tx=session.beginTransaction();
				String hql = "from "+c.toString()+" as o where o."+name+"='"+value+"'";
				Query query=session.createQuery(hql);
				List result=query.getResultList();
				tx.commit();

				return result.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public Object getList(Class c,String name, String value) {
		// TODO Auto-generated method stub
		try {
			//	System.out.println("ready to getsession");	
				Session session =HibernateUtil.getSession() ;
				Transaction tx=session.beginTransaction();
				String hql = "from "+c.toString()+" as o where o."+name+"='"+value+"'";
				Query query=session.createQuery(hql);
				List result=query.getResultList();
				tx.commit();

				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

}
