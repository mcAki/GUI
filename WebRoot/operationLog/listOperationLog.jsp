<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="../js/divselect.js"></script>  
	<script type="text/javascript">
		function showOrder(mainOrderId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/mainOrderShowModal.do?mainOrderId='
						+ mainOrderId , 700, 600);
		}
	function m_over(tr) {
		tr.className = tr.className + " " + "trmo";
	}
	function m_out(tr) {
		var cn = tr.className.replace(/\s+trmo/, '');
		tr.className = cn;
	}
	</script>
	</head>
	<body style="min-width: 680px;">
		<%@ include file="../common/incBanner.jsp"%>
		<!-- 
		
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list" namespace="/operationLog"
			method="post">
				<table>
					<tr>
						<td>&nbsp;操作类型:&nbsp;</td>
						<td><s:select name="opType" list="%{#{0:'请选择类型', 1:'充值 ',2:'购买卡密', 3:'冲正',4:'强制订单成功',255:'取消订单'}}" listKey="key"></s:select></td>
						<c:if test="${sessionScope.user.usergroup.id<=2}">
							<td>&nbsp;<s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select>&nbsp;</td>
						</c:if>
						<td>&nbsp;<s:select name="goodsId" list="goodsList" listKey="goodsId" listValue="goodsName"></s:select>&nbsp;</td>
						<td>请选择日期:&nbsp;</td>
						<td>&nbsp;<input id="d5221" name="startDate" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
									至
								<input id="d5222" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>&nbsp;</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>&nbsp;订单流水号:&nbsp;</td>
						<td><input type="text" name="mainOrderId" size="10" value="${param.mainOrderId}"/></td>
						<td>&nbsp;经销商:&nbsp;</td>
						<td><input type="text" name="username" size="10" value="${param.username}"/></td>
						<td>&nbsp;终端机号:&nbsp;</td>
						<td><input type="text" name="terminalNo" size="10" value="${param.terminalNo}"/></td>
						<td>&nbsp;手机号:&nbsp;</td>
						<td><input type="text" name="mobile" size="10" value="${param.mobile}"/></td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				</s:form>
		</div>
		<br/>
		 -->
		 <div id="comInfo" class="fixed">
		    <div id="contain" class="fixed">
            <s:include value="../system/head_div.jsp" ></s:include>
             <div class="tqyj fixed">
                        <c:choose>
                            <c:when test="${sessionScope.user.usergroup.groupType<=2}">
                                 <div class="cxjj fixed">
			                          <div class="rechargey cxjjtable" style="width: 770px;float:left;">
			                             <s:form id="form0" name="form0" action="list" namespace="/operationLog" method="post">
											<table width="100%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
													       <div id="divselect10" class="divselect3 divselect">
			                                                              <cite>请选择类型</cite>
			                                                               <ul >
			                                                                 <li><a href="javascript:;" selectid="0">请选择类型</a></li>
			                                                                 <li><a href="javascript:;" selectid="1">充值</a></li>
			                                                                 <li><a href="javascript:;" selectid="2">购买卡密</a></li>
			                                                                 <li><a href="javascript:;" selectid="3">冲正</a></li>
			                                                                 <li><a href="javascript:;" selectid="4">强制订单成功</a></li>
			                                                                 <li><a href="javascript:;" selectid="255">取消订单</a></li>
			                                                              </ul>
			                                               </div>
			                                                      <input name="opType" type="hidden" value="" id="inputselect"/>
				                                                <script type="text/javascript">
																	$(function(){
																		$.divselect("#divselect10","#inputselect");
																	});
																</script>
													</td>
													<c:if test="${sessionScope.user.usergroup.groupType<=2}">
										<td>&nbsp;<s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select>&nbsp;</td>
									</c:if>
													<td>
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
													<td>请选择日期:&nbsp;</td>
													<td>&nbsp;<input id="d5221" name="startDate" class="date1" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-%M-{%d-10} %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
																至
															<input id="d5222" name="endDate" class="date2" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>&nbsp;</td>
												</tr>
											</table>
											</div>
								<%--
								 <p class="jjcx">详细记录</p>
								 --%>
							</div>
					 <div class="list">
                           <div class="cxjjtable3">	
								<table style="min-width:770px;" border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td>&nbsp;流水号:&nbsp;</td>
										<td><input type="text" name="mainOrderId" size="10" value="${param.mainOrderId}" class="sjyz" /></td>
										<td>&nbsp;经销商:&nbsp;</td>
										<td><input type="text" name="username" size="10" value="${param.username}" class="sjyz" /></td>
										<td>&nbsp;终端机号:&nbsp;</td>
										<td><input type="text" name="terminalNo" size="10" value="${param.terminalNo}" class="sjyz" /></td>
										<td>&nbsp;手机号:&nbsp;</td>
										<td><input type="text" name="mobile" size="10" value="${param.mobile}" class="sjyz" /></td>
										<td>&nbsp;<button   type="submit" value="搜索" class="sousuo1"></button> </td>
									</tr>
								</table>
								</s:form>
                          </div>
                          
                      </div>     
                            </c:when>
                            <c:when test="${sessionScope.user.usergroup.groupType>2}">
                                  <div class="cxjj fixed">
			                          <div class="rechargey cxjjtable" style="position:absolute;">
			                             <s:form id="form0" name="form0" action="list" namespace="/operationLog" method="post">
											<table width="650" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="80">&nbsp;手机号码:&nbsp;</td>
													<td width="110"><input type="text" name="mobile" size="10" value="${param.mobile}" class="khmm" /></td>
													<td width="400">请选择日期:
													&nbsp;<input id="d5221" name="startDate" class="date1" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-{%M-2}-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
																至
															<input id="d5222" name="endDate" class="date2" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>&nbsp;
											
													</td>
													<td>
													<button   type="submit" value="搜索" class="sousuo1"></button> 
													</td>
												</tr>
												
											</table>
										</s:form>
                              	 </div>
								 <p class="jjcx">详细记录</p>
							</div>
                              
                            </c:when>
                        </c:choose>
                    
		<div class="modify">
			<display:table export="true" id="idListTb" name="pageView" style="width:100%;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb_002" decorator="com.sys.volunteer.common.OverOutWrapperUtils"
				requestURI="${pageContext.request.contextPath}/operationLog/list.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				
				<c:if test="${sessionScope.user.usergroup.groupType<=2}">
					<display:column title="供货商" property="supplyName"/>
				</c:if>
				<c:if test="${sessionScope.user.usergroup.groupType!=5}">
				  <display:column title="经销商" property="userName"/>
				</c:if>
				<display:column title="充值凭证" media="html">
					<c:choose>
						<c:when test="${sessionScope.user.usergroup.groupType<=2}">
							<a href="javascript:void(0);" onclick="showOrder('${idListTb.mainorder.mainOrderId }')">${idListTb.mainorder.mainOrderId }</a>
						</c:when>
						<c:otherwise><a href="javascript:void(0);" onclick="showOrder('${idListTb.mainorder.mainOrderId }')">${idListTb.mainorder.mainOrderId }</a></c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="订单流水号" media="excel">
						${idListTb.mainorder.mainOrderId }
				</display:column>
				<display:column title="充值手机" property="mobileNum"/>
				<display:column property="createTime" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="交易金额" >
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.subtotal }"/>
				</display:column>
				<display:column title="交易数量" property="amount"/>
				<display:column title="类型" >
					<c:choose>
						<c:when test="${idListTb.type==1}">空中充值</c:when>
						<c:when test="${idListTb.type==2}">购买卡密</c:when>
						<c:when test="${idListTb.type==3}">冲正</c:when>
						<c:when test="${idListTb.type==4}">强制订单成功</c:when>
						<c:when test="${idListTb.type==255}">取消订单</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="结果" >
					<c:choose>
						<c:when test="${idListTb.result==1}">成功</c:when>
						<c:when test="${idListTb.result==2}">失败</c:when>
						<c:otherwise>处理中</c:otherwise>
					</c:choose>
				</display:column>
				<display:setProperty name="export.excel.filename" value="times.xls"/>
			</display:table>
		</div>
		
   </div>
   </div>
 </div>
 <script type="text/javascript">
	displaytagExportLink();
</script>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
