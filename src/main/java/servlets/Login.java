package servlets;


import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Properties;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.ServiceFactory;
import model.Customer;
//import javax.sql.DataSource;
//import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private DataSource dataSource = null;
//    private Connection connection;
    private int visitor = 0;
    private ArrayList<String> userList = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

//    public void init() {
//    	
//    	InitialContext jndiContext = null;
//
//		Properties properties = new Properties();
//		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
//		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
//		try {
//			jndiContext = new InitialContext(properties);
//			dataSource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/myj2ee");
//			System.out.println("got context");
//			System.out.println("About to get ds---myj2ee");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//    	try {
//    		Class.forName("com.mysql.jdbc.Driver");
//    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myj2ee?useSSL=false","root","741236985qcz");
//		} catch (Exception e) {
//			// TODO: handle exception
//				e.printStackTrace();
//		}
    	
//    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login="";
		HttpSession session = request.getSession(false);
		Cookie cookie = null;
        Cookie[] cookies = request.getCookies();

		if(null == session) {
        	session = request.getSession();
        }
//        Integer ival = new Integer(1);
        		
        if (null != cookies) {
            // Look through all the cookies and see if the
            // cookie with the login info is there.
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("LoginCookie")) {
                    login=cookie.getValue();
                    break;
                }
            }
        }
    
        // Logout action removes session, but the cookie remains
        if (null != request.getParameter("Logout")) {
            if (null != session) {
            	session.invalidate();
                session = null;
            }
        }
        
        ServletContext Context= getServletContext();
		if(null == Context.getAttribute("visitor")&&!userList.contains(login)) {
			++visitor;
			userList.add(login);
		}else {
			visitor= (Integer)Context.getAttribute("visitor");
		}

		
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        out.println(
                "<form method='POST' action='"
                    + response.encodeURL(request.getContextPath()+"/Login")
                    + "'>");
        out.println(
            "login: <input type='text' name='login' value='" + login + "'>");
        out.println(
            "password: <input type='password' name='password' value=''>");
        out.println("<input type='submit' name='Submit' value='Submit'>");
   
        out.println("<p>Servlet is version @version@</p>");
    out.println("</p>总游客数： " + visitor);
       
    out.println("</form>");
     out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
        out.println("<html>"
        		+ "<head><title>订单信息</title>"
        		+ "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs\r\n" + 
        		"/jquery/1.4.0/jquery.min.js\"></script></head>"
        		+ "<body>");
        

		String customer_name = request.getParameter("login");
		String password = request.getParameter("password");
		Customer customer = ServiceFactory.getCustomerManagerService().getCustomerInfo(customer_name);
		if(null!=customer&&customer.getPassword().equals(password)) {
			
		
//		ResultSet resultSet = null;
//        
//		try {
////			Connection connection = dataSource.getConnection();
//			Statement statement = connection.createStatement();
//			String sql = "select password from customer where customer_name = '" + customer_name+"'";
//			resultSet = statement.executeQuery(sql);
//			if(resultSet.next()&&resultSet.getString("password").equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("login", customer_name);
				int visitor;
				if(null == request.getAttribute("visitor")) {
					visitor = 1;
					request.setAttribute("visitor", visitor);
				}else{
					visitor = (Integer) request.getAttribute("visitor");
				}
				request.getRequestDispatcher("/ShowOrder").forward(request,response);
			}else {
				out.println("<h1>用户名或密码错误</h1>");
				out.println("<form method='GET' action='"
						+ response.encodeURL(request.getContextPath()+"/Login")
						+ "'>");
				out.println("<input type='submit' name='Logout' value='Logout'></form>");
			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}

}

