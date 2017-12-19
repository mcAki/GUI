<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
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
			var url="${pageContext.request.contextPath}/supply/list!listSupplyForLog.do?";
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
  <%--
  
  	<s:form name="strutsForm" action="doAdd" namespace="/spplyscorelog" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>评分日志</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td width="105" align="right">评分人名称:</td>
  	    <td align="left"><s:textfield name="spplyscorelog.users.userName" readonly="true"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
		<td width="145" align="right">
			供货商:
		</td>
		<s:hidden name="supplyId" id="userId"></s:hidden>
		<td align="left" class="nowrap">
			<s:textfield name="spplyscorelog.supplyName"
				id="userName" readonly="true" />
			<input icon="icon-btncom" type="button" value="选择"
				onclick='showSupplyForLog();'/>
		</td>
		<td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	 </tr>
	 
	 <tr>
  	    <td align="right">评分:</td>
  	    <td align="left"><s:select cssClass="txtW2" name="score" list="%{#{0:'0分',1:'1分',2:'2分',3:'3分',4:'4分',5:'5分',6:'6分',7:'7分',8:'8分',9:'9分',10:'10分'}}"/></td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >描述:</td>
  	   <td colspan="4" align="left"><s:textarea name="spplyscorelog.desc" cssClass="txtW5" rows="4"></s:textarea></td>
       
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
   --%>
   <div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<p class="jjcx">
							评分日志
						</p>

					</div>
					<div class="modify">
					  <center>
					    <s:form name="strutsForm" action="doAdd" namespace="/spplyscorelog" method="post">
						  	<center><s:fielderror></s:fielderror></center>
						  	<s:token/>
						  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
						  	  <tr>
						  	    <td colspan="5"></td>
						      </tr>
						      
						  	  <tr>
						  	    <td width="105" align="right">评分人名称:</td>
						  	    <td align="left"><s:textfield name="spplyscorelog.users.userName" readonly="true"/></td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
								<td width="145" align="right">
									供货商:
								</td>
								<s:hidden name="supplyId" id="userId"></s:hidden>
								<td align="left" class="nowrap">
									<s:textfield name="spplyscorelog.supplyName"
										id="userName" readonly="true" />
									<input icon="icon-btncom" type="button" value="选择"
										onclick='showSupplyForLog();'/>
								</td>
								<td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
							 </tr>
							 
							 <tr>
						  	    <td align="right">评分:</td>
						  	    <td align="left"><s:select cssClass="txtW2" name="score" list="%{#{0:'0分',1:'1分',2:'2分',3:'3分',4:'4分',5:'5分',6:'6分',7:'7分',8:'8分',9:'9分',10:'10分'}}"/></td>
						  	    <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	   <td align="right" >描述:</td>
						  	   <td colspan="4" align="left"><s:textarea name="spplyscorelog.desc" cssClass="txtW5" rows="4"></s:textarea></td>
						       
						      </tr>
						      
						      <tr>
						  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
						        </td>
						  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
						        <td>&nbsp;</td>
						      </tr>
								
						  </table>
						  </s:form>
					  
					  </center>
					   
					</div>
				</div>
			</div>
		</div>	
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

