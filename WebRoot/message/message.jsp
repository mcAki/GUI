<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  	<link href="../css/criteria.css" rel="stylesheet" type="text/css">
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
	<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
		function showRecieveUser(){
		
		//计算弹出位置
		
		height = 500;
		width = 400;
		//barHeight=30;
		//top = (screen.Height/2) - (height/2) - barHeight;
		//left = (screen.width/2) - (width/2);
		

		//要查询的链接
		var url="${pageContext.request.contextPath}/users/list!listUserForSelectManager.do?";
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
		<s:form name="strutsForm" action="doAdd" namespace="/message" method="post">
			<center><s:fielderror></s:fielderror></center>
			<s:token/>
			<s:hidden name="userId" />
			<s:hidden name="teamId"></s:hidden>
		  	<p>&nbsp;</p>
		  	<p>&nbsp;</p>
		  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
		  		<tr>
		  			<td colspan="4">&nbsp;</td>
		  		</tr>
		  		
		  		<tr>
		  			<td align="right">发送人:</td>
		  			<td align="left"><s:textfield name="message.usersBySendId.userName" readonly="true"></s:textfield></td>
		  			<td width="145" align="right">消息接收人:</td>
					<s:hidden name="message.usersByRecieveId.userId" id="userId"></s:hidden>	
					<td align="left" class="nowrap"><s:textfield name="message.recieveName" id="userName" readonly="true"/>
					<input icon="icon-btncom" type="button" value="选择" onclick="showRecieveUser()"/>
					</td>
		  		</tr>
		  		
		  		<tr>
		  			<td align="right">消息主题:</td>
		  			<td colspan="3" align="left"><s:textfield name="message.title" cssClass="txtW5" rows="4"/></td>
		  		</tr>
		  		
		  		<tr>
		  			<td align="right">消息内容:</td>
		  			<td colspan="3" align="left"><s:textarea name="message.content" cssClass="txtW5" rows="4"/></td>
		  		</tr>
		  		
		  		
		  		<tr>
		  			<td colspan="4" align="center">
		  				<s:submit value="发送"></s:submit>&nbsp;
		  				<s:reset value="重置"></s:reset>&nbsp;
		  			</td>
		  		</tr>
			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp" %>
	</body>
</html>

