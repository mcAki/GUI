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
   $(document).ready(function(){
       for(var j = 1;j<6;j++){
       $("#id_"+j).val("");
       }
      $("#select_id").change(function(){
         var selectId = $("#select_id").find("option:selected").val();
         $("#id_"+selectId).show();
         for(var i =1;i<=6;i++){
           if(i!=selectId){
               $("#id_"+i).val("");
               $("#id_"+i).hide();
           }
         }
      });
      
        //-------------------->复选框的单选，全选，反选；
		      $(".checkAll").click(function(){
		         if($(".checkAll").attr("checked")==true){
		             $("[name='orderIds']").attr("checked",'true');
		         }else{
		             $("[name='orderIds']").removeAttr("checked",'true');
		         }
		      });
		       
		       $(".check").click(function(){
		       //记录选中项
		          var checked = "";
		         $("[name='orderIds']").each(function(){
		           if($(this).attr("checked")==true){
		               $(".checkAll").attr("checked",'true');
		               checked+=$(this).val();
		           }
		           })
		           if(checked==""){
		             $(".checkAll").removeAttr("checked",'true');
		           }
		       });
		       //------------------------------->复选框选择结束
               $("#batchCancel_id").click(function(){
                    batchCancelFrom.submit();
               });
               
               $("#reset_paw_id").click(function(){
                   $.post("/MPRS/order/addOrderComments.do",$("#addComm_form").serialize(),function(data){
                       alert(data);
                   });
                   hide('light2');
               });
   })  
 function show(tag,orderId,mobile){
		 var light=document.getElementById(tag);
		 document.getElementById("hide_orderId").value= orderId;
		 document.getElementById("mobile").innerHTML = mobile;
		 light.style.display='block';
		 light.style.position='fixed';
		 light.style._position='absolute';
		 }
	function hide(tag){
	 var light=document.getElementById(tag);
	 light.style.display='none';
	}
</script>   	
<style type="text/css">   
    .trmo{   
        background-color:#5F9F9F !important;   
    } 
    .white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 45%;
	width: 300px;
	padding: 6px 16px;
	border: 12px solid #D6E9F1;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: #f5f5f5;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.close_div {
	float: right;
	clear: both;
	width: 100%;
	text-align: right;
	margin: 0 0 6px 0
}

.close_div a {
	color: #333;
	text-decoration: none;
	font-size: 14px;
	font-weight: 700
}

