<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
				<%@ include file="../common/incHead.jsp"%>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
			<link href="../css/main_new.css" rel="stylesheet" type="text/css">
			 <script type="text/javascript" src="../js/divselect.js"></script>           
			<script type="text/javascript" src="../js/calendar/calendar.js"></script>
			<script type="text/javascript" src="../js/calendar/lang/en.js"></script>
			<link rel="stylesheet" type="text/css" href="../js/calendar/jscal2.css"/>
			<link rel="stylesheet" type="text/css" href="../js/calendar/border-radius.css"/>
			<link rel="stylesheet" type="text/css" href="../js/calendar/win2k.css"/> 
			 
				<script type="text/javascript">
	function showOrderDetail(mainOrderId) {
		var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/lists!listOrderdetail.do?mainOrderId=' + mainOrderId,
				700, 600);
	}
	function showOrder(mainOrderId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/mainOrderShowModal.do?mainOrderId='
						+ mainOrderId , 700, 600);
		}
</script>
				<script type="text/javascript">
	function m_over(tr) {
		tr.className = tr.className + " " + "trmo";
	}
	function m_out(tr) {
		var cn = tr.className.replace(/\s+trmo/, '');
		tr.className = cn;
	}
</script>
				<style type="text/css">
.trmo {
	background-color: #5F9F9F !important;
}
</style>
	</head>
	<body style="min-width:700px;">
		<%@ include file="../common/incBanner.jsp"%>
		<!-- 
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0" action="lists" namespace="/order"
				method="post">
				<table>
					<tr>
						<%-- 
						<td>&nbsp;订单流水号:&nbsp;</td>
						<td><input type="text" name="mainOrderId" size="10"	value="${param.mainOrderId}" /></td>
					    --%>
						<td>
							请选择日期:&nbsp;
						</td>
						<td>
							&nbsp;
							<input id="d5221" name="startDate" class="Wdate" type="text"
								onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})" />
							至
							<input id="d5222" name="endDate" class="Wdate" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})" />
							&nbsp;
						</td>
						<td>
							冲正订单日期选择:&nbsp;
						</td>
						<td>
							&nbsp;
							<input id="d52211" name="start2Date" class="Wdate" type="text"
								onFocus="var d52222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},maxDate:'#F{$dp.$D(\'d52222\')}'})" />
							至
							<input id="d52222" name="end2Date" class="Wdate" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})" />
							&nbsp;
						</td>
						<c:if test="${sessionScope.user.usergroup.id<=2}">
							<td>
								&nbsp;
								<s:select name="supplyId" list="supplyList" listKey="id"
									listValue="supplyName"></s:select>
								&nbsp;
							</td>
						</c:if>
					</tr>
				</table>
				<table>
					<tr>
						<%--
					    <td>
							&nbsp;经销商:&nbsp;
						</td>
						<td>
							<input type="text" name="username" size="10"
								value="${param.username}" />
						</td>
		 
						<td>
							&nbsp;终端机号:&nbsp;
						</td>
						<td>
							<input type="text" name="terminalNo" size="10"
								value="${param.terminalNo}" />
						</td>
						<td>
							&nbsp;
							<s:select name="sellType"
								list="%{#{0:'===选择销售类型===',1:'空中充值',2:'卡密'}}" listKey="key"></s:select>
							&nbsp;
						</td>
					     --%>
						<td>
							&nbsp;
							<s:select name="goodsId" list="goodsList" listKey="goodsId"
								listValue="goodsName"></s:select>
							&nbsp;
						</td>
						<td>
							&nbsp;
							<s:select name="goodsTypeId" list="goodsTypeList"
								listKey="goodsTypeId" listValue="goodsTypeName"></s:select>
							&nbsp;
						</td>
						<td>
							&nbsp;
							<s:select name="state"
								list="%{#{-2:'===选择订单状态===',-1:'已冲正',0:'申请处理',1:'处理成功',2:'处理失败',3:'处理中',253:'申请取消',254:'取消中',255:'已取消'}}"
								listKey="key"></s:select>
							&nbsp;
						</td>
						<td>
							&nbsp;
							<s:select name="reversalState"
								list="%{#{-3:'===选择冲正状态===',-1:'未冲正',-2:'申请冲正',0:'冲正中',1:'冲正成功',2:'冲正失败'}}"
								listKey="key"></s:select>
							&nbsp;
						</td>
						<td>
							&nbsp;手机号:&nbsp;
						</td>
						<td>
							<input type="text" name="mobile" size="10"
								value="${param.mobile}" />
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
		-->
	 <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
          
		<div class="tqyj fixed" >
                    <s:form id="form0" name="form0" action="list!listBatchOrder" namespace="/order" method="post">
                     <div class="list" style="height: 38px;">
                              <div class="cxjjtable3" style="width:930px;">
                                     <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                       <tr>
                                                  <td>流水号：<input type="text" name="batchOrderId" value="${batchOrderId }" style="width:230px;"/> </td>
                                                  <td  align="center"  >经销商名：
                                                       <input name="username"  value="${param.username}" type="text" class="khmm" /></td>
                                                        <td width="380" align="center" valign="middle" colspan="3">  请选择日期：
													<input id="d5221" name="startDate" class="date1" type="text" style="margin-bottom: 3px;"
														onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-{%M-2}-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})" />
													至
													<input id="d5222" name="endDate" class="date2" type="text"style="margin-bottom: 3px;"
														onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})" />
                                                <%--
												<button  type="submit"  class="sousuo1"></button> 
                                                 --%>
												</td>
                                                <td>
													<button  type="submit"  class="sousuo1" style="vertical-align: middle;height: 30px;"></button> 
												</td>
                                          </tr>
                                     </table>
                              </div>
                     </div>
                     </s:form>
                      <p class="zhuyi_p order_zhuyi_p"  >订单成功与否以  订单查询 的最终状态为准，处理中的订单切勿退款或补充，如有疑问请联系我司客服QQ或致电:4000307517查询。</p>
			<div class="modify" style="margin-top:0px;">
				<display:table export="true" id="idListTb" name="pageView"
					style="min-width:600px;" sort="external" pagesize="${pageSize}"
					cellspacing="1" class="list_tb_002" 
					requestURI="${pageContext.request.contextPath}/order/list!listBatchOrder.do"
					decorator="com.sys.volunteer.common.OverOutWrapperUtils">
					<display:setProperty name="paging.banner.placement" value="bottom" />
					
				    <display:column title="商户名称" property="userName" />
					<display:column property="createTime" title="充值日期"
						format="{0,date,yy-MM-dd HH:mm}" />
					<display:column title="订单状态">
						<c:choose>
							<c:when test="${idListTb.batchOrderState==0}">待确认</c:when>
							<c:when test="${idListTb.batchOrderState==1}">未开始</c:when>
							<c:when test="${idListTb.batchOrderState==2}">进行中</c:when>
							<c:when test="${idListTb.batchOrderState==3}">暂停</c:when>
							<c:when test="${idListTb.batchOrderState==4}">停止</c:when>
							<c:when test="${idListTb.batchOrderState==255}">已完成</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose>
					</display:column>
						
					<display:column title="消费金额" property="totalMoney" />
					
					
					<display:column title="流水号" media="html">
                             <a href="/MPRS/order/list!viewBatchOrder.do?batchOrderId=${idListTb.batchOrderId }" >${idListTb.batchOrderId }</a>
					</display:column>
					<display:column title="流水号" media="excel">
					${idListTb.batchOrderId }
					</display:column>
					
					<display:setProperty name="export.excel.filename" value="times.xls"/>
				</display:table>
				</div>
		</form>
			</div>
			</div>
          </div>
		<br />
		<br />
		<script type="text/javascript">
	displaytagExportLink();
</script> <a href="user!add.html"></a> <%@ include
			file="../common/incFooter.jsp"%>
	</body>
</html>
