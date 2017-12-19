
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
			 <script type="text/javascript" src="../js/divselect.js"></script>           
			<script type="text/javascript" src="../js/calendar/calendar.js"></script>
			<script type="text/javascript" src="../js/calendar/lang/en.js"></script>
			<link rel="stylesheet" type="text/css" href="../js/calendar/jscal2.css"/>
			<link rel="stylesheet" type="text/css" href="../js/calendar/border-radius.css"/>
			<link rel="stylesheet" type="text/css" href="../js/calendar/win2k.css"/> 
	<script type="text/javascript">
		function showOrder(mainOrderId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/mainOrderShowModal.do?mainOrderId='
						+ mainOrderId , 700, 600);
		}
	$(document).ready(function(){
		   $("#wen_show").click(function(){
		       $("#wen_selAllExDateCountList").slideToggle();
		   });
		   $("#wen_hide").click(function(){
		       $("#wen_selAllExDateCountList").hide();
		   });
		})
	function show(tag){
		 var light=document.getElementById(tag);
		 light.style.display='block';
		 }
	function hide(tag){
	 var light=document.getElementById(tag);
	 light.style.display='none';
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
	<body style="min-width:700px;"> 
		<%-- 
		<%@ include file="../common/incBanner.jsp"%>
		
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list!listUdForUser" namespace="/useraccountdealdetail"
			method="post">
				<table>
					<tr>
						<td>&nbsp;<s:select name="flag" list="%{#{0:'===选择记录类型===',1:'划款',2:'商户充值',3:'消费',4:'冲正',5:'提款',6:'被提款',8:'提取佣金',100:'当日结算',255:'取消订单'}}" listKey="key"></s:select>&nbsp;</td>
						<td>&nbsp;<s:select name="voucherType" list="%{#{0:'===选择凭证类型===',1:'现金凭证',2:'支付凭证',3:'转账凭证',4:'财付通支付'}}" listKey="key"></s:select>&nbsp;</td>
						<td>经销商名:&nbsp;</td>
						<td>&nbsp;<input type="text" name="username" size="10" value="${param.username}"/>&nbsp;</td>
						<td>电话号码:&nbsp;</td>
						<td>&nbsp;<input type="text" name="mobile" size="10" value="${param.mobile}"/>&nbsp;</td>
						<td>请选择日期:&nbsp;</td>
						<td>&nbsp;<input id="d5221" name="startDate" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
									至
								<input id="d5222" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>&nbsp;</td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				</s:form>
		</div>
				 --%>
		 <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
          <div class="tqyj fixed">
           <s:form id="form0" name="form0"
			action="list!listUdForUser" namespace="/useraccountdealdetail"
			method="post">
                     <div class="cxjj fixed">
                         <c:choose>
                           <c:when test="${user.userType==1}">
                              <div class="quota cxjjtable" style="width: 90%;">
                                     <table  border="0" cellpadding="0" cellspacing="0" >
                                          <tr>
                                             
                                               <td width="100">
                                                     <div id="divselect7" class="divselect1 divselect">
                                                          <cite>选择记录类型</cite>
                                                          <ul>
                                                             <li><a href="javascript:;" selectid="0">选择记录类型</a></li>
                                                             <li><a href="javascript:;" selectid="1">划款</a></li>
                                                             <li><a href="javascript:;" selectid="2">商户充值</a></li>
                                                             <li><a href="javascript:;" selectid="3">消费</a></li>
                                                             <li><a href="javascript:;" selectid="4">冲正</a></li>
                                                             <li><a href="javascript:;" selectid="5">提款</a></li>
                                                             <li><a href="javascript:;" selectid="6">被提款</a></li>
                                                             <li><a href="javascript:;" selectid="7">向供货商充值</a></li>
                                                             <li><a href="javascript:;" selectid="8">提取佣金</a></li>
                                                             <li><a href="javascript:;" selectid="9">扣手续费</a></li>
                                                             <li><a href="javascript:;" selectid="10">加手续费</a></li>
                                                             <li><a href="javascript:;" selectid="100">当日结算</a></li>
                                                             <li><a href="javascript:;" selectid="255">取消订单</a></li>
                                                          </ul>
                                                      </div>
                                                     <input name="flag" type="hidden" value="" id="inputselect1"/>
                                               
                                                    <script type="text/javascript">
													$(function(){
														$.divselect("#divselect7","#inputselect1");
													});
													</script>
                                                </td>
                                                 <td> 操作员：<input type="text" name="recodeOperator"  class="khmm">  </td>
                                                <td width="100">
                                                     <div id="divselect6" class="divselect1 divselect">
                                                          <cite>选择凭证类型</cite>
                                                          <ul>
                                                             <li><a href="javascript:;" selectid="0">选择凭证类型</a></li>
                                                             <li><a href="javascript:;" selectid="1">现金凭证</a></li>
                                                             <li><a href="javascript:;" selectid="2">支付凭证</a></li>
                                                             <li><a href="javascript:;" selectid="3">转账凭证</a></li>
                                                             <li><a href="javascript:;" selectid="4">财付通支付</a></li>
                                                             <li><a href="javascript:;" selectid="5">pos机充值支付</a></li>
                                                             <li><a href="javascript:;" selectid="6">支付宝支付</a></li>
                                                             <li><a href="javascript:;" selectid="7">pos机卡密支付</a></li>
                                                             <li><a href="javascript:;" selectid="8">易票联支付</a></li>
                                                          </ul>
                                                      </div>
                                                     <input name="voucherType" type="hidden" value="" id="inputselect2"/>
                                               
                                                    <script type="text/javascript">
													$(function(){
														$.divselect("#divselect6","#inputselect2");
													});
													</script>
                                                </td>
                                                <td width="80" align="right">经销商名：</td>
                                                <td width="100"><input name="username"  value="${param.username}" type="text" class="khmm" /></td>
                                           </tr>
                                           <tr>
                                             <td align="left" width="350" colspan="2">
						                       	 请选择日期：
						                       	
						                          <input id="d5221" name="startDate" class="date1" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
											  
												至
												<input id="d5222" name="endDate" class="date2" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/></td>
						                     
						                     <td width="80" align="right">电话号码：</td>
                                                <td width="100"><input name="mobile" value="${param.mobile}" type="text" class="khmm" /></td>
												<td><button  type="submit"  class="sousuo1"></button></td>
						                     </td>
						                             
		                       </tr>
		                      </table>
                           </div>
                           </c:when>
                           <c:when test="${user.userType>1}">
                                 <div class="quota cxjjtable" style="width: 750px;">
                                    
                                     <table  border="0" cellpadding="0" cellspacing="0" >
                                          <tr>
                                              <c:if test="${user.userType==2}">  
                                                  <td width="80" align="right">经销商名：</td>
                                                  <td width="100"><input name="username"  value="${param.username}" type="text" class="khmm" /></td>
                                               </c:if>
                                           <td width="80" align="right">电话号码：</td>
                                           <td width="100"><input name="mobile" value="${param.mobile}" type="text" class="khmm" /></td>
                                           <td width="100">
                                                     <div id="divselect7" class="divselect1 divselect">
                                                          <cite>选择记录类型</cite>
                                                          <ul>
                                                             <li><a href="javascript:;" selectid="0">选择记录类型</a></li>
                                                             <li><a href="javascript:;" selectid="1">划款</a></li>
                                                             <li><a href="javascript:;" selectid="2">商户充值</a></li>
                                                             <li><a href="javascript:;" selectid="3">消费</a></li>
                                                             <li><a href="javascript:;" selectid="4">冲正</a></li>
                                                             <li><a href="javascript:;" selectid="5">提款</a></li>
                                                             <li><a href="javascript:;" selectid="6">被提款</a></li>
                                                             <li><a href="javascript:;" selectid="8">提取佣金</a></li>
                                                             <li><a href="javascript:;" selectid="9">扣手续费</a></li>
                                                             <li><a href="javascript:;" selectid="10">加手续费</a></li>
                                                             <li><a href="javascript:;" selectid="100">当日结算</a></li>
                                                             <li><a href="javascript:;" selectid="255">取消订单</a></li>
                                                          </ul>
                                                      </div>
                                                     <input name="flag" type="hidden" value="" id="inputselect1"/>
                                               
                                                    <script type="text/javascript">
													$(function(){
														$.divselect("#divselect7","#inputselect1");
													});
													</script>
                                                </td>
                                             
                                              <td align="left" width="380" colspan="6">
						                       	 请选择日期：
						                       	
						                          <input id="d5221" name="startDate" class="date1" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-{%M-2}-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
											  
												至
												<input id="d5222" name="endDate" class="date2" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/></td>
						                       <%--
						                        <td width="80" align="right">电话号码：</td>
                                                <td width="100"><input name="mobile" value="${param.mobile}" type="text" class="khmm" /></td>
						                        --%>
												<td><button  type="submit"  class="sousuo1"></button></td>
						                     </td>
		                       </tr>
		                      </table>
		                      
                           </div>
                           </c:when>
                         </c:choose> 
                         <%--
                       <p class="jjcx">经销商额度列表</p>
                          --%> 
                 </div>
          </s:form>
		
		                      
		<%--
		
		 <c:if test="${user.userType==1}">
		  <center>
		     <div class="cxjjtable3">
		     <P>
		      <form id="form1" name="form1"action="list!listUdForUser.do" namespace="/useraccountdealdetail"
			method="post">
		        按时间查询：<input id="d52211" name="startDate_wen" class="date1" type="text" onFocus="var d5222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},maxDate:'#F{$dp.$D(\'d52222\')}'})"/>
									至
								<input id="d52222" name="endDate_wen" class="date2" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})"/>
		     <input   type="submit" value="搜索"/>
		      </form>
		     </P>
		    
		    <p style=" width: 30%; ">
		                  查询日期为：
		           <s:if test="endDate_wen != null && startDate_wen !=null">
		                  ${startDate_wen } 至 ${endDate_wen }
		           </s:if>
		           <s:else>
			                                      今日	
		           </s:else>
                <br/>	           
		                  所有商户（管理员另外）当日结算的合计数为: <span style="color: red; font-size: 16px;"> ${ExDateCount }</span>元
		       <br/>
		            <a href="javaScript:;" id="wen_show" onclick="show('light2')">所有供货商的当日结算合计数</a> 
		       <div id="light2" class="white_content" style="max-height: 200px;overflow: scroll;">
      <div class="div_close"><a href="javascript:void(0)" onclick="hide('light2')"> 关闭</a></div>
      <div class="con"> 
       <table class="list_tb_002" style="width: 95%">
			                 <tr>
			                    <th>经销商名称</th>
			                    <th>当日结算合计数(/元)</th>
			                 </tr>
			                <s:iterator value="#session.selAllExDateCountList" id="column">
			                  <tr>
			                    <td>
		                           <s:property value="key"/>
			                    </td>
			                    <td>
		                          <s:property value="value"/>
			                    </td>
			                 </tr>
			                </s:iterator>
			              </table>
      </div>
