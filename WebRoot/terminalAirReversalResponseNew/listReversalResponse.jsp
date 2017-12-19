<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
		function showOrderDetail(mainOrderId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/list!listOrderdetail.do?mainOrderId='
						+ mainOrderId , 700, 600);
		}
	</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list" namespace="/order"
			method="post">
				<table>
					<tr>
					</tr>
				</table>
				</s:form>
		</div>
		<center>
			<display:table export="true" id="idListTb" name="airReversalResponseNews" style="width:960px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/order/listReversalResponse.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption title="订单信息">冲正记录列表</display:caption>
				<display:column title="冲正结果" >
					<c:choose>
						<c:when test="${idListTb.respCode=='0'}">成功</c:when>
						<c:otherwise>失败</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="响应描述" property="respDisc"/>
				<display:column title="订单流水号" property="storeSeq"/>
				<display:column title="请求订单号" property="orderNo"/>
				<display:column title="终端编号" property="terminalNo"/>
				<display:column title="手机号码" property="mobileNum"/>
				<display:column title="充值金额" property="amount"/>
				<display:column title="签名" property="sign"/>
				<display:column property="responseTime" title="应答时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:setProperty name="export.excel.filename" value="times.xls"/> 
			</display:table>
		<br />
		<br />
		<script type="text/javascript">
			displaytagExportLink();
	    </script>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
