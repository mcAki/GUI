
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<%@ page import="org.displaytag.decorator.TotalTableDecorator"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/divselect.js"></script>
		<script type="text/javascript" src="../js/calendar/calendar.js"></script>
		<script type="text/javascript" src="../js/calendar/lang/en.js"></script>
		<link rel="stylesheet" type="text/css"
			href="../js/calendar/jscal2.css" />
		<link rel="stylesheet" type="text/css"
			href="../js/calendar/border-radius.css" />
		<link rel="stylesheet" type="text/css" href="../js/calendar/win2k.css" />
		<script type="text/javascript">
	function showOrder(mainOrderId) {
		var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/mainOrderShowModal.do?mainOrderId=' + mainOrderId,
				700, 600);
	}
	$(document).ready(function() {
		
	})
	function show(tag) {
		var light = document.getElementById(tag);
		light.style.display = 'block';
	}
	function hide(tag) {
		var light = document.getElementById(tag);
		light.style.display = 'none';
	}
	
	function showSelect(type){
	   if(type==1){
		    $("#ex_id").show();
		    $("#exex_id").hide();
	   }else{
		    $("#ex_id").hide();
		    $("#exex_id").show();
	   }
	}
</script>
		<style type="text/css">
#wen_selAllExDateCountList {
	display: none;
	width: 50%;
	background: none repeat scroll 0 0 #FFFFFF;
	border: 1px solid #D5EAF8;
	padding: 0px 10px 10px 10px;
	z-index: 10000;
	overflow: scroll;
	max-height: 400px;
}