</div>
		    </center>
		    </c:if>
		     --%>
		    
		     <p class="zhuyi_p  order_zhuyi_p">
		                                                                         此列表只用于额度对账使用，切勿以此列表判断订单成功与否，订单状态查询请点击：
		                                 <c:if test="${sessionScope.user.usergroup.groupType<4}">
		                                   <a href="/MPRS/order/list.do" style="text-decoration:underline;color:red;">订单查询</a>，如有疑问请联系我司客服QQ或致电:4000307517查询。
		                                 </c:if>
		                                 <c:if test="${sessionScope.user.usergroup.groupType>=4}">
		                                   <a href="/MPRS/order/lists.do" style="text-decoration:underline;color:red;">订单查询</a>，如有疑问请联系我司客服QQ或致电:4000307517查询。
		                                 </c:if>
		                       </p>
		      <div class="modify" style="margin-top:0px;">                  
			<display:table export="true" id="idListTb" name="pageView" style="width:100%;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb_002" decorator="com.sys.volunteer.common.OverOutWrapperUtils"
				requestURI="${pageContext.request.contextPath}/useraccountdealdetail/list!listUdForUser.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				
				<c:if test="${sessionScope.user.usergroup.groupType<=2}">
				    <display:column title="订单流水号" media="html">
							<a href="javascript:void(0);" onclick="showOrder('${idListTb.mainorder.mainOrderId }')">
							${idListTb.mainorder.mainOrderId }
							</a>
				    </display:column>
				    <display:column title="订单流水号" media="excel">
							${idListTb.mainorder.mainOrderId }
				    </display:column>
				     
				</c:if>
				     <display:column property="userName" title="商户" />
				<c:if test="${sessionScope.user.usergroup.groupType==4}">
				<display:column property="recodeUserName" title="对应商户" />
				</c:if>
				<display:column property="mobile" title="号码" />
				<display:column property="createTime" title="日期"
					format="{0,date,yy-MM-dd HH:mm}" />
					
					<display:column title="交易金额"  media="html">
					<c:choose>
						<c:when test="${idListTb.flag==1}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:when test="${idListTb.flag==2}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:when test="${idListTb.flag==3}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:when test="${idListTb.flag==4}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:when test="${idListTb.flag==5}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:when test="${idListTb.flag==6}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:when test="${idListTb.flag==8}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:when test="${idListTb.flag==255}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></font></c:when>
						<c:otherwise><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:otherwise>
					</c:choose>
				</display:column>
					<display:column title="交易金额"  media="excel" property="dealMoney">
					<%--
					<c:choose>
						<c:when test="${idListTb.flag==1}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:when test="${idListTb.flag==2}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:when test="${idListTb.flag==3}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:when test="${idListTb.flag==4}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:when test="${idListTb.flag==5}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:when test="${idListTb.flag==6}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:when test="${idListTb.flag==8}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:when test="${idListTb.flag==255}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:when>
						<c:otherwise><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealMoney }"/></c:otherwise>
					</c:choose>
					 --%>
				</display:column>
				<display:column title="交易佣金"  media="html">
					<c:choose>
						<c:when test="${idListTb.flag==1}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:when test="${idListTb.flag==2}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:when test="${idListTb.flag==3}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:when test="${idListTb.flag==4}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:when test="${idListTb.flag==5}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:when test="${idListTb.flag==6}"><font color="#CE0000"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:when test="${idListTb.flag==8}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:when test="${idListTb.flag==255}"><font color="#009100"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></font></c:when>
						<c:otherwise><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="交易佣金" media="excel" property="currentBalance">
				<%--
					<c:choose>
						<c:when test="${idListTb.flag==1}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:when test="${idListTb.flag==2}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:when test="${idListTb.flag==3}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:when test="${idListTb.flag==4}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:when test="${idListTb.flag==5}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:when test="${idListTb.flag==6}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:when test="${idListTb.flag==8}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:when test="${idListTb.flag==255}"><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:when>
						<c:otherwise><fmt:formatNumber maxFractionDigits="4" value="${idListTb.dealCommission }"/></c:otherwise>
					</c:choose>
				 --%>
				</display:column>
				<display:column title="当前余额" media="html">
					<fmt:formatNumber maxFractionDigits="4" value="${idListTb.currentBalance}"/>
				</display:column>
				<display:column title="当前余额" media="excel" property="currentBalance"></display:column>
				
				<display:column title="当前佣金余额" media="html">
					<fmt:formatNumber maxFractionDigits="4" value="${idListTb.currentCommission }"/>
				</display:column>
				<display:column title="当前佣金余额" media="excel" property="currentCommission"></display:column>
				<display:column title="记录类型" >
					<c:choose>
						<c:when test="${idListTb.flag==1}">划款</c:when>
						<c:when test="${idListTb.flag==2}">商户充值</c:when>
						<c:when test="${idListTb.flag==3}">消费</c:when>
						<c:when test="${idListTb.flag==4}">冲正</c:when>
						<c:when test="${idListTb.flag==5}">提款</c:when>
						<c:when test="${idListTb.flag==6}">被提款</c:when>
						<c:when test="${idListTb.flag==8}">提取佣金</c:when>
						<c:when test="${idListTb.flag==9}">扣手续费</c:when>
						<c:when test="${idListTb.flag==10}">加手续费</c:when>
						<c:when test="${idListTb.flag==100}">当日结算</c:when>
						<c:when test="${idListTb.flag==255}">取消订单</c:when>
					</c:choose>
				</display:column>
				
				<display:column title="凭证类型" >
					<c:choose>
						<c:when test="${idListTb.voucherType==1}">现金凭证</c:when>
						<c:when test="${idListTb.voucherType==2}">支付凭证</c:when>
						<c:when test="${idListTb.voucherType==3}">转账凭证</c:when>
						<c:when test="${idListTb.voucherType==4}">财付通支付</c:when>
						<c:when test="${idListTb.voucherType==5}">pos机充值支付</c:when>
						<c:when test="${idListTb.voucherType==6}">支付宝支付</c:when>
						<c:when test="${idListTb.voucherType==7}">pos机卡密支付</c:when>
						<c:when test="${idListTb.voucherType==8}">易票联支付</c:when>
						<c:otherwise>无</c:otherwise>
					</c:choose>
				</display:column>
				
				<display:column  title="凭证号" >
				 ${idListTb.voucherCode} 
				</display:column>
			
				<c:if test="${sessionScope.user.usergroup.groupType<=2}">
				    <display:column property="recodeOperator" title="操作员" /> 
				</c:if>
				
				<s:if test="flag==100">
				<display:column property="itemPrice" title="合计" /> 
				</s:if>
			 <display:setProperty name="export.excel.filename" value="times.xls"/>
			
			</display:table>
			
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
