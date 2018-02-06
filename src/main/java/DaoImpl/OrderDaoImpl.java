package DaoImpl;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;

//import Dao.DaoHelper;
import Dao.OrderDao;
import model.Order;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

//	private Connection connection;
	
	private static OrderDao orderDaoImpl = new OrderDaoImpl();
//	private static DaoHelper daoHelper = DaoHelperImpl.getInstance();
	
	private OrderDaoImpl() {
		
	}
	public static OrderDao getInstance() {
		return orderDaoImpl;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Order> getOrderList(int customer_id) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		ResultSet resultSet = null;
//		ArrayList<Order> orderList = new ArrayList<Order>();
//		Order order = new Order();
//		order.setCustomerId(customer_id);
//		try {
//			statement = connection.createStatement();
//			String sql = "select * from myj2ee.`order` where customer_id = '"+customer_id+"'";
//			resultSet = statement.executeQuery(sql);
//			while(resultSet.next()) {
//				order.setCustomerId(resultSet.getInt("customer_id"));
//				order.setOrderId(resultSet.getInt("order_id"));
//				order.setOrderNum(resultSet.getString("order_num"));
//				order.setTotalPrice(resultSet.getInt("total_price"));
//				orderList.add(order);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
//		return orderList;
		return (ArrayList<Order>)super.getList(Order.class, "customer_id",Integer.toString(customer_id));
	}

	public void addOrder(Order order) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			String sql = "insert into myj2ee.`order` (order_num,total_price,customer_id) values ("
//							+ "'"+order.getOrderNum()+"'"
//							+ "'"+order.getTotalPrice()+"'"
//							+ "'"+order.getCustomerId()+"')";
//			statement.executeQuery(sql);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
		super.save(order);
	}

	private Order getOrder(int order_id) {
		Order order = (Order) super.load(Order.class, Integer.toString(order_id));
		return order;
	}
	public void deleteOrder(int order_id) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			String sql = "delete from myj2ee.order where order_id = '"+order_id+"'";
//			statement.executeQuery(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
		Order order = getOrder(order_id);
		super.delete(order);
	}

	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
//		connection = daoHelper.getConnection();
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			String sql = "update myj2e.order set "
//					+ "customer_id = '"+order.getCustomerId()+"',"
//					+ "order_num = '"+order.getOrderNum()+"',"
//					+ "total_price = "+order.getTotalPrice()+"' "
//					+ "where order_id = '"+order.getOrderId()+"'";
//			statement.executeQuery(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			daoHelper.closeStatement(statement);
//			daoHelper.closeConnection(connection);
//		}
		super.update(order);
	}

}
