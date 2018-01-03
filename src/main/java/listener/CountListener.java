package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class CountListener implements ServletContextListener, HttpSessionListener{
	
	private int user = 0;
	
	public void contextInitialized(ServletContextEvent se) {
		System.out.println("启动应用");
	}
	
	public void contextDestroyed(ServletContextEvent se) {
		System.out.println("关闭应用");
	}
	
	public void sessionCreated(HttpSessionEvent hEvent) {
		System.out.println("您有新的用户上线！");
		hEvent.getSession().getServletContext().setAttribute("user", ++this.user);
	}
	
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("一个用户下线了！");
		httpSessionEvent.getSession().getServletContext().setAttribute("user", --this.user);
	}

	
}
