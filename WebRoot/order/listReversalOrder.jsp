<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
				<%@ include file="../common/incHead.jsp"%>
					<%@ include file="../common/incBanner.jsp"%>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
			<link href="../css/main_new.css" rel="stylesheet" type="text/css">
			<script type="text/javascript" src="../js/divselect.js"></script>  
				<script type="text/javascript">
	function showOrderDetail(mainOrderId) {
		var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/list!listOrderdetail.do?mainOrderId=' + mainOrderId,
				700, 600);
	}
</script>
 <script type="text/javascript">   
   function m_over(tr){   
       tr.className=tr.className + " " + "trmo";   
   }   
   function m_out(tr){   
       var cn = tr.className.replace(/\s+trmo/,'');   
       tr.className = cn;   
   }   
</script>   
<style type="text/css">   
    .trmo{   
        background-color:#5F9F9F !important;   
    }   
</style>   

	</head>
	<body style="width: 1650px;">
	
		<%--
		 
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0" action="list" namespace="/order"
				method="post">
				<table>
					<tr>
						<td>&nbsp;订单流水号:&nbsp;</td>
						<td><input type="text" name="mainOrderId" size="10"	value="${param.mainOrderId}" /></td>
						<td>请选择日期:&nbsp;</td>
						<td>&nbsp;
							<input id="d5221" name="startDate" class="Wdate" type="text"
								onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})" />
							至
							<input id="d5222" name="endDate" class="Wdate" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})" />
							&nbsp;
						</td>
						<c:if test="${sessionScope.user.usergroup.id==1}">
						
						<td>冲正订单日期选择:&nbsp;</td>
						<td>&nbsp;
							<input id="d52211" name="start2Date" class="Wdate" type="text"
								onFocus="var d52222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},maxDate:'#F{$dp.$D(\'d52222\')}'})" />
							至
							<input id="d52222" name="end2Date" class="Wdate" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})" />
							&nbsp;
						</td>
						
						<c:if test="${sessionScope.user.usergroup.id<=2}">
							<td>&nbsp;<s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select>
								&nbsp;
							</td>
						</c:if>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							&nbsp;
							<s:select name="goodsId" list="goodsList" listKey="goodsId"
								listValue="goodsName"></s:select>
							&nbsp;
						</td>
						<td>
							&nbsp;
							<s:select name="goodsTypeId" list="goodsTypeList" listKey="goodsTypeId"
								listValue="goodsTypeName"></s:select>
							&nbsp;
						</td>
						<td>
							&nbsp;
							<s:select name="sellType"
								list="%{#{0:'===选择销售类型===',1:'空中充值',2:'卡密'}}" listKey="key"></s:select>
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
						 </c:if>
						<td>
							&nbsp;手机号:&nbsp;
						</td>
						<td>
							<input type="text" name="mobile" size="10"
								value="${param.mobile}" />
						</td>
						<c:if test="${sessionScope.user.usergroup.id==1}">
					</tr>
				</table>
		    <div class="cxjjtable3" style="width: 90%">
				 <p>
				    <table>
				       <tr>
				          <td>人工补充: </td>
				          <td>
				               <div id="divselect8" class="divselect3 divselect">
                                                              <cite>选择人工补充</cite>
                                                              <ul >
                                                                 <li><a href="javascript:;" selectid="1">不需要</a></li>
                                                                 <li><a href="javascript:;" selectid="2">需要</a></li>
                                                                 <li><a href="javascript:;" selectid="255">已卡充</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="isNeedManual" type="hidden" value="" id="inputselect"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect8","#inputselect");
													});
													</script>
				          </td>
				          <td>&nbsp;&nbsp;&nbsp; 补充人:</td>
				          <td><input name="manualUserName" type="text" class="sjyz"/></td>
				          <td>     是否直充:  </td>
				          <td>
                              <div id="divselect10" class="divselect3 divselect">
                                                              <cite>选择是否直充</cite>
                                                              <ul >
                                                                 <li><a href="javascript:;" selectid="1">是</a></li>
                                                                 <li><a href="javascript:;" selectid="0">否</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="orderType" type="hidden" value="" id="inputselect"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect10","#inputselect");
													});
													</script>
                          </td>
                          <!-- 
                          <td>
                                  &nbsp;&nbsp;&nbsp;上级商户：
                          </td>
                          <td>
                               <div id="divselect11" class="divselect3 divselect">
                                                              <cite>选择用户组</cite>
                                                              <ul >
                                                                 <li><a href="javascript:;" selectid="1">超级管理员</a></li>
                                                                 <li><a href="javascript:;" selectid="2">员工</a></li>
                                                                 <li><a href="javascript:;" selectid="3">查询</a></li>
                                                                 <li><a href="javascript:;" selectid="4">一级商户</a></li>
                                                                 <li><a href="javascript:;" selectid="5">二级商户</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="groupIdSelect" type="hidden" value="" id="inputselect"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect11","#inputselect");
													});
													</script>
                          </td>
                           -->
                          <td>
                                  &nbsp;&nbsp;&nbsp;是否终端：
                          </td>
                          <td>
                               <div id="divselect11" class="divselect3 divselect">
                                                              <cite>选择是否终端</cite>
                                                              <ul >
                                                                 <li><a href="javascript:;" selectid="1">是</a></li>
                                                                 <li><a href="javascript:;" selectid="0">否</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="isTerminal" type="hidden" value="" id="inputselect"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect11","#inputselect");
													});
													</script>
							</td>		
							 </c:if>			
				         <td>
				         	&nbsp;
					       <input icon="icon-search" type="submit" value="搜索" />
				         </td>
				       </tr>
				    </table>
				    </p>
				</s:form>
		    </div>	
		</div>
		<center >
		--%>
		 
	 <div id="comInfo" class="fixed" >
	 <center>
          <display:table export="true" id="idListTb"
			name="list" style="width:1600px;" sort="external"
			pagesize="${pageSize}" cellspacing="1" class="list_tb_002"
			requestURI="${pageContext.request.contextPath}/order/list!listReversalOrder.do" decorator="com.sys.volunteer.common.OverOutWrapperUtils">
			<display:setProperty name="paging.banner.placement" value="bottom" />
			
			<display:column title="号码" property="mobile" />
			<display:column property="createTime" title="销售日期"
				format="{0,date,yy-MM-dd HH:mm}" />
			<display:column title="操作" media="html">
				<c:choose>
					<c:when
						test="${idListTb.state==1&&(idListTb.reversalState==2||idListTb.reversalState==-1)
							&&idListTb.orderType==1&&
							(sessionScope.user.usergroup.groupType<=2||
								(sessionScope.user.usergroup.groupType>=4&&idListTb.canReverse==1))}">
						<a href="reversalOrdersEx.do?orderId=${idListTb.mainOrderId }" onclick="return window.confirm(' ${idListTb.mobile} : 您确定要重新冲正订单吗？')">订单冲正</a>
					</c:when>
				</c:choose>
			</display:column>	
			<display:column title="商品名称" property="goodsName" />
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
				<display:column title="供货商" property="supplyName" />
			</c:if>
			<display:column title="订单状态">
				<c:choose>
					<c:when test="${idListTb.state==0}">申请处理</c:when>
					<c:when test="${idListTb.state==1}">处理成功</c:when>
					<c:when test="${idListTb.state==2}">处理失败</c:when>
					<c:when test="${idListTb.state==3}">处理中</c:when>
					<c:when test="${idListTb.state==5}">处理中</c:when>
					<c:when test="${idListTb.state==253}">申请取消</c:when>
					<c:when test="${idListTb.state==254}">取消中</c:when>
					<c:when test="${idListTb.state==255}">已取消</c:when>
					<c:otherwise>未知</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="冲正状态">
				<c:choose>
					<c:when test="${idListTb.reversalState==0}">冲正中</c:when>
					<c:when test="${idListTb.reversalState==1}">冲正成功</c:when>
					<c:when test="${idListTb.reversalState==2}">冲正失败</c:when>
					<c:when test="${idListTb.reversalState==-1}">未冲正</c:when>
					<c:when test="${idListTb.reversalState==-2}">申请冲正</c:when>
					<c:when test="${idListTb.reversalState==5}">冲正中</c:when>
					<c:otherwise>未知</c:otherwise>
				</c:choose>
			</display:column>
			<display:column property="reversalTime" title="冲正订单日期"
				format="{0,date,yy-MM-dd HH:mm}" />
			<display:column title="交易总金额">
				<fmt:formatNumber maxFractionDigits="4" value="${idListTb.totalMoney }"/>
			</display:column>
			<c:choose>
				<c:when test="${sessionScope.user.usergroup.groupType<=2}">
					<display:column title="进货价">
						<fmt:formatNumber maxFractionDigits="4" value="${idListTb.stockPrice }"/>
					</display:column>
					<display:column title="销售价">
						<fmt:formatNumber maxFractionDigits="4" value="${idListTb.retailPrice }"/>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column title="进货价">
						<fmt:formatNumber maxFractionDigits="4" value="${idListTb.retailPrice }"/>
					</display:column>
				</c:otherwise>
			</c:choose>
			<display:column title="交易佣金">
				<fmt:formatNumber maxFractionDigits="4" value="${idListTb.commission }"/>
			</display:column>
			<display:column title="号码余额">
				<fmt:formatNumber maxFractionDigits="4" value="${idListTb.cBalance }"/>
			</display:column>
			<display:column title="订单流水号" media="html" >
				<c:choose>
					<c:when test="${sessionScope.user.usergroup.groupType<=2}">
						<a href="javascript:void(0);"
							onclick="showOrderDetail('${idListTb.mainOrderId}')">${idListTb.mainOrderId
							}</a>
					</c:when>
					<c:otherwise>${idListTb.mainOrderId }</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="订单流水号" property="mainOrderId" media="excel" />
			
			
			<display:column title="经销商" property="userName" />
			<display:column title="终端机号" property="terminalNo" />
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="面额" property="goodsValue" />
			</c:if>
			<display:column title="购买数量" property="goodsNo" />
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="交易总金额">
				<fmt:formatNumber maxFractionDigits="4" value="${idListTb.totalMoney }"/>
			</display:column>
			</c:if>
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="是否终端操作">
				<c:choose>
					<c:when test="${idListTb.isTerminal==0}">否</c:when>
					<c:when test="${idListTb.isTerminal==1}">是</c:when>
					<c:otherwise>否</c:otherwise>
				</c:choose>
			</display:column>
			</c:if>
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="订单类型">
				<c:choose>
					<c:when test="${idListTb.orderType==1}">直充</c:when>
					<c:when test="${idListTb.orderType==2}">卡密</c:when>
					<c:otherwise>未知</c:otherwise>
				</c:choose>
			</display:column>
			</c:if>
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="人工补充">
				<c:choose>
					<c:when test="${idListTb.isNeedManual==1}">需要</c:when>
					<c:when test="${idListTb.isNeedManual==2}">不需要</c:when>
					<c:when test="${idListTb.isNeedManual==255}">已补充</c:when>
					<c:otherwise>未知</c:otherwise>
				</c:choose>
			</display:column>
			</c:if>
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="补充卡号" property="cardNo" />
			</c:if>
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="补充人" property="manualUserName" />
			</c:if>
			
			 
			</display:table>
			</center>
	</div>
		<br />
		<br />
		<a href="user!add.html"></a>
		
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
