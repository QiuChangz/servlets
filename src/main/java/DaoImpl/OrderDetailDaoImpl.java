package DaoImpl;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

//import Dao.DaoHelper;
import Dao.OrderDetailDao;
import model.Goods;
import model.OrderDetail;
import util.HibernateUtil;

public class OrderDetailDaoImpl extends BaseDaoImpl implements OrderDetailDao{

//	private Connection connection;
	
	private static OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
//	private static DaoHelper daoHelper = DaoHelperImpl.getInstance();
	
	private OrderDetailDaoImpl() {
		
	}
	
	public static OrderDetailDao getInstance() {
		return orderDetailDao;
	}
	public ArrayList<OrderDetail> getOrderDetail(String order_num) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		ResultSet resultSet = null;
//		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
//		try {
//			OrderDetail orderDetail = new OrderDetail();
//			orderDetail.setOrderNum(order_num);
//			statement = connection.createStatement();
//			String sql = "select * from myj2ee.order_detail where order_num = '"+order_num+"'";
//			resultSet = statement.executeQuery(sql);
//			while(resultSet.next()) {
//				orderDetail.setGoodsId(resultSet.getInt("goods_id"));
//				orderDetail.setGoodsName(resultSet.getString("goods_name"));
//				orderDetail.setGoodsNum(resultSet.getInt("goods_num"));
//				orderDetail.setGoodsPrice(resultSet.getInt("goods_price"));
//				orderDetailList.add(orderDetail);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
//		Iterator<OrderDetail> iterator = orderDetailList.iterator();
//		while(iterator.hasNext()) {
//			OrderDetail orderDetail = iterator.next();
//			System.out.println(orderDetail.getGoodsName());
//		}
		@SuppressWarnings("unchecked")
		ArrayList<OrderDetail> orderDetailList = (ArrayList<OrderDetail>) super.getList(OrderDetail.class, "order_num", order_num);
		return orderDetailList;
	}

	public void addOrderDetail(Goods goods, int num) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
			String sql = "insert into myj2ee.order_detail (goods_id,goods_name,goods_num,goods_price) values ("
					+ "'"+goods.getGoodsId()+"',"
							+ "'"+goods.getGoodsName()+"',"
							+ "'"+num+"',"
							+ "'"+goods.getGoodsPrice()+"')";
//			statement.executeQuery(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
		try {
			//	System.out.println("ready to getsession");	
				Session session =HibernateUtil.getSession() ;
				Transaction tx=session.beginTransaction();
				Query query=session.createQuery(sql);
				query.executeUpdate();
				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void deleteOrderDetail(int goods_id,String order_num) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
			String sql = "delete from myj2ee.order_detail where order_num = '"+order_num+"' and goods_id = '"+goods_id+"'";
//			statement.executeQuery(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
		
		try {
			//	System.out.println("ready to getsession");	
				Session session =HibernateUtil.getSession() ;
				Transaction tx=session.beginTransaction();
				Query query=session.createQuery(sql);
				query.executeUpdate();
				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void updateOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
			String sql = "update myj2ee.order_detail set"
							+ " goods_num = '"+orderDetail.getGoodsNum()+"'"
							+ " where order_num = '"+orderDetail.getOrderNum()+"'"
							+ " and goods_id = '"+orderDetail.getGoodsId()+"'";
//			statement.executeQuery(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
		
		try {
			//	System.out.println("ready to getsession");	
				Session session =HibernateUtil.getSession() ;
				Transaction tx=session.beginTransaction();
				Query query=session.createQuery(sql);
				query.executeUpdate();
				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
