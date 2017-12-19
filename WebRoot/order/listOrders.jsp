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
		   <c:choose>
               <c:when test="${sessionScope.user.usergroup.groupType<=2}">
                    <s:form id="form0" name="form0" action="lists" namespace="/order" method="post">
                     <div class="cxjj fixed">
                              <div class="rechargey cxjjtable">
                                     <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                          <tr>
                                            <td width="20%">
                                                
                                                        <div id="divselect8" class="divselect3 divselect" >
                                                              <cite>请选择商品</cite>
                                                                
                                                                   <ul style="height: 200px; overflow: scroll;">
                                                                    <s:iterator value="goodsList" id="goodsList">   
																        <li > <a href="javascript:;" selectid="<s:property value='goodsId'/> "> <s:property value="goodsName"/> </a>
																         </li>
																       </s:iterator>
                                                                    </ul>
                                                      </div>
                                                       <input name="goodsId" type="hidden" value="" id="inputselect"/>
                                                
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect8","#inputselect");
													});
													</script>
                                            </td>
                                            <td width="20%">
                                               
                                                        <div id="divselect9" class="divselect3 divselect">
                                                              <cite>请选择商品类型</cite>
                                                                   <ul style="height: 200px; overflow: scroll;">
                                                                    <s:iterator value="goodsTypeList" id="goodsTypeList">   
																        <li > <a href="javascript:;" selectid="<s:property value='goodsTypeId'/> "> <s:property value="goodsTypeName"/> </a>
																         </li>
																       </s:iterator>
                                                                    </ul>
                                                      </div>
                                                       <input name="goodsTypeId" type="hidden" value="" id="inputselect2"/>
                                               
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect9","#inputselect2");
													});
													</script>
                                            </td>
                                            <td width="20%">
                                                        <div id="divselect10" class="divselect3 divselect">
                                                              <cite>选择订单状态</cite>
                                                              <ul style="height: 200px; overflow: scroll;">
                                                                 <li><a href="javascript:;" selectid="-2">选择订单状态</a></li>
                                                                 <li><a href="javascript:;" selectid="-1">已冲正</a></li>
                                                                 <li><a href="javascript:;" selectid="0">申请处理</a></li>
                                                                 <li><a href="javascript:;" selectid="1">处理成功</a></li>
                                                                 <li><a href="javascript:;" selectid="2">处理失败</a></li>
                                                                 <li><a href="javascript:;" selectid="3">处理中</a></li>
                                                                 <li><a href="javascript:;" selectid="4">已完成</a></li>
                                                                 <li><a href="javascript:;" selectid="253">申请取消</a></li>
                                                                 <li><a href="javascript:;" selectid="254">取消中</a></li>
                                                                 <li><a href="javascript:;" selectid="255">已取消</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="state" type="hidden" value="" id="inputselect3"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect10","#inputselect3");
													});
													</script>
                                            </td>
                                            <td width="20%">
                                                        <div id="divselect123" class="divselect3 divselect">
                                                              <cite>选择冲正状态</cite>
                                                              <ul style="height: 200px; overflow: scroll;">
                                                                 <li><a href="javascript:;" selectid="-3">选择冲正状态</a></li>
                                                                 <li><a href="javascript:;" selectid="-1">未冲正</a></li>
                                                                 <li><a href="javascript:;" selectid="-2">申请冲正</a></li>
                                                                 <li><a href="javascript:;" selectid="0">冲正中</a></li>
                                                                 <li><a href="javascript:;" selectid="1">冲正成功</a></li>
                                                                 <li><a href="javascript:;" selectid="2">冲正失败</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="reversalState" type="hidden" value="" id="inputselect4"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect123","#inputselect4");
													});
													</script>
                                            </td>
                                          </tr>
                                    </table>
                              </div>
                            
                            <p class="jjcx">话费订单查询列表</p>
                     </div>
                     <div class="list">
                              <div class="cxjjtable3">
                                     <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                          <tr>
                                          <!-- 
                                                <td width="7%" align="right">订单流水号：</td>
                                                <td width="7%"><input name="" type="text" class="khmm" /></td>
                                           -->
                                                <td width="7%" align="center">请选择日期：</td>
                                                <td width="15%">&nbsp;
							<input id="d5221" name="startDate" class="date1" type="text"
								onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-%M-{%d-10} %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})" />
							至
							<input id="d5222" name="endDate" class="date2" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})" />
							&nbsp;</td>
                                                <td width="7%" align="center">冲正单日选择</td>
                                                <td width="15%">
                                                <input id="d52211" name="start2Date" class="date1" type="text"
								onFocus="var d52222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},minDate:'%y-%M-{%d-10} %H:%m:%s',maxDate:'#F{$dp.$D(\'d52222\')}'})" />
							至
							<input id="d52222" name="end2Date" class="date2" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})" />
							&nbsp;
						</td>
						<c:if test="${sessionScope.user.usergroup.groupType<=2}">
							<td>
								&nbsp;
								<s:select name="supplyId" list="supplyList" listKey="id"
									listValue="supplyName"></s:select>
								&nbsp;
							</td>
						</c:if>
                                               <!-- 
                                                <td width="6%" align="right">经销商：</td>
                                                <td width="6%"><input name="" type="text" class="sjyz" /></td>
                                                <td width="6%" align="right">终端机号：</td>
                                                <td width="6%"><input name="" type="text" class="sjyz" /></td>
                                                -->
                                                <td width="6%" align="right">手机号：</td>
                                                <td width="6%"><input name="mobile" type="text" value="${param.mobile}" class="sjyz" /></td>
                                                <td width="6%" align="right"><button  type="submit"  class="sousuo1"></button> </td>
                                          </tr>
                                     </table>
                              </div>
                     </div>
                     </s:form>
               </c:when>
               <c:when test="${sessionScope.user.usergroup.groupType>2}">
                     <s:form id="form0" name="form0" action="lists" namespace="/order" method="post">
                     <div class="cxjj fixed">
                              <div class="rechargey cxjjtable" style="width:700px;">
                                     <table width="700" height="36" border="0"  cellpadding="0" cellspacing="0">
                                       <c:if test="${sessionScope.user.usergroup.groupType>=4}">
                                       <tr>
                                            <td width="20%">
                                               
                                                        <div id="divselect9" class="divselect3 divselect">
                                                              <cite>请选择商品类型</cite>
                                                                   <ul style="height: 200px; overflow: scroll;">
                                                                    <s:iterator value="goodsTypeList" id="goodsTypeList">   
																        <li > <a href="javascript:;" selectid="<s:property value='goodsTypeId'/>"> <s:property value="goodsTypeName"/> </a>
																         </li>
																       </s:iterator>
                                                                    </ul>
                                                      </div>
                                                       <input name="goodsTypeId" type="hidden" value="" id="inputselect3"/>
                                               
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect9","#inputselect3");
													});
													</script>
                                            </td>
                                       </tr>
                                        <tr>
                                        	
                                            <td  colspan="2">
		                                               <div id="divselect10" class="divselect3 divselect">
		                                                              <cite>选择订单状态</cite>
		                                                              <ul style="height: 200px; overflow: scroll;">
		                                                                 <li><a href="javascript:;" selectid="-2">选择订单状态</a></li>
		                                                                 <li><a href="javascript:;" selectid="-1">已冲正</a></li>
		                                                                 <li><a href="javascript:;" selectid="0">申请处理</a></li>
		                                                                 <li><a href="javascript:;" selectid="1">处理成功</a></li>
		                                                                 <li><a href="javascript:;" selectid="2">处理失败</a></li>
		                                                                 <li><a href="javascript:;" selectid="3">处理中</a></li>
		                                                                 <li><a href="javascript:;" selectid="4">已完成</a></li>
		                                                                 <li><a href="javascript:;" selectid="253">申请取消</a></li>
		                                                                 <li><a href="javascript:;" selectid="254">取消中</a></li>
		                                                                 <li><a href="javascript:;" selectid="255">已取消</a></li>
		                                                              </ul>
		                                                      </div>
		                                                       <input name="state" type="hidden" value="" id="inputselect2"/>
		                                                <script type="text/javascript">
															$(function(){
																$.divselect("#divselect10","#inputselect2");
															});
															</script>
													</td>
                                           <c:if test="${sessionScope.user.usergroup.groupType==4}">
													   <td  align="center">经销商名：
                                                       <input name="username"  value="${param.username}" type="text" class="khmm" /></td>
                                                   </c:if>    
												    <td>
												     <div id="divselect12" class="divselect3 divselect">
                                                              <cite>选择冲正状态</cite>
                                                              <ul style="height: 200px; overflow: scroll;">
                                                                 <li><a href="javascript:;" selectid="-3">选择冲正状态</a></li>
                                                                 <li><a href="javascript:;" selectid="-1">未冲正</a></li>
                                                                 <li><a href="javascript:;" selectid="-2">申请冲正</a></li>
                                                                 <li><a href="javascript:;" selectid="0">冲正中</a></li>
                                                                 <li><a href="javascript:;" selectid="1">冲正成功</a></li>
                                                                 <li><a href="javascript:;" selectid="2">冲正失败</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="reversalState" type="hidden" value="" id="inputselect1"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect12","#inputselect1");
													});
													</script>
												    </td>
													
												</tr>
                                           </c:if>
                                               <tr>
                                                <td width="80" align="right" valign="middle">手机号码：</td>
                                                <td width="110" valign="middle">
                                                <input name="mobile" style="height: 30px;" type="text" value="${param.mobile}" class="khmm" />
                                                </td>
                                                <td width="380"  valign="middle">  请选择日期：
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
                              <!-- 
                               -->
                                <c:if test="${sessionScope.user.usergroup.groupType==4}">
                            <p class="jjcx" style="line-height: 60px;">话费订单查询列表</p>
                                </c:if>
                                <c:if test="${sessionScope.user.usergroup.groupType!=4}">
                            <p class="jjcx" >话费订单查询列表</p>
                                </c:if>
                              
                     </div>
                     </s:form>
               </c:when>
           </c:choose>    
                      <p class="zhuyi_p order_zhuyi_p"  >订单成功与否以  订单查询 的最终状态为准，处理中的订单切勿退款或补充，如有疑问请联系我司客服QQ或致电:4000307517查询。</p>
			<div class="modify" style="margin-top:0px;">
				<display:table export="true" id="idListTb" name="pageView"
					style="min-width:600px;" sort="external" pagesize="${pageSize}"
					cellspacing="1" class="list_tb_002" 
					requestURI="${pageContext.request.contextPath}/order/lists.do"
					decorator="com.sys.volunteer.common.OverOutWrapperUtils">
					<display:setProperty name="paging.banner.placement" value="bottom" />
				 <c:if test="${sessionScope.user.usergroup.groupType==4}">	
				    <display:column title="商户名称" property="userName" />
				 </c:if>
					<display:column title="充值号码" property="mobile" />
					<display:column title="备注" property="commentsOn"></display:column>
					<display:column property="createTime" title="充值日期"
						format="{0,date,yy-MM-dd HH:mm}" />
					<display:column title="号码余额"  >
						<fmt:formatNumber maxFractionDigits="4" value="${idListTb.cBalance }"/>
					</display:column>
					
				 <c:if test="${sessionScope.user.usergroup.groupType!=4}">	
					<display:column title="订单操作" media="html">
						<c:choose>
							<c:when
								test="${idListTb.state==1&&idListTb.reversalState==-1&&idListTb.orderType==1&&
							(sessionScope.user.usergroup.groupType<=2||
								(sessionScope.user.usergroup.groupType>=4&&idListTb.canReverse==1))}">
								<a href="/MPRS/order/reversalOrders.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要订单冲正吗？')">订单冲正</a>
							</c:when>
							<c:when
								test="${(idListTb.state==3||idListTb.state==0||idListTb.state==5)&&idListTb.orderType==1&&sessionScope.user.usergroup.groupType<=2}">
								<a href="/MPRS/order/cancelOrderExe.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要取消订单吗？')">取消订单</a>
								<a
									href="/MPRS/order/cancelOrderNoReturnExe.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要取消订单(不反额度)吗？')">取消订单(不反额度)</a>
								<br/>
								<a href="/MPRS/order/dealOrders.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要强制订单成功吗？')">强制订单成功</a>
								<a href="/MPRS/order/dealOrdersNoReturn.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要强制订单成功(不扣额度)吗？')">强制订单成功(不扣额度)</a>
							<br/> 
							</c:when>
							<c:when
								test="${(idListTb.state==2||idListTb.state==255)&&idListTb.orderType==1&&sessionScope.user.usergroup.groupType<=2}">
								<br/>
								<a href="/MPRS/order/dealOrders.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要强制订单成功吗？')">强制订单成功</a>
								<a href="/MPRS/order/dealOrdersNoReturn.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要强制订单成功(不扣额度)吗？')">强制订单成功(不扣额度)</a>
							<br/>
							</c:when>
							<c:when
								test="${(idListTb.reversalState==0||idListTb.reversalState==-2||idListTb.reversalState==5)&&idListTb.orderType==1&&sessionScope.user.usergroup.groupType<=2}">
								<br/>
								<a href="/MPRS/order/reverseOrderSuccess.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要强制订单冲正成功吗？')">强制订单冲正成功</a>
								<a href="/MPRS/order/reverseOrderFailed.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' ${idListTb.mobile} : 您确定要强制订单冲正失败吗？')">强制订单冲正失败</a>
							<br/>
							</c:when>
						</c:choose>
						<c:if
							test="${idListTb.state==2&&idListTb.isNeedManual==1&&idListTb.isTerminal==1&&sessionScope.user.usergroup.groupType<=2}">
							<a href="/MPRS/order/page!manualOrder.do?orderId=${idListTb.mainOrderId }"
								onclick="return window.confirm(' ${idListTb.mobile} : 您确定要人工充值吗？')">人工充值</a>
						</c:if>
					</display:column>
					</c:if>	
					<display:column title="商品名称" property="goodsName" />
					<display:column title="订单状态">
						<c:choose>
							<c:when test="${idListTb.state==0}">申请处理</c:when>
							<c:when test="${idListTb.state==1}">处理成功</c:when>
							<c:when test="${idListTb.state==2}">处理失败</c:when>
							<c:when test="${idListTb.state==3}">处理中</c:when>
							<c:when test="${idListTb.state==4}">已完成</c:when>
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
					<display:column property="reversalTime" title="冲正日期" media="excel"
						format="{0,date,yy-MM-dd HH:mm}" />
					<display:column title="消费金额" property="goodsValue" />
					<display:column title="佣金" media="excel" >
						<fmt:formatNumber maxFractionDigits="4"
							value="${idListTb.commission}" />
					</display:column>
					<c:if test="${sessionScope.user.isShowSupply==1}">
						<display:column title="供货商" property="supplyName"/>
					</c:if>
					<c:choose>
						<c:when test="${sessionScope.user.usergroup.groupType<=2}">
							<display:column title="进货价" media="excel">
								<fmt:formatNumber maxFractionDigits="4"
									value="${idListTb.stockPrice}" />
							</display:column>
							<display:column title="销售价" media="excel">
								<fmt:formatNumber maxFractionDigits="4"
									value="${idListTb.retailPrice}" />
							</display:column>
						</c:when>
						<c:otherwise>
							<display:column title="进货价" media="excel">
								<fmt:formatNumber maxFractionDigits="4"
									value="${idListTb.retailPrice }" />
							</display:column>
						</c:otherwise>
					</c:choose>
					
					<display:column title="经销商" property="userName" media="excel"/>
					<display:column title="终端机号" property="terminalNo" media="excel"/>
					<display:column title="购买数量" property="goodsNo"/>
					
					
					<display:column title="流水号" media="html">
						<c:choose>
							<c:when test="${sessionScope.user.usergroup.groupType<=2}">
								<a href="javascript:void(0);"
									onclick="showOrderDetail('${idListTb.mainOrderId}')">${idListTb.mainOrderId}</a>
							</c:when>
							<c:otherwise>
                                  <a href="javascript:void(0);"
									onclick="showOrder('${idListTb.mainOrderId}')">${idListTb.mainOrderId }</a>
                           </c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="流水号" media="excel">
					${idListTb.mainOrderId }
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
