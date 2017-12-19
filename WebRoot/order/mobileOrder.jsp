<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.sys.volunteer.pojo.*"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>		
  <link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
 <%@ include file="../common/incBanner.jsp" %>
		<%@ include file="../common/incHead.jsp"%>
		<%@ include file="../common/incFooter.jsp" %>
		<script type="text/javascript" src="../js/divselect.js"></script>           
<script type="text/javascript" src="../js/calendar/calendar.js"></script>
<script type="text/javascript" src="../js/calendar/lang/en.js"></script>
<link rel="stylesheet" type="text/css" href="../js/calendar/jscal2.css"/>
<link rel="stylesheet" type="text/css" href="../js/calendar/border-radius.css"/>
<link rel="stylesheet" type="text/css" href="../js/calendar/win2k.css"/>
	<style  type="text/css">
	.form_tb {
		height: 30px;
		line-height: 30px;
		font-size: 30px;
		font-weight: normal;
		color: #333;
		background-color: #FFF;
		text-align: center;
		border: double 3px #446633;
		border-collapse: collapse;
	}
	/*==文本框====================*/
	.z-txt{
		color:#334433;
		/*margin:+1px +1px 0px +1px;*/
		border-left:dotted 1px #669966;
		border-right:dotted 1px #669966;
		border-top:dotted 1px #669966;
		border-bottom:solid 1px #006600;
		height:30px;
		line-height:30px;
		padding:1px 5px;
		background-color:#FAFFFA;
	}
	.table_mobileOrder{
	    display: none;
	}
	
	
	</style>
	<script type="text/javascript">
		function showSupplyInterface(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/supplyInterface/list!listInterfaceForSupply.do?";
			//为了处理缓存加入随机时间
			url += "&date=" + new Date().getTime();
			
			//弹出参数
			var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth="+ width +"px;dialogHeight=" + height + "px";
			
			//创建传入参数
			var obj = new Object();
			obj.name = "参数";
			var returnVal = window.showModalDialog(url,obj,"dialogWidth=600px;dialogHeight=500px");
			if(!$.isEmptyObject(returnVal)){
				$("#userId").val(returnVal.uid);
				$("#userName").val(returnVal.username);
			}
		}
		function showOrder(mainOrderId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/mainOrderShowModal.do?mainOrderId='
						+ mainOrderId , 700, 600);
		}
	
		
		function enterChange(){
			var objId = event.srcElement.id;
			//alert(objId);
			if (window.event.keyCode==13) {//回车键处理
				if(objId==12){
					//alert((objId)+"submit");
					strutsForm.submit();
				}else{
					//alert((objId+1)+"onfocus");
					var id = Number(objId);
					//alert(id+1);
					document.getElementById(id+1).focus();
				}
			}
			
		}
		/*
		function showValue(){
			var val = document.getElementById("10");
			for(i=0;i <document.strutsForm.price.length;i++) { 
				if(document.strutsForm.price[i].checked){
					val.value = document.strutsForm.price[i].value;
					document.getElementById(11).focus();
					return;
				}
			} 
		}
		
			
			document.onkeydown=function(evt){
					if(evt.keyCode==13){
					     return;
					}
		     }
		*/
		
		 function mobileKeyDown(){
		      $(".move").show();
		       var mobile = $("#10").val();
		        if(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile)&&mobile.length==11){
			      $.post( "/MPRS/order/showMobileGSD.do", {mobile:mobile},  function(data){
					 if(data.flag==1){
						 $(".move").html(data.province+"-"+data.city+"-"+data.businessType);
						 $(".gou").show();
						 $(".table_mobileOrder").show();
						 document.getElementById('11').select();
					 }else{
					     $(".move").html("未知的手机号码，请联系客服代表");
					 }
					},'json')
		     }else{
		        if(mobile.length<=0){
		          $(".move").html("请输入手机号码");
		        }else{
		        $(".move").html("手机号码输入不正确");
		        }
		        //document.getElementById("10").focus();
		        //document.getElementById("10").value = "";  
		        $(".gou").hide();
		         $(".table_mobileOrder").hide();
		        return false;
		     }
		 }
		 
		$(document).ready(function(){
		
		initFocus();
		
		    $("#10").change(function(){
		      mobileKeyDown()
		    });
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
				   case 1:
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
		function showAttribution(){
		   var mobile = document.getElementById("10").value;
		     if(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile)){
			     window.location.href = "${pageContext.request.contextPath}/order/showMobileGSD.do?mobile="+mobile;
		     }else{
		        $(".move").attr("html","手机号码输入不正确");
		        document.getElementById("10").focus();
		        document.getElementById("10").value = "";  
		         
		        return false;
		     }
		}	
		
	   function initFocus(){
          document.getElementById("10").focus();
          				mobileKeyDown();
          
       }
     function checkCanreverse(){
         var mobile = $("#10").val();
         var amount = $("#11").val();
         if(mobile!=null&&amount!=""){
	        $.post("/MPRS/order/checkSupplyCanreverse.do",{mobile:mobile,priceShow:amount},
	              function (data){
		              if(data!=""){
			              $("#show_mes_p").show();
			               $("#show_mes_p").html(data);
		               }else{
		               	$("#show_mes_p").hide();
		               }
	              },'text');
         }else{
	                  $("#show_mes_p").html("未输入金额");
         }
     }
		
	</script>
	
  </head>  
  <body  style="min-width:680px;">
  
 
   
   <!-- 
  	<s:actionerror/>
  	<s:form name="strutsForm" id="strutsForm" action="doAddMobileOrderExe" namespace="/order" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:hidden name="mainorder.mainOrderId" />
  	<s:hidden name="orderdetail.orderDetailId" />
  	<s:token/>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
      
  	<table width="900" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>空中充值订单</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>
      	<td align="right" >账户余额:</td>
	 	<td align="left"><s:textfield name="useableAccount" readonly="true" cssStyle="font-size:30px;"/></td>
       	<td>&nbsp;</td>
       	<td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
      	<td align="right" >选择金额:</td>
	 	<td align="left"><s:radio name="price" list="%{#{30:'30元',50:'50元',100:'100元',200:'200元',300:'300元',500:'500元'}}" listKey="key" onclick="showValue();"/></td>
       	<td>&nbsp;</td>
       	<td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
       
      
     
      <tr>   
      	<td align="right" >请填写手机:</td>
	 	<td align="left"><s:textfield name="mobile" id="10" onkeydown="enterChange()" onchange="return showAttribution()" cssStyle="font-size:30px;"/></td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
      </tr>

      <tr>
	 	<s:if test="mobile!=null">
        <td align="right" ></td> 
        <td align="left">
	 	      ${areaCode.province}-${areaCode.city}&nbsp;
	 	      <s:if test="#areaCode.businessType==1">
	 	                           移动
	 	      </s:if>
	 	      <s:elseif test="#areaCode.businessType==2">
	 	                          电信
	 	      </s:elseif>
	 	      <s:else>
	 	                          联通
	 	      </s:else>
        </td>
        <td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	</s:if>
      </tr>
	 	
      <tr>
      	<td align="right" >金额:</td>
	 	<td align="left"><s:textfield id="11" name="priceShow" onkeydown="enterChange()" cssStyle="font-size:30px;"/>元</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
      </tr>
      
      <tr>
      	<td align="right" >输入密码:</td>
	 	<td align="left"><s:password  name="userPassword" id="12" onkeydown="enterChange()" cssStyle="font-size:30px;"/></td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="充值"></s:submit>
        </td>
  	    <td align="center">
  	     <font size="3">
  	              余额:${useableAccount}
  	     </font>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
      </tr>
		
  </table>
  </s:form>
      -->
  <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
              <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                     <div class="cxjj fixed">
                           <p style=" color: #669999; display: block; font-size: 14px; font-weight: bold;   padding-left: 20px;">全国话费充值</p>
                           <p style=" color: #669999; display: block; font-size: 14px; font-weight: bold;   padding-left: 20px;">可为全国<span style="color:blue;"> 移动、电信、联通 </span>手机号码进行充值（含<span style="color:blue;">10元、20元</span>面额）。 </p>
                     </div>
                   
                     <div class="country communi" style="width:680px;">
                             <s:form name="strutsForm" id="strutsForm" action="doAddMobileOrderExe" namespace="/order" method="post">
                            	<s:hidden name="mainorder.mainOrderId" />
                            	<s:hidden name="orderdetail.orderDetailId" />
                             <s:token/>
                                        <table width="100%" height="100" border="0" cellpadding="0" cellspacing="0">
                                        <%--
                                             <tr>
                                                <td align="right">账户余额：</td>
                                                <td><input style="width:300px;" value="${useableAccount }" onkeydown="if(event.keyCode==13) {document.getElementById('10').select();event.keyCode=0;return false}" readonly="true" class="czhm" /> </td>
                                             </tr>
                                         --%>
                                             <tr>
                                                <td width="18%" align="right" class="font_order"> 充值手机：</td>
                                                <td width="82%" align="left" valign="middle"><input  id="10" name="mobile" type="text" onkeydown="if(event.keyCode==13) {mobileKeyDown();event.keyCode=0;return false;}" onkeyup="this.value=this.value.replace(/\D/g,'');mobileKeyDown();" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="${mobile }" class="czhm" />
                                               <span class="gou" style="display: none;"></span>
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
												  </script></td>
                                              </tr>
                                             <tr>
                                                <td width="18%" align="right">&nbsp; </td>
                                                <td width="82%" colspan="93%"><p class="move" style="display: none; padding-left:10px;text-align: center;"></p>
										 	</td>  
                                              </tr>
                                        </table>
                                        
                                        <table width="100%" height="70" border="0" cellpadding="0" cellspacing="0" class="table_mobileOrder">
                                            <tr>
                                                 <td width="18%" class="font_order" align="right">充值金额：</td>
                                                 <td width="82%" colspan="7"><input  id="11" onkeydown="if(event.keyCode==13) {document.getElementById('pwd').select();event.keyCode=0;return false}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="priceShow"  type="text"  value="${priceShow }" class="czhm2 czhm"  /></td>
                                              </tr>
                                        </table>
                                        <%--
                                        
                                        <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0" class="table_mobileOrder">
                                              <tr>
                                                <td width="18%">&nbsp;</td>
                                                <td width="3%" align="center"><input class="charge_radio_" name="radio" type="radio" value="30" checked="checked" /></td>
                                                <td width="4%">30元</td>
                                                <td width="3%" align="center"><input class="charge_radio_" name="radio" type="radio" value="50" /></td>
                                                <td width="4%">50元</td>
                                                <td width="3%" align="center"><input class="charge_radio_" name="radio" type="radio" value="100" /></td>
                                                <td width="5%">100元</td>
                                                <td width="3%" align="center"><input class="charge_radio_" name="radio" type="radio" value="200" /></td>
                                                <td width="5%">200元</td>
                                                <td width="3%" align="center"><input class="charge_radio_" name="radio" type="radio" value="300" /></td>
                                                <td width="5%">300元</td>
                                                <td width="3%" align="center"><input class="charge_radio_" name="radio" type="radio" value="500" /></td>
                                                <td width="41%">500元</td>
                                              </tr>
                                        </table>
                                        <table width="100%" height="20" border="0" cellpadding="0" cellspacing="0" class="table_mobileOrder">
                                              <tr>
                                                <td width="18%">&nbsp;</td>
                                                <td width="3%" align="center"><input class="charge_radio_" name="radio" type="radio" value="0" /></td>
                                                <td colspan="79%">手动输入面额</td>
                                              </tr>
                                         </table>--%>
                                         
                                        <table width="100%"border="0" cellpadding="0" cellspacing="0" class="table_mobileOrder">
                                              <tr>
                                                <td width="18%">&nbsp;</td>
                                                <td colspan="82%"><p id="show_mes_p" style="line-height:30px;text-align:center; font-size:14px;width:287px;background-color:#E4F5FF; border: 1px solid #A7D0FE;color:#666999" >
                                                	手动输入<font color="red"> <span class="amount_class" id=""> 10 </span>,<span>20</span>,<span>30</span>,<span>50</span>,
                                                	<span>100</span>,<span>200</span>,<span>300</span>,<span>500</span></font>元
                                                	</p>
                                                	
                                                <%--
                                                                                                                                您选择充值面额是：<span id="show_charge_radio" type="text" style="color: red;">30</span> 元</p></td>
                                                 --%>
                                              </tr>
                                        </table>
                                      <br/>
                                      <table width="100%"border="0" cellpadding="0" cellspacing="0" class="table_mobileOrder">
                                              <tr>
                                                <td width="18%">&nbsp;</td>
                                                <td colspan="82%"><p id="show_mes_p" style="line-height:30px;text-align:center; font-size:14px;width:287px;background-color:#E4F5FF; border: 1px solid #A7D0FE;color:#666999" >
                                                	<font color="red"> 10元,20元面额以及广东省外号码充值无法冲正</font>
                                                	</p>
                                                	
                                                <%--
                                                                                                                                您选择充值面额是：<span id="show_charge_radio" type="text" style="color: red;">30</span> 元</p></td>
                                                 --%>
                                              </tr>
                                        </table>
                                      <br/>
                                        <table width="100%" height="40" border="0" cellpadding="0" cellspacing="0" class="table_mobileOrder">
                                              <tr>
                                                    <td width="18%" align="right" class="font_order">交易密码：</td>
                                                    <td colspan="7"><input id="pwd" onkeydown="if(event.keyCode==13) {strutsForm.submit();event.keyCode=0;return false;}" name="businessPassword" type="password"  value="" class="czhm" /></td>
                                             </tr>
                                      </table>
                                        <table width="70%" height="60" border="0" cellpadding="0" cellspacing="0" class="table_mobileOrder" >
                                              <tr>
                                              <td colspan="4" align="right">
                                          <%-- 账户余额：${useraccount.balance } --%>  &nbsp;  
                                              </td>
                                            <td colspan="4" align="center"><button type="submit"  class="goumai11">充值</button> 
                                            <button  type="reset"  class="bt_clear"> 清空</button>
                                            <!-- 
                                             -->
                                            </td>
                                      </tr>
                                      </table>
                             </s:form>
                     </div>
                     <%--
                      <div class="check1 check" style="position: absolute;left:60%; top: 100px; overflow: hidden;">
                                    <strong>温馨提示</strong>
                                   
                                   <p>*根据省电信的要求，为防止恶意冲正，从即日起，后付费天翼手机用户冲正时限由原24小时改为6小时,超过6个小时的记录无法冲正，
									并且单笔充值金额超过100元（不含100元）的记录无法由代理商自行冲正，如需冲正请于充值后6小时内联系我司，超过6小时无法受理，
									冲正请求由我司代为发起，冲正结果稍后由我司人员进行通知。</p>
								   <p>因汕头、潮州、揭阳，中山，清远，阳江这6个地市的移动公司政策性问题要求停止充值。请贵商户今后停止提交这3个地市的移动充值；
									现移动已再次警告，再被查到会做相关处罚甚至停止广东移动的充值业务。为了贵商户跟我司的业务正常开展，望贵司协助配合，勿再提交。</p>
                                   <p>*每日23：59至次日00：02系统维护，请您避开此时段充值！</p>
                            </div>
                      --%>
                            <div class="tishi_id">
                                 <strong>温馨提示 :</strong>
                                   <p>
                                     1. 全国充值到账时间一般情况下为1-15分钟,请各位代理耐心等侯，如长时间不到账，建议先开单据证明给客户并收取金额。 <br/>
                                     <span style="color: red"> 2. 特别提醒：10元,20元面额以及广东省外号码充值无法冲正，请各位代理在充值前务必仔细核对要充值的号码。</span>
                                   </p>
                            </div>
                       <div style="border:1px;border-style: solid;border-color: #ccc;background-color:#D5EAF8;border-radius: 5px 5px 5px 5px; ">
                           <strong style="padding-left:30px;">【最近五笔交易记录】</strong>
                            <img id="refresh_button" style="cursor: pointer;margin-left:300px;vertical-align:middle;margin-top:5px;margin-bottom:5px;" src="/MPRS/img/refresh_button.jpg"/> 
                       </div>
                                <p class="zhuyi_p" >订单成功与否以 <a href="/MPRS/order/list.do" style="text-decoration:underline;color:red;" >订单查询</a> 的最终状态为准，处理中的订单切勿退款或补充，如有疑问请联系我司客服QQ或致电:4000307517查询。</p>
                    <div class="payment modify" style="margin:0;">
                            <table class="list_tb_002" style="min-width: 680px;">
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
										<a href="reversalOrders.do?orderId=${ mainOrderId }" onclick="return window.confirm(' 手机号码： ${mobile}；面额 :${goodsValue }   您确定要订单冲正吗？')">订单冲正</a>
									</c:when>
									<c:when
										test="${( state==3|| state==0)&& orderType==1&&sessionScope.user.usergroup.id<=2}">
										<a href="cancelOrderExe.do?orderId=${ mainOrderId }" onclick="return window.confirm('  手机号码： ${mobile}；面额 :${goodsValue }   您确定要取消订单吗？')">取消订单</a>
										<a href="cancelOrderNoReturnExe.do?orderId=${ mainOrderId }" onclick="return window.confirm('  手机号码： ${mobile}；面额 :${goodsValue }   您确定要取消订单(不反额度)吗？')">取消订单(不反额度)</a>
									</c:when>
									<c:when
										test="${( state==2|| state==255)&& orderType==1&&sessionScope.user.usergroup.id<=2}">
										<a href="dealOrders.do?orderId=${ mainOrderId }" onclick="return window.confirm(' 手机号码： ${mobile}；面额 :${goodsValue }   您确定要强制订单成功吗？')">强制订单成功</a>
									</c:when>
								</c:choose>
								<c:if test="${ state==2&& isNeedManual==1&& isTerminal==1&&sessionScope.user.usergroup.id<=2}">
									<a href="page!manualOrder.do?orderId=${ mainOrderId }" onclick="return window.confirm('  手机号码： ${mobile}；面额 :${goodsValue }   您确定要人工充值吗？')">人工充值</a>
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
                      
	
  </body>
</html>