#wen_hide {
	float: right;
	cursor: pointer;
	border: 1px solid #D5EAF8;
	padding: 3px;
	margin: 3px;
	background-color: #FEE396;
}
.divselect1{
    width:114px;
}
.divselect1 cite{
    padding-left: 0px;
}
</style>
	</head>
	<jsp:scriptlet>
	 TotalTableDecorator tt = new  TotalTableDecorator();
	  tt.setSubtotalLabel("{0} 小计");  
	  tt.setTotalLabel("合计"); 
	   request.setAttribute("total",tt);
	   </jsp:scriptlet>
	<body>
		<%@ include file="../common/incBanner.jsp"%>

		<div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
					  <center>
						<div class="quota cxjjtable" style="width: 750px;">
						 
							 <form id="form0" name="form0"action="/MPRS/order/list!statisticsOrderVOList.do"  method="post">
								<table width="750">
								   <tr >
								      <c:if test="${users.usergroup.groupType==1}">
									    
									     <td id="ex_id"  >     
									     <%--
									         <s:select list="usersListEx" name="userId" listKey="userId" listValue="userName" ></s:select>
									      --%>     	    
									          <div id="divselect8" class="divselect1 divselect">
                                                          <cite>一级商户</cite>
                                                          <ul style="overflow: scroll; max-height: 200px;">
                                                            <s:iterator value="usersListEx">
	                                                             <li> <a href="javascript:;" selectid="${userId }">${userName }</a> </li>
                                                            </s:iterator>
                                                          </ul>
                                            </div>
                                                     <input name="userIdEx" type="hidden" value="" id="inputselect2"/>
                                               
                                                    <script type="text/javascript">
													$(function(){
														$.divselect("#divselect8","#inputselect2");
													});
													</script>
									     </td>
									     
									     </c:if>
										  <c:if test="${users.usergroup.groupType<=4}">
										    <td id="exex_id"  >
											      <%--
											         <s:select list="usersListExEx" name="userId" listKey="userId" listValue="userName" ></s:select>
											       --%>
											         
											           <div id="divselect9" class="divselect1 divselect">
		                                                          <cite>二级商户</cite>
		                                                          <ul style="overflow: scroll; max-height: 200px;">
		                                                            <s:iterator value="usersListExEx">
			                                                             <li> <a href="javascript:;" selectid="${userId }">${userName }</a> </li>
		                                                            </s:iterator>
		                                                          </ul>
		                                            </div>
		                                                     <input name="userIdExEx" type="hidden" value="" id="inputselect3"/>
		                                               
		                                                    <script type="text/javascript">
															$(function(){
																$.divselect("#divselect9","#inputselect3");
															});
															</script>
											     </td>
										     </c:if>
								      <td>
								           <div id="divselect7" class="divselect1 divselect">
                                                          <cite>选择类型</cite>
                                                          <ul>
                                                             <li><a href="javascript:;" selectid="0">全国直冲</a></li>
                                                             <li><a href="javascript:;" selectid="1">QQ直冲</a></li>
                                                             <li><a href="javascript:;" selectid="2">移动直冲</a></li>
                                                             <li><a href="javascript:;" selectid="3">联通直冲</a></li>
                                                             <li><a href="javascript:;" selectid="4">电信直冲</a></li>
                                                             <li><a href="javascript:;" selectid="255">汇总</a></li>
                                                          </ul>
                                            </div>
                                                     <input name="isLocal" type="hidden" value="255" id="inputselect1"/>
                                               
                                                    <script type="text/javascript">
													$(function(){
														$.divselect("#divselect7","#inputselect1");
													});
													</script>
								      </td>
								      <td colspan="3">
								按时间查询：
								<c:if test="${users.usergroup.groupType<4}">
								<input id="d52211" name="startDate" class="date1"
									type="text"
									onFocus="var d5222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},maxDate:'#F{$dp.$D(\'d52222\')}'})" />
							   </c:if>
							   <c:if test="${users.usergroup.groupType>=4}">
								<input id="d52211" name="startDate" class="date1"
									type="text"
									onFocus="var d5222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},minDate:'%y-{%M-2}-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'d52222\')}'})" />
							    
							   </c:if>
								至
								<input id="d52222" name="endDate" class="date2" type="text"
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})" />
								      </td>
								      <td >
								<button  type="submit"  class="sousuo1"></button>
								      
								      </td>
								   </tr>
								</table>
								
							</form>
						</div>
					  </center>
						<p class="jjcx">
							二级商户销售统计
						</p>

					</div>
					<div class="modify">
						<div class="cxjjtable3">
							   <center>
							    <strong style="font-size: 16px;" id="show_type">
							      <c:choose>
							         <c:when test="${isLocal==0}"> 全国直冲 </c:when>
							         <c:when test="${isLocal==1}"> QQ直冲 </c:when>
							         <c:when test="${isLocal==2}"> 移动直冲 </c:when>
							         <c:when test="${isLocal==3}"> 联通直冲 </c:when>
							         <c:when test="${isLocal==4}"> 电信直冲 </c:when>
							         <c:when test="${isLocal==255}"> 汇总 </c:when>
							      </c:choose>
							                   
							    </strong>
								查询日期为：<font color="red">
								<s:if test="endDate!= null && startDate!=null">
					                  <%=session.getAttribute("startDate") %>至   <%=session.getAttribute("endDate") %>
					           </s:if>
								<s:else>
			                                                             今日	
		                        </s:else>
		                        </font>
							   </center>
								<display:table export="true" id="idListTb"  name="statisticsOrderVOList" 
								 style="width:100%;" decorator="org.displaytag.decorator.TotalTableDecorator"
								 requestURI="${pageContext.request.contextPath}/order/list!statisticsOrderVOList.do"
									pagesize="${pageSize}" sort="external" cellspacing="1" class="list_tb_002" >
								         <display:column property="userName" title="二级商户" />
								         <display:column property="sumAll" title="笔数" total="true" />
								         
									     <display:column property="amountAll" title="总额" total="true" >
									     <%--
									       <fmt:formatNumber maxFractionDigits="2" value="${idListTb.amountAll }"/>
									      --%>
									     </display:column>
									 <display:setProperty name="export.excel.filename" value=" ${startDate }-${endDate }.xls"/>     
								</display:table>
			
				<%--
							<table class="list_tb_002" style="width: 95%">
								<tr>
									<th>
										供货商名称
									</th>
									<th>
										当日结算合计数(/元)
									</th>
								</tr>
								<s:iterator value="#session.selAllExDateCountList" id="column">
									<tr>
										<td>
											<s:property value="supplyName" />
										</td>
										<td>
											<s:property value="itemPrice" />
										</td>
									</tr>
								</s:iterator>
							</table>
				 --%>

						</div>
					</div>
				</div>
			</div>
		</div>	
			<br />
			<br />
			<script type="text/javascript">
	displaytagExportLink();
</script>
			<a href="user!add.html"></a>
			<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
