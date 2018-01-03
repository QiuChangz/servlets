package listener;

import java.util.ArrayList;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * 监听游客访问信息和应用启动消息
 * @author QiuSama
 *
 */
public class CountListener implements HttpSessionListener, ServletRequestAttributeListener{
	
	private int visitor = 0;
	private int user = 0;
	private ArrayList<String> userList = new ArrayList<String>();

	public void sessionCreated(HttpSessionEvent hEvent) {
		hEvent.getSession().getServletContext().setAttribute("visitor", ++this.visitor);
	}
	
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("用户离开了！");
		//检查session销毁时对应用户身份
		//如果是游客那么游客数减一，其余不变，如果是已登陆用户则将维护的用户列表里删除对应用户同时将user减一,将游客数加一
		String user_name = (String) httpSessionEvent.getSession().getAttribute("login");
		if(userList.contains(user_name)) {
			userList.remove(user_name);
			httpSessionEvent.getSession().getServletContext().setAttribute("visitor", ++this.visitor);
			httpSessionEvent.getSession().getServletContext().setAttribute("user", --this.user);
		}else {
			if(this.visitor == 0) {
				httpSessionEvent.getSession().getServletContext().setAttribute("visitor", this.visitor);
			}else {
				httpSessionEvent.getSession().getServletContext().setAttribute("visitor", --this.visitor);
			}
		}
		
	}
	
	public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
		
	}
	
	public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
		String user_name = servletRequestAttributeEvent.getServletRequest().getParameter("login");
		if(!userList.contains(user_name)) {
			userList.add(user_name);
			servletRequestAttributeEvent.getServletContext().setAttribute("user", ++this.user);
		}
		if(visitor == 0) {
			servletRequestAttributeEvent.getServletContext().setAttribute("visitor", this.visitor);
		}else {
			servletRequestAttributeEvent.getServletContext().setAttribute("visitor", --this.visitor);
		}
		
	}
	
	public void attributeDestroyed(ServletRequestAttributeEvent servletRequestAttributeEvent) {
		
	}
	
	
}
