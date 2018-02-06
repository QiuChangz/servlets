package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.Customer;
import qiusama.j2ee.servlets.model.Goods;
import qiusama.j2ee.servlets.model.Order;
import qiusama.j2ee.servlets.model.OrderDetail;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	 public static SessionFactory getSessionFactory(){
		 try {
				Configuration config;
				ServiceRegistry serviceRegistry;
				config = new Configuration().configure();
				config.addAnnotatedClass(Customer.class);
				config.addAnnotatedClass(Goods.class);
				config.addAnnotatedClass(Order.class);
				config.addAnnotatedClass(OrderDetail.class);
				serviceRegistry =new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				sessionFactory=config.buildSessionFactory(serviceRegistry);	
				return sessionFactory;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	 }
	 
	 /** * gerCurrentSession ���Զ��ر�session��ʹ�õ��ǵ�ǰ��session���� * * @return */
	 public static Session getSession(){
		 return getSessionFactory().getCurrentSession();
	 }
}
