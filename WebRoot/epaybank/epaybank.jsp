<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function showSupplyForLog(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/supply/list!listSupplyForCardLib.do?";
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
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="authCharge" namespace="/epaybank" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>易票联账号支付</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td align="right">易票联账号:</td>
  	    <td align="left"><s:textfield name="payCardNo"  /></td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
		<td align="right">充值金额:</td>
        <td align="left"><s:textfield name="amount" /></td>  
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
       </tr>
      
      <tr height="300px">
  	   <td align="right">附带信息:</td>
  	   <td colspan="4" align="left"><s:textarea name="summary" cssClass="txtW5" rows="10"></s:textarea>
  	   </td>
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

