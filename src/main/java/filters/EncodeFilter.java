package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodeFilter implements Filter{

	private FilterConfig config;
	private String encoding = "utf-8";
	
	public void init(FilterConfig config) throws ServletException{
		this.config = config;
		String encoding = this.config.getInitParameter("encoding");
		if(null != encoding) {
			this.encoding = encoding;
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		response.setCharacterEncoding(encoding);
	}
	
	public void destroy() {
		this.config = null;
	}

}
