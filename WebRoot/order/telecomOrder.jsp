<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
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
				$("#userId").val(returnVal.uid);
				$("#userName").val(returnVal.username);
			}
		}
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="page!doChargeTelecom" namespace="/order" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
  	<center><s:fielderror></s:fielderror></center>
    <caption>广东电信产品缴费</caption>
    		<s:hidden name="mainorder.mainOrderId" />
  			<s:hidden name="orderdetail.orderDetailId" />
  	  <tr>
  	    <td colspan="5"></td>
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
		<td width="145" align="right">
			电信号码:
		</td>
		<td align="left" class="nowrap">
			<s:label name="resp.mobile"/><s:hidden name="mobile"></s:hidden>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	 </tr>
      
      <tr>
		<td width="145" align="right">
			付费类型:
		</td>
		<td align="left" class="nowrap"><s:label>
			<c:choose>
				<c:when test="${resp.payType==1 }">后付费</c:when>
				<c:when test="${resp.payType==2 }">预付费</c:when>
			</c:choose>
			</s:label>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	 </tr>
	 
	 <tr>
		<td width="145" align="right">
			号码余额:
		</td>
		<td align="left" class="nowrap">
			<fmt:formatNumber maxFractionDigits="2" value="${resp.cBalance/100 }"/>元
		</td>
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
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

