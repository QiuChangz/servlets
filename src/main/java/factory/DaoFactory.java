package factory;

import Dao.CustomerDao;
import Dao.GoodsDao;
import Dao.OrderDao;
import Dao.OrderDetailDao;
import DaoImpl.CustomerDaoImpl;
import DaoImpl.GoodsDaoImpl;
import DaoImpl.OrderDaoImpl;
import DaoImpl.OrderDetailDaoImpl;

public class DaoFactory {
	
	public static CustomerDao getCustomerDao() {
		return CustomerDaoImpl.getInstance();
	}
	
	public static GoodsDao getGoodsDao() {
		return GoodsDaoImpl.getInstance();
	}
	
	public static OrderDao getOrderDao() {
		return OrderDaoImpl.getInstance();
	}
	
	public static OrderDetailDao getOrderDetailDao() {
		return OrderDetailDaoImpl.getInstance();
	}
	
}
