<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
			<center>
			<display:table id="idListTb" name="orderdetails" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/order/list!listOrderdetail.do">
				<display:caption>详细订单列表</display:caption>
				<display:column title="订单详细流水号" property="orderDetailId"/>
				<display:column title="订单流水号" property="mainorder.mainOrderId"/>
				<display:column title="销售商户名" property="users.userName"/>
				<display:column title="终端机号" property="terminalNo"/>
				<display:column title="充值手机" property="mobile"/>
				<display:column title="卡密ID" property="cardIds"/>
				<display:column title="购买数量" property="cardNo"/>
				<display:column title="交易总金额" property="money"/>
				<display:column title="订单状态" >
					<c:choose>
						<c:when test="${idListTb.state==-1}">已冲正</c:when>
						<c:when test="${idListTb.state==0}">申请处理</c:when>
						<c:when test="${idListTb.state==1}">处理成功</c:when>
						<c:when test="${idListTb.state==2}">处理失败</c:when>
						<c:when test="${idListTb.state==3}">处理中</c:when>
						<c:when test="${idListTb.state==255}">已取消</c:when>
					</c:choose>
				</display:column>

				<display:column property="createTime" title="销售日期"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="操作">
					<c:choose>
						<c:when test="${idListTb.state==0}"><a href="cancelOrder.do?orderId=${idListTb.mainorder.mainOrderId }">取消订单</a></c:when>
					</c:choose>
				</display:column>
			</display:table>
		</center>
		<br />
		<br />

		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
