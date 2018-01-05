package service;

import java.util.ArrayList;
import java.util.HashMap;

import model.Customer;
import model.Order;
import model.OrderDetail;

public interface CustomerManagerService {
	
	public Customer getCustomerInfo(String customer_name);
	public HashMap<Order, ArrayList<OrderDetail>> getOrderList(int customer_id);
}
