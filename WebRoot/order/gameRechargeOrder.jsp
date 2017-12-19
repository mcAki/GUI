<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="${jsUrl}" language="javascript" charset="gb2312"></script>
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
		
		function sub(){
			if(at_fs()){
				strutsForm.submit();
			}
		}
	</script>
  </head>  
  <body onload="at_fi(${kaBaseNum})">
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="doAddGameRechargeOrder" namespace="/order" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="mainorder.mainOrderId" />
  	<s:hidden name="orderdetail.orderDetailId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
  		<c:import url="${formUrl}" charEncoding="gb2312"/>
  		<s:hidden name="gameId"/>
  		<s:hidden name="autoGameId"/>
  		<tr>
      	<td align="right" >输入密码:</td>
	 	<td align="left"><s:password  name="businessPassword" id="12" cssStyle="font-size:30px;"/></td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
      </tr>
  		<tr>
	  		<td colspan="4">
	  		重要说明：
1. Q币充值无冲正功能，请谨慎操作！
2. 如果用户的QQ号码属于QQ行号码或靓号，且号码因欠费而已被回收将无法充值；如果号码已欠费但还未被回收，必须在充值成功后及时续费才能登录使用。QQ号码一旦被回收，其个人帐户里的Q币余额也将一并收回，不予返还。请代理商务必对用户说明。
3. 充值成功后，可以登录my.qq.com，查询个人帐户余额，并用个人帐户来支付各种QQ增值服务，如：QQ秀、QQ交友中心、QQ会员服务等等。
	  		</td>
  		</tr>
  		<tr><td colspan="4" align="center"><input type="button" value="提交" onclick="sub()"/></td></tr>
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
	
  </body>
</html>

