package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bussiness.OrderListBean;
import factory.ServiceFactory;
import model.Customer;

/**
 * Servlet implementation class ShowOrder
 */
public class ShowOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 设置数据库链接
	 */
//	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

//
//  public void init(){
//  	try {
//  			Class.forName("com.mysql.jdbc.Driver");
//  			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myj2ee?useSSL=false","root","741236985qcz");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//  }
//    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		PrintWriter out = response.getWriter();
		
		processRequest(request, response);
//		ResultSet resultSet = null;
//		
//        Statement statement;
//		try {
//			statement = connection.createStatement();
//			String sql = "select customer_id,password from customer where customer_name = '" + customer_name+"'";
//			resultSet = statement.executeQuery(sql);
//			
//			resultSet.next();
//			int customer_id = resultSet.getInt("customer_id");
//			String search_order = "select o.order_num as order_num," + 
//					" o.total_price as total_price," + 	
//					" od.goods_id as goods_id," + 
//					" od.goods_name as goods_name," + 
//					" od.goods_num as goods_num," + 
//					" od.goods_price as goods_price "
//					+ "from myj2ee.order o "
//					+ "inner join order_detail od on o.order_num = od.order_num "
//					+ "where o.customer_id = '"+customer_id+"'";
//			ResultSet order_set = statement.executeQuery(search_order);
//		
//	        if(!order_set.next()) {
//	        	out.println("<h1>您暂时还没有订单</h1>");
//	        }else {
//	        	order_set.previous();
//
//				out.println("<table><tr>"
//						+ "<th>订单号</th>"
//						+ "<th>商品名称</th>"
//						+ "<th>商品编号</th>"
//						+ "<th>商品单价</th>"
//						+ "<th>商品数量</th>"
//						+ "<th>是否缺货</th></tr>");
//	        }
//			while (order_set.next()) {
//				int goods_id = order_set.getInt("goods_id");
//				int goods_num = order_set.getInt("goods_num");
//				String goods_name = order_set.getString("goods_name");
//				out.println("<tr>"
//						+ "<td>"+order_set.getString("order_num")+"</td>"
//						+ "<td>"+goods_name+"</td>"
//						+ "<td>"+goods_id+"</td>"
//						+ "<td>"+order_set.getInt("goods_price")+"</td>"
//						+ "<td>"+goods_num+"</td>");
//				Statement temp = connection.createStatement();
//				ResultSet goods_set = temp.executeQuery("select goods_num from goods where goods_id = "+order_set.getInt("goods_id"));
//				
//				if(goods_set.next()&&goods_num <= goods_set.getInt("goods_num")) {
//					out.println("<td>否</td></tr>");
//				}else {
//					out.println("<td>是</td></tr>");
//					out.println("<script>alert(\""+goods_name+"缺货！\")</script>");
//				}
//	        }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		out.println("当前游客数："+visitor);
//		out.println("当前用户人数"+user);
//		total = visitor + user;
//		out.print("总人数"+total);
//		out.println("<form method='GET' action='"
//                		+ response.encodeURL(request.getContextPath()+"/Login")
//                	+ "'>");
//		out.println("<input type='submit' name='Logout' value='Logout'></form>");
//		out.println("</body></html>");
		
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		response.setContentType("text/html;charset=utf-8");

		request.setCharacterEncoding("UTF-8");
		
		ServletContext context = getServletContext();
		
		int visitor,user;
		//在线用户和游客用户
		if(null == context.getAttribute("visitor")) {
			visitor = 0;
			context.setAttribute("visitor", visitor);
		}
		
		if(null == context.getAttribute("user")) {
			user = 0;
			context.setAttribute("user", user);
		}
		
		HttpSession session = request.getSession();
		OrderListBean orderListBean = new OrderListBean();
		Customer customer = null;
		String customer_name = request.getParameter("login");
		if(null == customer_name||customer_name.equals("")) {
			try {
				context.getRequestDispatcher("/Login").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"This is a servlet error");
			}
		}
		customer = ServiceFactory.getCustomerManagerService().getCustomerInfo(customer_name);
		orderListBean.setOrderList(ServiceFactory.getCustomerManagerService().getOrderList(customer.getCustomerId()));
		try {
			if(null == customer || orderListBean.getOrderList().size()<0) {
				context.getRequestDispatcher("/Login").forward(request, response);
			}else {
				session.setAttribute("orderListBean", orderListBean);
				context.getRequestDispatcher("/Jsp/showOrder.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"This is a servlet error");
		}
	}

}
