package tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import bussiness.OrderListBean;
import qiusama.j2ee.servlets.model.Order;
//import model.Order;
//import model.OrderDetail;
import qiusama.j2ee.servlets.model.OrderDetail;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OrderInfoHandler extends SimpleTagSupport{

	public void doTag() throws JspException, IOException {
		
		try {
			OrderListBean orderListBean = (OrderListBean) getJspContext().findAttribute("orderListBean");
			Iterator<Entry<Order, ArrayList<OrderDetail>>> iterator = orderListBean.getOrderList().entrySet().iterator();
			JspWriter out = getJspContext().getOut();
			while(iterator.hasNext()) {
				Entry<Order, ArrayList<OrderDetail>> entry = iterator.next();
				Order order = entry.getKey();
				ArrayList<OrderDetail> orderDetails = entry.getValue();
				Iterator<OrderDetail> orderIterator = orderDetails.iterator();
				
				boolean flag = true; //标记第一次循环，用于合并单元格
				
				int rowspan = orderDetails.size();
				while(orderIterator.hasNext()) {
					OrderDetail orderDetail = orderIterator.next();
					if(flag) {
						flag = false;
						out.println("<tr><TD align='center' rowspan='"+rowspan+"'>"
								+ order.getOrderNum() + "</TD>");
						out.println("<TD align='center'>"
								+ orderDetail.getGoodsId() + "</TD>");
					}else {
						out.println("<tr><TD align='center'>"
								+ orderDetail.getGoodsId() + "</TD>");
					}
					out.println("<TD align='center'>"
							+ orderDetail.getGoodsName() + "</TD>");
					out.println("<TD align='center'>"
							+ orderDetail.getGoodsPrice() + "</TD>");
					out.println("<TD align='center'>"
							+ orderDetail.getGoodsNum() + "</TD>");
				}
				out.println("<TD align='center' rowspan='"+rowspan+"'>"
						+ order.getTotalPrice()+"</TD></tr>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
	}
	
}
