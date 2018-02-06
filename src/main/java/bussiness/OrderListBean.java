package bussiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import qiusama.j2ee.servlets.model.Order;
import qiusama.j2ee.servlets.model.OrderDetail;

//import model.Order;
//import model.OrderDetail;

public class OrderListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Order, ArrayList<OrderDetail>> orderList;
	
	public HashMap<Order, ArrayList<OrderDetail>> getOrderList(){
		return this.orderList;
	}
	
	public void setOrderList(HashMap<Order, ArrayList<OrderDetail>> orderList) {
		this.orderList = orderList;
	}
}