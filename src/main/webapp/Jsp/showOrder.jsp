<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="order" uri="/WEB-INF/tlds/OrderInfo.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MyOrder</title>
</head>
<body>

	<H1>OrderList</H1>
	
	<jsp:useBean id="orderListBean"
		type="bussiness.OrderListBean"
		scope="session"></jsp:useBean>
					
	<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TBODY>
				<TR>
					<TH width="15%">订单号</TH>
					<TH width="15%">货物号</TH>
					<TH width="15%">货物名</TH>
					<TH width="15%">货物单价</TH>
					<TH width="15%">货物数量</TH>
					<TH width="15%">货物总价</TH>
				</TR>
				<order:orderInfo/>
			</TBODY>
		</TABLE>
		
	<%
		ServletContext context = request.getServletContext();
		int visitor = (Integer)context.getAttribute("visitor");
		int user = (Integer)context.getAttribute("user");
		int total = visitor + user;
		out.print("<h4>在线用户人数："+visitor+" ");
		out.print("游客人数："+user+" ");
		out.println("总人数："+total+"</h4>");
	%>
	<form method="GET" action="<%=response.encodeURL(request.getContextPath() + "/Login")%>">
		<input type="submit" name="Logout" value="Logout">
	</form>
</body>
</html>