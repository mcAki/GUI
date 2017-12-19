<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function showQuery(){
		
			//计算弹出位置
			
			height = 200;
			width = 300;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			var mobile = document.getElementById("mobile").value;
			
			var tradeType = document.getElementsByName("tradeType");
			var traValue;
			for(var i=0;i<tradeType.length;i++){
				if(tradeType[i].checked)
				traValue = tradeType[i].value;
			}
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/order/page!doQueryBalance.do?mobile="+mobile+"&tradeType="+traValue;
			//为了处理缓存加入随机时间
			url += "&date=" + new Date().getTime();
			
			//弹出参数
			var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=no;dialogWidth="+ width +"px;dialogHeight=" + height + "px";
			
			//创建传入参数
			var obj = new Object();
			obj.name = "参数";
			var returnVal = window.showModalDialog(url,obj,"dialogWidth=600px;dialogHeight=500px");
			if(!$.isEmptyObject(returnVal)){
				//$("#userId").val(returnVal.uid);
				//$("#userName").val(returnVal.username);
				var result = returnVal.result;
				var storeSeq = returnVal.storeSeq;
				//if(result==1){
					//window.name = "__self";
                    //window.open("${pageContext.request.contextPath}/order/page!chargeTelecom.do?storeSeq="+storeSeq , "__self");
                    //window.location.href="${pageContext.request.contextPath}/order/page!chargeTelecom.do?storeSeq="+storeSeq;
				//}
				if(result=="0000"){
					$("#errorMsg").css("display", "none");
					$("#CustomerInfo").css("display", "");
		            $("#QueryCustomerNum").html(returnVal.mobile);
		            var name = returnVal.name.replace(/[ ]/g,"");
		            $("#CustomerName").html(name);
		            switch(returnVal.payType){
		            	case "1":
		            		$("#PaymentType").html("后付费");
		            		break;
		            	case "2":
		            		$("#PaymentType").html("预付费");
		            		break;
		            	default:
		            		$("#PaymentType").html("未知");
		            		break;
		            }
		            $("#Balance").html(returnVal.balance);
				}else{
					$("#errorMsg").css("display", "");
					$("#msg").html("该号码没使用或已经停用,错误代码:"+result);
				}
				
			}
		}
		function showOrder(mainOrderId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/mainOrderShowModal.do?mainOrderId='
						+ mainOrderId , 700, 600);
		}
		$(document).ready(function(){
		
		var mobileTemp = document.getElementById("mobile_temp").value;
          if(mobileTemp!=""||mobileTemp.length>0){
               document.getElementById("mobile").value = mobileTemp;
          }
		
	$("#refresh_button").click(function(){
		      $.ajax({
			type : 'post',
			url : '/MPRS/order/refresh.do',
			dataType : 'json',
			success : function(data) {
			   $(".list_tb_002").html("<tr><th>手机号码</th><th>交易日期</th><th>号码余额</th><th>商品名称</th><th>订单状态</th><th>冲正状态</th><th>消费总额</th><th>购买数量</th><th>充值凭证</th></tr>");
				var stateTemp;
				var reversalStateTemp;
				$.each(data, function(i, item) {
				switch(item.state){
				   case -1:
				          stateTemp="冲正";
				          break;
				   case 0:
				          stateTemp="申请处理";
				          break;
				   case 1:
				          stateTemp="处理成功";
				          break;
				   case 2:
				          stateTemp="处理失败";
				          break;
				   case 3:
				          stateTemp="处理中";
				          break;
				   case 255:
				          stateTemp="用户取消";
				          break;
				}
				switch(item.reversalState){
				   case -1:
				          reversalStateTemp="未冲正";
				          break;
				   case 0:
				          reversalStateTemp="处理中";
				          break;
				   case 2:
				          reversalStateTemp="成功";
				          break;
				   case 2:
				          reversalStateTemp="失败";
				          break;
				}
				      $(".list_tb_002").append("<tr><td>"+item.mobile+
				      "</td><td>"+format(item.createTime.time, 'YY-MM-dd HH:mm')+
				      "</td><td>"+item.cBalance+
				      "</td><td>"+item.goodsName+
				      "</td><td>"+stateTemp+
				      "</td><td>"+reversalStateTemp+
				      "</td><td>"+item.totalMoney+
				      "</td><td>"+item.mainOrderId+"</td><td>");
					});
				      
			},
			error : function(text) {
				alert("刷新失败！");
			}
		});	 
		    });
		    })
	function checked(elm_id) {
    var danxuananniu = document.getElementById(elm_id);
        danxuananniu.checked = "checked";
        //alert(bank.value);
         } 
       function setMobile(){
          
       }
	</script>
	
  </head>  
   <body  style="min-width:680px;">
  <%@ include file="../common/incBanner.jsp" %>
  <%--
   
  	<s:form name="strutsForm" action="doChargeTelecom" namespace="/order" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="mainorder.mainOrderId" />
  	<s:hidden name="orderdetail.orderDetailId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>电信充值</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>
		<td width="145" align="right">
			电信号码:
		</td>
		<td align="left" class="nowrap">
			<s:textfield name="mobile" id="mobile"/>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	 </tr>
      
      <tr>
		<td width="145" align="right">
			选择商品类型:
		</td>
		<td align="left" class="nowrap">
			<s:radio name="tradeType" list="%{#{1:'电信单一充值',3:'宽带充值缴费',4:'电信综合缴费'}}" listKey="key" id="tradeType" value="1"></s:radio>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	 </tr>
	 
	 <tr>
      	<td align="right" >充值缴费金额:</td>
	 	<td align="left"><s:textfield id="11" name="amount" cssStyle="font-size:30px;"/>元</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
      </tr>
      
      <tr>
      	<td align="right" >输入密码:</td>
	 	<td align="left"><s:password  name="userPassword" id="12" cssStyle="font-size:30px;"/></td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
      </tr>
	 
	 <tr>
	 	<td style="display: none" id="CustomerInfo" colspan="5">
                    <div align="left" style="font-weight: bolder; color: Blue;">
                        <font size="3">以下为客户信息：</font></div>
                    <div align="left" >
                        <font style="font-weight: bolder;" size="3">电信号码：</font><span id="QueryCustomerNum"></span></div>
                    <div align="left" >
                        <font style="font-weight: bolder;" size="3">客户名称：</font><span id="CustomerName"></span></div>
                    <div align="left" >
                        <font style="font-weight: bolder;" size="3">账户金额：</font> <span id="Balance">
                        </span>
                    </div>
                    <div align="left" >
                        <font style="font-weight: bolder;" size="3">号码类型：</font><span id="PaymentType"></span></div>
         </td>
         
         <td style="display: none" id="errorMsg" colspan="5">
                    <div style="font-weight: bolder; color: Blue;">
                        <span id="msg"></span></div>
         </td>
	 </tr>
	 	
      <tr>
  	    <td colspan="2" align="center"><input icon="icon-btncom" type="button" value="查询余额"
				onclick='showQuery();'/>&nbsp;<s:submit icon="icon-apply" value="充值缴费"></s:submit>
        </td>
  	    <td colspan="2" align="center"></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
  --%>
   <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                     <div class="cxjj fixed">
                            <p style=" color: #669999; display: block; font-size: 14px; font-weight: bold;   padding-left: 20px;">电信充值</p>
                            <p style=" color: #669999; display: block; font-size: 14px; font-weight: bold;   padding-left: 20px;">
                                                                  只支持<span style="color:blue;">广东省内电信固话、宽带</span>充值缴费（含后付费用户）  </p>
                     </div>
                     <%--
                     <div style="float:left; ">
                      
                         <font style="color:red;font-size: 14px; font-weight: bold"> 只支持广东省内电信固话、宽带充值缴费（含后付费用户） </font>
                     </div><br/><br/>
                      --%>
                      <br/>
                     <div class="country communi" style="width:100%">
                       <center>
                             <s:form name="strutsForm" action="doChargeTelecom" namespace="/order" method="post">
								  	<center><s:fielderror></s:fielderror></center>
								  	<s:token/>
								  	<s:hidden name="mainorder.mainOrderId" />
								  	<s:hidden name="orderdetail.orderDetailId" />
                                        <table width="650" height="100" border="0" cellpadding="0" cellspacing="0">
                                             <tr>
                                                <td width="18%" align="right" class="font_order"> 电信号码：</td>
                                                <td width="82%" colspan="93%" align="left"><input  id="mobile" name="mobile" type="text" value="020" class="czhm" />
												<input type="hidden" value="${mobile }" id="mobile_temp"/>
												<script>
												  function FormFocus(obj,str) {
												        if (obj.value == str) {
												            obj.value = "";
												        }
												    }
												
												    function FormBlur(obj, str) {
												        if (obj.value == "") {
												            obj.value = str;
												        }
												    }
												  </script>
												  </td>
                                              </tr>
                                              <tr>
                                              <td width="18%" align="right" > &nbsp; </td>
                                                 <td align="left"><p class="move" style="display: block; padding-left:10px;text-align: center;">提示默认区号为020</p></td>
                                              </tr>
                                              <tr >
                                               <td height="10"> &nbsp; </td>
                                              </tr>
                                              <tr>
												<td width="18%" align="right" class="font_order">
												商品类型：
												</td>
												<td align="left" class="nowrap" style="font-size:14px; font-weight:bold;font-family: '微软雅黑','Arial Narrow';">
												<%--
													<s:radio name="tradeType" list="%{#{30:'电信单一充值',31:'宽带充值缴费',32:'电信综合缴费'}}" listKey="key" id="tradeType" value="30"></s:radio>
												 --%>
												 	 <input type="radio" value="30" id="30" name = "tradeType" checked="checked"/><span style="cursor: pointer;" onclick="checked('30')">电信单一充值 ：</span><font color="red">固定电话,包年宽带,无线宽带号码进行充值 </font><br/>
													 <input type="radio" value="31" id = "31"name = "tradeType"/><span style="cursor: pointer;" onclick="checked('31')">宽带充值缴费 ：</span><font color="red">为预/后付费宽带用户充值缴费，面额10元起 </font><br/>
													 <input type="radio" value="32" id="32" name = "tradeType"/><span style="cursor: pointer;" onclick="checked('32')">电信综合缴费 ：</span><font color="red">捆绑套餐用户缴费(如：E6、E8、E9套餐用户)</font> <br/>
												</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											 </tr>
                                        
                                            <tr>
                                                 <td width="18%" align="right" class="font_order">充值金额：</td>
                                                 <td width="82%" colspan="7" align="left"><input  id="11" onkeydown="if(event.keyCode==13) {event.keyCode=0;return false}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="amount"  type="text"  value="${amount }" class="czhm2 czhm"  /></td>
                                              </tr>
                                              <tr >
                                               <td height="20"> &nbsp; </td>
                                              </tr>
                                              <tr>
                                                    <td width="18%" align="right" class="font_order">交易密码：</td>
                                                    <td colspan="7" align="left"><input  onkeydown="if(event.keyCode==13) {event.keyCode=0;return false}" name="businessPassword" type="password"  value="" class="czhm" /></td>
                                             </tr>
                                      
                                         <tr>
										 	<td style="display: none" id="CustomerInfo" colspan="5">
									                    <div align="left" style="font-weight: bolder; color: Blue;">
									                        <font size="3">以下为客户信息：</font></div>
									                    <div align="left" >
									                        <font style="font-weight: bolder;" size="3">电信号码：</font><span id="QueryCustomerNum"></span></div>
									                    <div align="left" >
									                        <font style="font-weight: bolder;" size="3">客户名称：</font><span id="CustomerName"></span></div>
									                    <div align="left" >
									                        <font style="font-weight: bolder;" size="3">账户金额：</font> <span id="Balance">
									                        </span>
									                    </div>
									                    <div align="left" >
									                        <font style="font-weight: bolder;" size="3">号码类型：</font><span id="PaymentType"></span></div>
									         </td>
									         <td style="display: none" id="errorMsg" colspan="5">
									                    <div style="font-weight: bolder; color: Blue;">
									                        <span id="msg"></span></div>
									         </td>
									         </tr>
                                             <tr >
                                               <td height="10"> &nbsp; </td>
                                              </tr>
                                               </table>
                                               <table width="650">
                                             <tr>
                                                  <td  align="right" width="150">
				                                          <button class="sel_amount" type="button"  onclick='showQuery();'>查询余额 </button>
                                                   </td>
		                                               <td  align="center"  width="150">
					                                        <button class="goumai11" type="submit" >提交</button> 
		                                              </td>
                                             		<td align="left" >
			                                               <button  type="reset" class="bt_clear"  >清空</button>
                                                     </td>
										      </tr>
                                      </table>
                             </s:form>
                             </center>
                             <br/>
                       <%--
                     
                             <div class="check1 check" style="position: absolute;left:60%; top: 60px; overflow: hidden;">
                                   <strong>温馨提示</strong>
                                   <p> 1、综合缴费：两个以上电信产品捆绑套餐用户使用综合缴费，如IP超市，我的E家， 政企客户。 (不能单独为宽带缴费，宽带缴费请选择[宽带缴费]) </p>
									<p> 2、单一充值：电信的固定电话，小灵通，天翼手机等为本号码缴费使用单一充值。(不能为宽带缴费) </p>
                                   <p> 3.宽带充值需大于10元,预付费号码不能冲正,不支持包年宽带缴费,也可以使用(区号)ADSLD8888888充值宽带(字母必须大写)。无线宽带请使用[电信缴费]功能，再选择“单一充值”进行充值。 </p>
                                   <p>4.电信缴费空充只能抵扣出账话费，不能抵扣如宽带包年套餐、安装调测费等一次性费用。请各网点注意向客户做好解释，不要为用户空充一次性缴交费用。 </p>
									<p>注意：可以输入电信用户号码，包括电信固话、小灵通、天翼手机（180/189/133/153）, 宽带捆绑的固定电话，按查询余额继续。 </p>
                            </div>
                                   <strong>温馨提示</strong>
                                   <p> 1、综合缴费：两个以上电信产品捆绑套餐用户使用综合缴费，如IP超市，我的E家， 政企客户。<br/> (不能单独为宽带缴费，宽带缴费请选择[宽带缴费]) </p>
									<p> 2、单一充值：电信的固定电话，小灵通，天翼手机等为本号码缴费使用单一充值。(不能为宽带缴费) </p>
                                   <p> 3.宽带充值需大于10元,预付费号码不能冲正,不支持包年宽带缴费,也可以使用(区号)ADSLD8888888充值宽带(字母必须大写)。<br/>无线宽带请使用[电信缴费]功能，再选择“单一充值”进行充值。 </p>
                                   <p>4.电信缴费空充只能抵扣出账话费，不能抵扣如宽带包年套餐、安装调测费等一次性费用。<br/>请各网点注意向客户做好解释，不要为用户空充一次性缴交费用。 </p>
                             --%>
                             <div class="tishi_id" >
                              
                              <p>1、综合缴费：为该号码同一账户内所有号码进行充值缴费，捆绑套餐用户建议使用综合缴费进行充值（如：E6、E8、E9套餐用户）。 </p>
								<p>2、单一充值：仅为电信的固定电话单一号码进行充值。 </p>
								<p>3、宽带缴费：为预/后付费宽带用户充值缴费，面额10元起，暂无法为包年优惠赠送宽带充值,无线宽带请选择“单一充值”进行充              值。（单一宽带账号充值格式=区号+充值号码,如“02088888888”,此格式以外的号码不能充值）</p>
                           </div>
                       <div style="border:1px;border-style: solid;border-color: #ccc;background-color:#D5EAF8;border-radius: 5px 5px 5px 5px; ">
                           <strong style="padding-left:30px;">【最近五笔交易记录】</strong>
                            <img id="refresh_button" style="cursor: pointer;margin-left:300px;vertical-align:middle;margin-top:5px;margin-bottom:5px;" src="/MPRS/img/refresh_button.jpg"/> 
                       </div>
                                   <p class="zhuyi_p" >订单成功与否以 <a href="/MPRS/order/list.do" style="text-decoration:underline;color:red;">订单查询</a> 的最终状态为准，处理中的订单切勿退款或补充，如有疑问请联系我司客服QQ或致电:4000307517查询。</p>
                      <div class="payment modify" style="margin:0;">
                              <table class="list_tb_002"  style="680px;">
                              <tr>
                               <th>手机号码</th>
                               <th>交易日期</th>
                               <th>号码余额</th>
                               <!-- 
                               <th>订单流水</th>
                               <th> 订单操作 </th>
                                -->
                                 <th>商品名称</th>
                                 <th>订单状态</th>
                                 <th>冲正状态</th>
                                 <th>消费总额</th>
                                 <th>购买数量</th>
                                 <th>充值凭证</th>
                                 <!-- 
                                 <th>冲正日期</th>
                                 <th>售价</th>
                                 <th>佣金</th>
                                  -->
                              </tr>
                              <s:iterator value="#session.listLimitFive" var="list">
                              <tr>
                                 <td>${mobile }</td>
                                 <td>
                                   <fmt:formatDate value="${createTime}" pattern="yy-MM-dd HH:mm" />
                                 </td>
                                  <td> ${cBalance } </td>
                                 <!-- 
                                <td> <a href="javaScript:;" onclick="showOrder('${mainOrderId}')"> ${mainOrderId}</a> </td>
                                <td>
                                  <c:choose>
									<c:when
										test="${ state==1&& reversalState==-1&& orderType==1&&
											(sessionScope.user.usergroup.id<=2||
												(sessionScope.user.usergroup.id>=4&& canReverse==1))}">
										<a href="reversalOrders.do?orderId=${ mainOrderId }" onclick="return window.confirm('  手机号码： ${mobile}；面额 :${goodsValue }  您确定要订单冲正吗？')">订单冲正</a>
									</c:when>
									<c:when
										test="${( state==3|| state==0)&& orderType==1&&sessionScope.user.usergroup.id<=2}">
										<a href="cancelOrderExe.do?orderId=${ mainOrderId }" onclick="return window.confirm('  手机号码： ${mobile}；面额 :${goodsValue }  您确定要取消订单吗？')">取消订单</a>
										<a href="cancelOrderNoReturnExe.do?orderId=${ mainOrderId }" onclick="return window.confirm('  手机号码： ${mobile}；面额 :${goodsValue }  您确定要取消订单(不反额度)吗？')">取消订单(不反额度)</a>
									</c:when>
									<c:when
										test="${( state==2|| state==255)&& orderType==1&&sessionScope.user.usergroup.id<=2}">
										<a href="dealOrders.do?orderId=${ mainOrderId }" onclick="return window.confirm(' 手机号码： ${mobile}；面额 :${goodsValue }  您确定要强制订单成功吗？')">强制订单成功</a>
									</c:when>
								</c:choose>
								<c:if test="${ state==2&& isNeedManual==1&& isTerminal==1&&sessionScope.user.usergroup.id<=2}">
									<a href="page!manualOrder.do?orderId=${ mainOrderId }" onclick="return window.confirm('  手机号码： ${mobile}；面额 :${goodsValue }  您确定要人工充值吗？')">人工充值</a>
								</c:if>
                                </td>
                                  -->
                                 <td>${goodsName }</td>
                                 <td>
                                   <c:choose>
                                      <c:when test="${state==-1}">冲正</c:when>
                                      <c:when test="${state==0}">申请处理</c:when>
                                      <c:when test="${state==1}">处理成功</c:when>
                                      <c:when test="${state==2}">处理失败</c:when>
                                      <c:when test="${state==3}">处理中</c:when>
                                      <c:when test="${state==5}">处理中</c:when>
                                      <c:when test="${state==255}">用户取消</c:when>
                                   </c:choose>
                              </td>
                                 <td>
                                     <c:choose>
                                      <c:when test="${reversalState==-1 }">未冲正</c:when>
                                      <c:when test="${reversalState==0 }">处理中</c:when>
                                      <c:when test="${reversalState==1 }">成功</c:when>
                                      <c:when test="${reversalState==2 }">失败</c:when>
                                      <c:when test="${reversalState==5 }">处理中</c:when>
                                   </c:choose>
                                 </td>
                                 <td>${totalMoney }</td>
                                 <td>${goodsNo }</td>
                                <td>
                                <!-- 
                                 <a href="javascript:void(0);"onclick="showOrder('${mainOrderId}')">
                                 -->
                                  ${mainOrderId }
                                  <!-- 
                                  </a>
                                   -->
                                   </td>
                                 <!-- 
                                 <td>${reversalTime } </td>
                                 <td>${cBalance }</td>
                                 <td>${commission }</td>
                                  -->
                                 
                              </tr>
                              </s:iterator>
                           </table>
                         
                           
                    </div>
                   </div>
                 </div>
             </div>
  
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

