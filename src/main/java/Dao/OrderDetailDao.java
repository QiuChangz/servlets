package Dao;

import java.util.ArrayList;


import model.Goods;
import model.OrderDetail;

public interface OrderDetailDao extends BaseDao {

	public ArrayList<OrderDetail> getOrderDetail(String order_num);
	public void addOrderDetail(Goods goods, int num);
	public void deleteOrderDetail(int goods_id,String order_num);
	public void updateOrderDetail(OrderDetail orderDetail);
}
