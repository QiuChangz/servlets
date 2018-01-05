package tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoginHandler extends BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspTagException{
		HttpSession session = pageContext.getSession();
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		if(null == session) {
			JspWriter out = pageContext.getOut();
			try {
				out.println("<%" + 
						"   String site = new String("+response.encodeURL(request.getContextPath()+"/Login") +")"+ 
						"   response.setStatus(response.SC_MOVED_TEMPORARILY);" + 
						"   response.setHeader(\"Location\", site); " + 
						"%>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return EVAL_BODY_INCLUDE;
//		if(customer_name.equals("")) {
//			
//		}
	}
	
	
//	public void doTag() throws JspException, IOException {
//		String customer_name = (String)getJspContext().findAttribute("login");
//		if(customer_name.equals("")) {
//			JspWriter out = getJspContext().getOut();
//			out.println("<%" + 
//					"   String site = new String("++");" + 
//					"   response.setStatus(response.SC_MOVED_TEMPORARILY);" + 
//					"   response.setHeader(\"Location\", site); " + 
//					"%>");
//		}
//	}
	
		
}
