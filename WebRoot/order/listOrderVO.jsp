<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css" />
		<%@ include file="../common/incHead.jsp"%>
		<%@ include file="../common/incFooter.jsp"%>
		<%@ include file="../common/incBanner.jsp"%>
		
		
	</head>
	<body>
	  <!-- 
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0" action="list!listMainOrderVO" namespace="/order"
				method="post">
				<table>
					<tr>
						<td>请选择日期:&nbsp;</td>
						<td>&nbsp;
							<input id="d5221" name="startDate" class="Wdate" type="text"
								onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})" />
							至
							<input id="d5222" name="endDate" class="Wdate" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})" />
							&nbsp;
						</td>
						<td>
							&nbsp;
							<input icon="icon-search" type="submit" value="搜索" />
							&nbsp;
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<center>
			<center>
			<display:table id="idListTb" name="mainorderVOs" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/order/list!listMainOrderVO.do">
				<display:caption>订单统计列表</display:caption>
				<display:column title="订单类型">
					<c:choose>
						<c:when test="${idListTb.orderType==1}">空中充值</c:when>
						<c:when test="${idListTb.orderType==2}">卡密</c:when>
					</c:choose>
				</display:column>
				<display:column title="交易金额">
					<fmt:formatNumber maxFractionDigits="4" value="${idListTb.sumValue }"/>
				</display:column>
				<display:column title="总进货价">
					<fmt:formatNumber maxFractionDigits="4" value="${idListTb.sumRetail }"/>
				</display:column>
				<display:column title="交易佣金">
					<fmt:formatNumber maxFractionDigits="4" value="${idListTb.sumComm }"/>
				</display:column>
			</display:table>
		</center>
		<br />
		<br />

		<a href="user!add.html"></a>
	 -->
	 <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                     <div class="cxjj fixed">
                              <div class="cxjjtable2 cxjjtable" style="width:550px;">
                                    <s:form id="form0" name="form0" action="list!listMainOrderVO" namespace="/order"
									method="post">
									<table>
										<tr>
											<td>请选择日期:&nbsp;</td>
											<td>&nbsp;
												<input id="d5221" name="startDate" class="date1" type="text"
													onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-{%M-2}-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})" />
												至
												<input id="d5222" name="endDate" class="date2" type="text"
													onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})" />
												&nbsp;
											</td>
											<td>
												&nbsp;
												<button class="sousuo1" type="submit" value="搜索" ></button>
												&nbsp;
											</td>
										</tr>
									</table>
								</s:form>
                              </div>
                            
                            <p class="jjcx">交易汇总统计列表</p>
                     </div>
            
                    <div class="modify">
							<display:table id="idListTb" name="mainorderVOs" style=""
								sort="external" pagesize="${pageSize}" cellspacing="1"
								class="list_tb_002"
								requestURI="${pageContext.request.contextPath}/order/list!listMainOrderVO.do">
								<display:column title="订单类型">
									<c:choose>
										<c:when test="${idListTb.orderType==1}">空中充值</c:when>
										<c:when test="${idListTb.orderType==2}">卡密</c:when>
									</c:choose>
								</display:column>
								<display:column title="交易金额">
									<fmt:formatNumber maxFractionDigits="4" value="${idListTb.sumValue }"/>
								</display:column>
								<display:column title="总进货价">
									<fmt:formatNumber maxFractionDigits="4" value="${idListTb.sumRetail }"/>
								</display:column>
								<c:if test="${sessionScope.user.usergroup.groupType<=2}">
									<display:column title="进货价">
										<fmt:formatNumber maxFractionDigits="4" value="${idListTb.sumStockPrice }"/>
									</display:column>
								</c:if>
								<display:column title="交易佣金">
									<fmt:formatNumber maxFractionDigits="4" value="${idListTb.sumComm }"/>
								</display:column>
							</display:table>
                          
                    </div>
            
          </div>
      </div>
	</body>
</html>
