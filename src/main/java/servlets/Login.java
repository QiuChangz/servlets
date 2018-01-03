package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myj2ee?useSSL=false","root","741236985qcz");
//    		Context context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/myj2ee");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
/*		ServletContext Context= getServletContext();
		int webCounter= Integer.parseInt((String) Context.getAttribute("webCounter"));
		if (null == request.getParameter("Logout")) {
			System.out.println("pageCounter++\n");
			webCounter++;
			Context.setAttribute("webCounter", Integer.toString(webCounter));
		}*/

		String login="";
		HttpSession session = request.getSession(false);
		Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
  
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
//    out.println("</p>You are visitor number " + webCounter);
       
    int visitor = (Integer) request.getServletContext().getAttribute("visitor");
    out.println("</form>");
    out.println("当前游客人数："+visitor);
     out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String customer_name = request.getParameter("login");
		String password = request.getParameter("password");
		ResultSet resultSet = null;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
        out.println("<html>"
        		+ "<head><title>订单信息</title>"
        		+ "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs\r\n" + 
        		"/jquery/1.4.0/jquery.min.js\"></script></head>"
        		+ "<body>");
        
		try {
			Statement statement = connection.createStatement();
			String sql = "select customer_id,password from customer where customer_name = '" + customer_name+"'";
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()&&resultSet.getString("password").equals(password)) {
				HttpSession session = request.getSession(false);
				//用户验证成功后创建会话
				if(null == session) {
		        	session = request.getSession();
		        }
				request.getRequestDispatcher("/ShowOrder").forward(request,response);
			}else {
				out.println("<h1>用户名或密码错误</h1>");
				out.println("<button onclick=\"history.back()\">返回</button></body></html>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}

