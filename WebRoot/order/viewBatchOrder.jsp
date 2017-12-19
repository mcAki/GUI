<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  <title>商户信息</title>
  <link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
     <link href="../css/main_new.css" rel="stylesheet" type="text/css" />
		
		<%@ include file="../common/incHead.jsp"%>
	    <%@ include file="../common/incBanner.jsp" %>
	    <script type="text/javascript" src="../js/divselect.js"></script>     
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
		function m_over(tr) {
		tr.className = tr.className + " " + "trmo";
	}
	function m_out(tr) {
		var cn = tr.className.replace(/\s+trmo/, '');
		tr.className = cn;
	}
	
		function showPersonalByUserId2(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId4.do?userId='
						+ userId , 700, 600);
		}
	</script>
	 <style type="text/css">
	  .formtable2 td{
	     font-size: 14px;
	 }
	 
	 </style>
  </head>  
  <body style="min-width: 800px">
  <!--  
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="550" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>商户信息</caption>
  	  <tr>
  	    <td colspan="4"></td>
      </tr>
     
      <tr>
	   <td align="right" >当前用户级别:</td>
	   <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.usergroup.id==1}">超级管理员</c:when>
	    						<c:when test="${users.usergroup.id==2}">员工</c:when>
	    						<c:when test="${users.usergroup.id==3}">供货商</c:when>
	    						<c:when test="${users.usergroup.id==4}">一级商户</c:when>
	    						<c:when test="${users.usergroup.id==5}">二级商户</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
     
  	  <tr>
      	<td width="121" align="right">经销商名称:</td>
  	    <td align="left"><s:label name="users.userName"/></td>
  	    <td>&nbsp;</td>
  	   	<td>&nbsp;</td>
  	  </tr>
  	  
  	  <tr>
  	   <td align="right">状态:</td>
  	   <td align="left" >
			<c:choose>
				<c:when test="${users.state==0}">正常</c:when>
				<c:when test="${users.state==1}">冻结</c:when>
				<c:when test="${users.state==2}">禁用</c:when>
			</c:choose>
		</td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
  	  
      <tr>
  	   <td align="right">上级商户:</td>
  	   <td align="left" ><s:label name="parentUser.userName"/></td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
      
  	  <tr>
	   <td align="right" >性&nbsp;别:</td>
	   <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.gender==1}">男</c:when>
	    						<c:when test="${users.gender==2}">女</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right">email:</td>
  	   <td align="left" ><s:label name="users.email"/></td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
      
      <tr>
  	    <td align="right">手机号码:</td>
  	    <td align="left" ><s:label name="users.mobile"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">账户余额:</td>
  	    <td align="left" ><s:label name="useraccount.balance"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">佣金余额:</td>
  	    <td align="left" ><s:label name="useraccount.commission"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
       <tr>
        <td align="right">可冲正次数:</td>
  	   	<td align="left" ><s:label name="users.reversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">最大可冲正次数:</td>
  	   	<td align="left" ><s:label name="users.maxReversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">终端机号:</td>
  	    <td align="left" ><s:label name="users.terminalNo"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
       
       <tr>
        <td align="right" >备&nbsp;注:</td>
  	    <td align="left" colspan="3"><s:label name="users.remark" rows="3"/></td> 	  
       </tr>
		
  </table>
  -->
  <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj1 tqyj fixed">
                     <strong class="tqxj">订单信息</strong>
                     <div class="hyxx1 hyxx" style="width:750px; min-height:300px; padding-top:30px; margin: 0 auto; "> 
                        
                            <table width="100%" border="2" bordercolor="blue" cellpadding="1" cellspacing="1" class="formtable2" >
                                 <tr align="center">
                                   <td colspan="4" align="center" style="font-size: 16px;font-weight: bold;"> 订单资料 </td>
                                 </tr>
                                 <tr align="center">
                                    <td>批量充值订单id:</td>
                                    <td> ${batchOrder.batchOrderId }</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                 </tr>
                                 <tr align="center">
                                    <td>下单人：</td>
                                    <td> ${batchOrder.userName } </td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                 </tr>
                                  <tr align="center">
                                    <td>金额:</td>
                                    <td>${batchOrder.totalMoney }</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                 </tr>
                                 <tr align="center">
                                    <td>创建日期：</td>
                                    <td><fmt:formatDate value="${batchOrder.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
                                    <td>订单状态：</td>
                                    <td>
                                     <c:choose>
											<c:when test="${batchOrder.batchOrderState==0}">
											  待确认
                                          </c:when>
											<c:when test="${batchOrder.batchOrderState==1}"> 
										            未开始
											</c:when>
											<c:when test="${batchOrder.batchOrderState==2}"> 
											     进行中
											</c:when>
											<c:when test="${batchOrder.batchOrderState==3}"> 
											     暂停
											</c:when>
											<c:when test="${batchOrder.batchOrderState==4}"> 
											     停止
											</c:when>
											<c:when test="${batchOrder.batchOrderState==255}"> 
											     已完成
											</c:when>
										</c:choose>
                                    </td>
                                 </tr>
                                  <tr align="center">
                                   <td>操作：</td>
                                   <td colspan="3">
                                    
									<c:if test="${sessionScope.user.usergroup.groupType==1
									 ||(sessionScope.user.usergroup.groupType<users.usergroup.groupType&&sessionScope.user.usergroup.groupType<=2)
									 || (sessionScope.user.userId==batchOrder.users.userId)}">
									 <c:choose>
									 	<c:when test="${batchOrder.batchOrderState==0||batchOrder.batchOrderState==1}">
									 		<a href="/MPRS/order/startBatchOrder.do?batchOrderId=${batchOrder.batchOrderId}">开始</a>&nbsp;
									 	</c:when>
									 	<c:when test="${batchOrder.batchOrderState==2}">
									 		<a href="/MPRS/order/pauseBatchOrder.do?batchOrderId=${batchOrder.batchOrderId}">暂停</a>&nbsp;
									 	</c:when>
									 	<c:when test="${batchOrder.batchOrderState==3}">
									 		<a href="/MPRS/order/restoreBatchOrder.do?batchOrderId=${batchOrder.batchOrderId}">恢复</a>&nbsp;
									 	</c:when>
									 </c:choose>
									</c:if>
                                </td>
                                <%--<c:if test="${sessionScope.user.userId==users.parentUsers.userId }">
                                   <a href="/MPRS/users/page!recharge.do?userId=${users.userId}">设置额度</a>&nbsp;
                                </c:if>--%>
                            
                             </table>    
                     </div>

             </div>
          </div>
      
			<c:if test="${pageView!=null}">
		<div class="tqyj fixed" >
                     <s:form id="form0" name="form0" action="list!viewBatchOrder" namespace="/order" method="post">
                     <div class="cxjj fixed">
                              <div class="rechargey cxjjtable" style="position:absolute;">
                                     <table width="850" height="36" border="0"  cellpadding="0" cellspacing="0">
                                      <td>流水号：<input type="text" name="mainOrderId" value="${mainOrderId }" style="width:230px;" /> </td>
                                      <td >
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
		                                                       <input name="state" type="hidden" value="" id="inputselect"/>
		                                                <script type="text/javascript">
															$(function(){
																$.divselect("#divselect10","#inputselect");
															});
															</script>
												</td>
												<td  >
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
                                                <td width="80" align="right" valign="middle">手机号码：</td>
                                                <td width="110" valign="middle">
                                                <input name="mobile" style="height: 30px;" type="text" value="${param.mobile}" class="khmm" />
                                                <s:hidden name="batchOrderId" />
                                                </td>
												<td>
												<button  type="submit"  class="sousuo1" style="vertical-align: middle;height: 30px;"></button> 
												</td>
                                          </tr>
                                   </table>
                              </div>
                            <p class="jjcx">话费订单查询列表</p>
                     </div>
                     </s:form>
                      <p class="zhuyi_p order_zhuyi_p"  >订单成功与否以  订单查询 的最终状态为准，处理中的订单切勿退款或补充，如有疑问请联系我司客服QQ或致电:4000307517查询。</p>
			<div class="modify" style="margin-top:0px;">
				<display:table export="true" id="idListTb" name="pageView"
					style="min-width:600px;" sort="external" pagesize="${pageSize}"
					cellspacing="1" class="list_tb_002" 
					requestURI="${pageContext.request.contextPath}/order/list!viewBatchOrder.do"
					decorator="com.sys.volunteer.common.OverOutWrapperUtils">
					<display:setProperty name="paging.banner.placement" value="bottom" />
				 <c:if test="${sessionScope.user.usergroup.groupType==4}">	
				    <display:column title="商户名称" property="userName" />
				 </c:if>
					<display:column title="充值号码" property="mobile" />
					<display:column property="createTime" title="充值日期"
						format="{0,date,yy-MM-dd HH:mm}" />
					
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
					<display:column title="运行状态">
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
					<display:column title="流水号" media="html">
					${idListTb.mainOrderId }
					</display:column>
					<c:if test="${sessionScope.user.usergroup.groupType<=2}">
						<display:column title="供货商" property="supplyName" media="excel"/>
					</c:if>
					<display:column title="经销商" property="userName" media="excel"/>
					<display:column title="终端机号" property="terminalNo" media="excel"/>
					<display:column title="购买数量" property="goodsNo" media="excel" />
					
					
					<display:column title="流水号" media="excel">
					${idListTb.mainOrderId }
					</display:column>
					
					<display:setProperty name="export.excel.filename" value="times.xls"/>
				</display:table>
		
			</div>
	</div>
	</c:if>
	<script type="text/javascript">
	displaytagExportLink();
</script> 
</div>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
