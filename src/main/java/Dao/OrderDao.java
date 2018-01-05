package Dao;

import java.util.ArrayList;

import model.Order;

public interface OrderDao {

	public ArrayList<Order> getOrderList(int customer_id);
	public void addOrder(Order order);
	public void deleteOrder(int order_id);
	public void updateOrder(Order order);
}
