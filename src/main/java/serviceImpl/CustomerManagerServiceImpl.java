package serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import factory.DaoFactory;
import model.Customer;
import model.Order;
import model.OrderDetail;
import service.CustomerManagerService;

public class CustomerManagerServiceImpl implements CustomerManagerService {

	private static CustomerManagerService customerManagerService = new CustomerManagerServiceImpl();
	
	private CustomerManagerServiceImpl() {
		
	}
	
	public static CustomerManagerService getInstance() {
		return customerManagerService;
	}
	public Customer getCustomerInfo(String customer_name) {
		// TODO Auto-generated method stub
		return DaoFactory.getCustomerDao().getCustomer(customer_name);
	}

	public HashMap<Order, ArrayList<OrderDetail>> getOrderList(int customer_id) {
		// TODO Auto-generated method stub
		HashMap<Order, ArrayList<OrderDetail>> result = new HashMap<Order, ArrayList<OrderDetail>>();
		ArrayList<Order> orderList = DaoFactory.getOrderDao().getOrderList(customer_id);
		Iterator<Order> iterator = orderList.iterator();
		while (iterator.hasNext()) {
			Order order = iterator.next();
			String order_num = order.getOrderNum();
			ArrayList<OrderDetail> orderDetailList = DaoFactory.getOrderDetailDao().getOrderDetail(order_num);
			result.put(order, orderDetailList);
		}
		return result;
	}

}
