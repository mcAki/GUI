<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
	</script>
	</head>
	<body style="min-width: 1000px;">
		<%@ include file="../common/incBanner.jsp"%>
		<%--
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list" namespace="/supply"
			method="post">
				<table>
					<tr>
						<td>供货商名称:&nbsp;</td>
						<td>&nbsp;<s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select>&nbsp;</td>
						<td>&nbsp;供货商类型:&nbsp;</td>
						<td><s:select name="supplyType" list="%{#{0:'===选择供货商类型===',1:'自有',2:'在线'}}"/></td>
						<td><s:select name="isUsed" list="%{#{-1:'===选择状态===',0:'禁用',1:'可用'}}"/></td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				<br/>
				</s:form>
		</div>
		<center>
			<display:table id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/supply/list.do">
				<display:caption>供货商列表</display:caption>
				<display:column title="供货商名称" property="supplyName"/>
				<display:column title="供货商类型" >
					<c:choose>
						<c:when test="${idListTb.supplyType==1}">自有</c:when>
						<c:when test="${idListTb.supplyType==2}">在线</c:when>
					</c:choose>
				</display:column>
				<display:column title="销售类型" >
					<c:choose>
						<c:when test="${idListTb.sellType==1}">空中充值</c:when>
						<c:when test="${idListTb.sellType==2}">卡密</c:when>
					</c:choose>
				</display:column>
				<display:column property="createTime" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="供货商余额" property="balance">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.balance }"/>
				</display:column>
				<display:column title="评分" property="avgScore"/>
				<display:column title="可用">
					<c:choose>
						<c:when test="${idListTb.isUsed==0}">禁用</c:when>
						<c:when test="${idListTb.isUsed==1}">可用</c:when>
					</c:choose>
				</display:column>
				<display:column title="操作">
					<a href="viewSupply.do?supplyId=${idListTb.id}">修改</a>&nbsp;
					<a href="page!recharge.do?supplyId=${idListTb.id}">充值</a>&nbsp;
					<c:choose>
						<c:when test="${idListTb.isUsed==1}">
							<a href="doDel.do?supplyId=${idListTb.id}">禁用</a>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="doRecover.do?supplyId=${idListTb.id}">恢复</a>&nbsp;
						</c:otherwise>
					</c:choose>
					
				</display:column>
			</display:table>
		</center>
		<br />
		<br />
		 --%>
<div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<div class="quota cxjjtable" style="width:700px;">
							<s:form id="form0" name="form0"
								action="list!listAlipay" namespace="/alipayCharge"
								method="post">
									<table>
										<tr >
											 <td align="left" width="350" colspan="2">
						                       	 请选择日期：
						                       	
						                          <input id="d5221" name="startDate" class="date1" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
											  
												至
												<input id="d5222" name="endDate" class="date2" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/></td>
											<td width="80" align="right">经销商名：</td>
                                                <td width="100"><input name="username"  value="${param.username}" type="text" class="khmm" /></td>
											<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
										</tr>
									</table>
							 </s:form>
						</div>
						<p class="jjcx">
							支付宝订单列表
						</p>

					</div>
					<div class="modify">
                       <center>
                          <display:table id="idListTb" name="pageView" style="width:900px;"
								sort="external" pagesize="${pageSize}" cellspacing="1"
								class="list_tb_002"
								requestURI="${pageContext.request.contextPath}/alipayCharge/list!listAlipay.do">
								<display:column title="交易订单号" property="tradeNo"/>
								<display:column title="商户名" property="userName"/>
								<display:column title="状态" >
									<c:choose>
										<c:when test="${idListTb.status==0}">处理中</c:when>
										<c:when test="${idListTb.status==1}">处理成功</c:when>
										<c:when test="${idListTb.status==2}">处理失败</c:when>
									</c:choose>
								</display:column>
								<display:column property="tradeTime" title="交易时间"
									format="{0,date,yy-MM-dd HH:mm}" />
								<display:column title="交易金额">
									<fmt:formatNumber maxFractionDigits="2" value="${idListTb.totalFee }"/>
								</display:column>
								<display:column title="已到账">
									<c:choose>
										<c:when test="${idListTb.isIntoaccount==0}">未付</c:when>
										<c:when test="${idListTb.isIntoaccount==1}">已付</c:when>
									</c:choose>
								</display:column>
								<display:column title="操作">
									<c:choose>
										<c:when test="${idListTb.isIntoaccount==0}">
											<a href="alipayCharge/doRecharge.do?alipayId=${idListTb.alipayId}">支付到账</a>&nbsp;
										</c:when>
									</c:choose>
									
								</display:column>
							</display:table>
                       </center>
					</div>
				</div>
			</div>
		</div>	
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
