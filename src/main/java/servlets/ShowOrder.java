package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowOrder
 */
public class ShowOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 设置数据库链接
	 */
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrder() {
        super();
        // TODO Auto-generated constructor stub
    }


  public void init(){
  	try {
  			Class.forName("com.mysql.jdbc.Driver");
  			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myj2ee?useSSL=false","root","741236985qcz");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("customer_name"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
        
		response.setContentType("text/html;charset=utf-8");
		
		ServletContext context = getServletContext();
		
		//在线用户和游客用户
		int user = (Integer)context.getAttribute("user");
		int visitor = (Integer)context.getAttribute("visitor");
		String customer_name = request.getParameter("login");
		ResultSet resultSet = null;
		
        Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "select customer_id,password from customer where customer_name = '" + customer_name+"'";
			resultSet = statement.executeQuery(sql);
			
			resultSet.next();
			int customer_id = resultSet.getInt("customer_id");
			String search_order = "select o.order_num as order_num," + 
					" o.total_price as total_price," + 	
					" od.goods_id as goods_id," + 
					" od.goods_name as goods_name," + 
					" od.goods_num as goods_num," + 
					" od.goods_price as goods_price "
					+ "from myj2ee.order o "
					+ "inner join order_detail od on o.order_num = od.order_num "
					+ "where o.customer_id = '"+customer_id+"'";
			ResultSet order_set = statement.executeQuery(search_order);
		
	        if(!order_set.next()) {
	        	out.println("<h1>您暂时还没有订单</h1>");
	        }else {
	        	order_set.previous();

				out.println("<table><tr>"
						+ "<th>订单号</th>"
						+ "<th>商品名称</th>"
						+ "<th>商品编号</th>"
						+ "<th>商品单价</th>"
						+ "<th>商品数量</th>"
						+ "<th>是否缺货</th></tr>");
	        }
			while (order_set.next()) {
				int goods_id = order_set.getInt("goods_id");
				int goods_num = order_set.getInt("goods_num");
				String goods_name = order_set.getString("goods_name");
				out.println("<tr>"
						+ "<td>"+order_set.getString("order_num")+"</td>"
						+ "<td>"+goods_name+"</td>"
						+ "<td>"+goods_id+"</td>"
						+ "<td>"+order_set.getInt("goods_price")+"</td>"
						+ "<td>"+goods_num+"</td>");
				Statement temp = connection.createStatement();
				ResultSet goods_set = temp.executeQuery("select goods_num from goods where goods_id = "+order_set.getInt("goods_id"));
				
				if(goods_set.next()&&goods_num <= goods_set.getInt("goods_num")) {
					out.println("<td>否</td></tr>");
				}else {
					out.println("<td>是</td></tr>");
					out.println("<script>alert(\""+goods_name+"缺货！\")</script>");
				}
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("当前在线人数："+user);
		out.println("当前游客人数："+visitor);
		out.println("总用户："+(user+visitor));
		out.println("<form method='GET' action='"
                		+ response.encodeURL(request.getContextPath()+"/Login")
                	+ "'>");
		out.println("<input type='submit' name='Submit' value='Logout'></form>");
		out.println("<script language='JavaScript'>window.onbeforeunload=function (event){   " +
				"if(event.clientX>document.body.clientWidth && event.clientY < 0 || event.altKey){   " + 
				"alert(\"你关闭了浏览器\");   " + 
				"}else{   " + 
				"alert(\"你正在刷新页面\");   " + 
				"};   " + 
				"};</script>");
		out.println("</body></html>");
		
	}

}