.con {
	text-indent: 1.5pc;
	line-height: 21px
}
</style>   

	</head>
	<body style="min-width: 2200px;">
	
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
		 <%--
		  --%>
	 <div id="comInfo" class="fixed" >
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
          <div class="tqyj fixed" >
		<div align="left">
			<s:form id="form0" name="form0" action="list" namespace="/order"
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
						<c:if test="${sessionScope.user.usergroup.groupType==1}">
						
						<%--
						<td>&nbsp;订单流水号:&nbsp;</td>
						<td><input type="text" name="mainOrderId" size="10"	value="${param.mainOrderId}" /></td>
						<td>冲正订单日期选择:&nbsp;</td>
						<td>&nbsp;
							<input id="d52211" name="start2Date" class="Wdate" type="text"
								onFocus="var d52222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},maxDate:'#F{$dp.$D(\'d52222\')}'})" />
							至
							<input id="d52222" name="end2Date" class="Wdate" type="text"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})" />
							&nbsp;
						</td>
						 --%>
						<td>&nbsp;&nbsp;</td>
						<td>
						    <s:select id="select_id" list="%{#{1:'手机号',2:'经销商',3:'终端机号',4:'订单流水号',5:'补充人',6:'大单号'}}"></s:select>
						</td>
						<td>&nbsp;&nbsp;</td>
						<td  >
							<input id="id_1" type="text" name="mobile" 
								value="${param.mobile}" />
							<input id="id_2" style="display: none;" type="text" name="username" 
								value="${param.username}" />
							<input id="id_3" style="display: none;" type="text" name="terminalNo" 
								value="${param.terminalNo}" />
							<input id="id_4" style="display: none;" type="text" name="mainOrderId"  value="${param.mainOrderId}" />
						    <input id="id_5" style="display: none;" name="manualUserName" type="text" class="sjyz"/>
						    <input id="id_6" style="display: none;" name="batchOrderId" type="text" class="sjyz"/>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						   <%--
						<td>
							&nbsp;
							<s:select name="goodsId" list="goodsList" listKey="goodsId"
								listValue="goodsName"></s:select>
							&nbsp;
						</td>
						    --%>
						
						<td>
							&nbsp;
							<s:select name="state"
								list="%{#{-2:'===选择订单状态===',-1:'已冲正',0:'申请处理',1:'处理成功',2:'处理失败',3:'处理中',4:'已完成',253:'申请取消',254:'取消中',255:'已取消'}}"
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
						<c:if test="${sessionScope.user.usergroup.groupType<=2}">
							<td>&nbsp;<s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select>
								&nbsp;
							</td>
						</c:if>
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
						
						 </c:if>
						 <c:if test="${sessionScope.user.usergroup.groupType>1}">
						<td>
							&nbsp;手机号:&nbsp;
						</td>
						<td>
							<input type="text" name="mobile" size="10"
								value="${param.mobile}" />
						</td>
						</c:if>
					</tr>
				</table>
		    <div class="cxjjtable3" style="width: 90%">
				    <table >
				       <tr>
				          <td>人工补充: </td>
				          <td>
				               <div id="divselect8" class="divselect3 divselect">
                                                              <cite>选择人工补充</cite>
                                                              <ul >
                                                                 <li><a href="javascript:;" selectid="0">不需要</a></li>
                                                                 <li><a href="javascript:;" selectid="1">需要</a></li>
                                                                 <li><a href="javascript:;" selectid="255">已卡充</a></li>
                                                              </ul>
                                                      </div>
                                                       <input name="isNeedManual" type="hidden" value="" id="inputselect1"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect8","#inputselect1");
													});
													</script>
				          </td>
				          <%--
				          <td>&nbsp;&nbsp;&nbsp; 补充人:</td>
				          <td><input name="manualUserName" type="text" class="sjyz"/></td>
				           --%>
				          <td>     是否直充:  </td>
				          <td>
                              <div id="divselect10" class="divselect3 divselect">
                                   <cite>选择是否直充</cite>
                                     <ul >
                                          <li><a href="javascript:;" selectid="1">是</a></li>
                                          <li><a href="javascript:;" selectid="2">否</a></li>
                                     </ul>
                              </div>
                               <input name="orderType" type="hidden" value="" id="inputselect2"/>
                               <script type="text/javascript">
									$(function(){
										$.divselect("#divselect10","#inputselect2");
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
                                <input name="isTerminal" type="hidden" value="" id="inputselect3"/>
                                      <script type="text/javascript">
											$(function(){
												$.divselect("#divselect11","#inputselect3");
											});
									</script>
							</td>
								
				         <td>
				         &nbsp;&nbsp;&nbsp;
					       <input  type="submit" value="搜索" />
				         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				         </td>
				       </tr>
				    </table>
				 </div>	
				</s:form>
		    <%--
		      <p class="jjcx">订单列表</p>
		     --%>
		</div>
		
		<center >
		<div class="modify">
		     <p class="zhuyi_p">订单成功与否以  订单查询  的最终状态为准，处理中的订单切勿退款或补充，如有疑问请联系我司客服QQ或致电:4000307517查询。</p>
		<form action="/MPRS/order/batchCancelOrderExe.do" method="get" name="batchCancelFrom">    
		<display:table  id="idListTb" export = "true"
			name="pageView"  sort="external"
			pagesize="${pageSize}" cellspacing="1" class="list_tb_002"
			requestURI="${pageContext.request.contextPath}/order/list.do" decorator="com.sys.volunteer.common.OverOutWrapperUtils">
			<display:setProperty name="paging.banner.placement" value="bottom" />
			
			<display:column title="号码" property="mobile" />
			<display:column property="createTime" title="销售日期"
				format="{0,date,yy-MM-dd HH:mm}" />
			<display:column title="操作  <input type='checkbox' class='checkAll' name='checkbox' value='0' /> <a href='javaScript:;' id='batchCancel_id'>取消订单</a>" media="html" >
				<c:if test="${sessionScope.user.usergroup.groupType<=2}">
				<a href="javaScript:;" onclick="show('light2','${idListTb.mainOrderId }','${idListTb.mobile }')" > 添加备注 </a>
				</c:if>
				<c:choose>
					<c:when
						test="${idListTb.state==1&&idListTb.reversalState==-1&&idListTb.orderType==1&&
							(sessionScope.user.usergroup.groupType<=2||
								(sessionScope.user.usergroup.groupType>=4&&idListTb.canReverse==1))}">
						<a href="/MPRS/order/reversalOrders.do?orderId=${idListTb.mainOrderId }" onclick="return window.confirm('手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue } 您确定要订单冲正吗？')">订单冲正</a>
					</c:when>
					<c:when
						test="${(idListTb.state==3||idListTb.state==0||idListTb.state==5)&&idListTb.orderType==1&&sessionScope.user.usergroup.groupType<=2}"> <input type="checkbox" calss="check" name="orderIds" value="${idListTb.mainOrderId }"/>
						<a href="/MPRS/order/cancelOrderExe.do?orderId=${idListTb.mainOrderId }" onclick="return window.confirm(' 手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue }  您确定要取消订单吗？')">取消订单</a>
						<a href="/MPRS/order/cancelOrderNoReturnExe.do?orderId=${idListTb.mainOrderId }" onclick="return window.confirm('  手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue } 您确定要取消订单(不反额度)吗？')">取消订单(不反额度)</a>
						<br/>
						<a href="/MPRS/order/dealOrders.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' 手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue }  您确定要强制订单成功吗？')">强制订单成功</a>
						<a href="/MPRS/order/dealOrdersNoReturn.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' 手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue } 您确定要强制订单成功(不反额度)吗？')">强制订单成功(不反额度)</a>
					</c:when>
					<c:when
						test="${(idListTb.state==2||idListTb.state==255)&&idListTb.orderType==1&&sessionScope.user.usergroup.groupType<=2}">
						<br/>
						<a href="/MPRS/order/dealOrders.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm('  手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue }  您确定要强制订单成功吗？')">强制订单成功</a>
						<a href="/MPRS/order/dealOrdersNoReturn.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm(' 手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue } 您确定要强制订单成功(不反额度)吗？')">强制订单成功(不反额度)</a>
					</c:when>
					<c:when
								test="${(idListTb.reversalState==0||idListTb.reversalState==-2||idListTb.reversalState==5)&&idListTb.orderType==1&&sessionScope.user.usergroup.groupType<=2}">
								<br/>
								<a href="/MPRS/order/reverseOrderSuccess.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm('  手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue }  您确定要强制订单冲正成功吗？')">强制订单冲正成功</a>
								<a href="/MPRS/order/reverseOrderFailed.do?orderId=${idListTb.mainOrderId }"
									onclick="return window.confirm('  手机号码： ${idListTb.mobile}；面额 :${idListTb.goodsValue } 您确定要强制订单冲正失败吗？')">强制订单冲正失败</a>
							</c:when>
				</c:choose>
				<c:if test="${idListTb.state==2&&idListTb.isNeedManual==1&&idListTb.isTerminal==1&&sessionScope.user.usergroup.groupType<=2}">
					<a href="/MPRS/order/page!manualOrder.do?orderId=${idListTb.mainOrderId }" onclick="return window.confirm(' ${idListTb.mobile} : 您确定要人工充值吗？')">人工充值</a>
				</c:if>
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
					<c:when test="${idListTb.state==4}">已完成</c:when>
					<c:when test="${idListTb.state==5}">处理中</c:when>
					<c:when test="${idListTb.state==253}">申请取消</c:when>
					<c:when test="${idListTb.state==254}">取消中</c:when>
					<c:when test="${idListTb.state==255}">已取消</c:when>
					<c:otherwise>未知</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="备注" property="commentsOn"></display:column>
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
			<display:column title="交易总金额" media="html">
				<fmt:formatNumber type="number" maxFractionDigits="4" value="${idListTb.totalMoney}"/>
			</display:column>
			<display:column title="交易总金额" media="excel" property="totalMoney"></display:column>
			
					<display:column title="进货价" media="html">
						<fmt:formatNumber maxFractionDigits="4" value="${idListTb.stockPrice }"/>
					</display:column>
					<display:column title="进货价" media="excel" property="stockPrice"></display:column>
					
					<display:column title="销售价" media="html">
						<fmt:formatNumber maxFractionDigits="4" value="${idListTb.retailPrice }"/>
					</display:column>
					<display:column title="销售价" media="excel" property="retailPrice"></display:column>
			
			<display:column title="交易佣金" media="html">
				<fmt:formatNumber maxFractionDigits="4" value="${idListTb.commission }"/>
			</display:column>
			<display:column title="交易佣金" media="excel" property="commission">
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
			<display:column title="大单号" media="html">
				<a href="/MPRS/order/list!viewBatchOrder.do?batchOrderId=${idListTb.batchOrderId }" >${idListTb.batchOrderId }</a>
			</display:column>
			<display:column title="大单号" media="excel">
					${idListTb.batchOrderId }
					</display:column>
			<display:column title="订单流水号" property="mainOrderId" media="excel" />
			
			
			<display:column title="经销商" property="userName" />
			<display:column title="终端机号" property="terminalNo" />
			<c:if test="${sessionScope.user.usergroup.groupType<=2}">
			<display:column title="面额" property="goodsValue" />
			</c:if>
			<display:column title="购买数量" property="goodsNo" />
			
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
			
			<display:setProperty name="export.excel.filename" value="times.xls"/>
			 
			</display:table>
			</form> 
			<%-- 
			 <form action="/MPRS/export/export.do">
			   <input type="hidden" name = "flagArray" value="1"/>
			   <input type="hidden" name = "listMainOrder" value="${listMainOrder }"/>
			        <input type="submit" value="导出excel"/>
			 </form>
			 --%>
			</div>
		</center>
		</div>
	</div>
	 <div id="light2" class="white_content">
      <div class="close_div"><a href="javascript:void(0)" onclick="hide('light2')"> 关闭</a></div>
      <div class="con"> 
      <form  name="reset_form" id="addComm_form">
        <table width="95%">
        	<tr>
        		<td>电话号码:</td>
        		<td><div id="mobile"></div></td>
        	</tr>
           <tr>
             <td> 填写备注信息：</td>
             <td> <s:textfield name="commentsOn" id="code_002"/> 
                  <s:hidden name="orderId" id="hide_orderId" ></s:hidden>
             </td>
           </tr>
           <tr>
              <td colspan="2"><span class="show_verifiCode_mes_002" style="color: red;"></span></td>
           </tr>
           <tr>
             <td colspan="2"> &nbsp; </td>
           </tr>
           <tr>
             <td  align="center" colspan="2"><button type="button" id="reset_paw_id">提交</button></td>
           </tr>
        </table>
         </form>
  	    
      </div>
</div>
		<%-- 
		 --%>
		<br />
		<br />
		<script type="text/javascript">
			displaytagExportLink();
	    </script>
		<a href="user!add.html"></a>
		
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
