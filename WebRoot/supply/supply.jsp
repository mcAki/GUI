<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function showUserForSupply(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/users/list!listUserForSupply.do?";
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
  <body style="min-width: 700px;">
  
  <%@ include file="../common/incBanner.jsp" %>
  <%--
  
  	<s:form name="strutsForm" action="doAdd" namespace="/supply" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="supply.supplyId"></s:hidden>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>添加供货商</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td width="105" align="right">供货商名称:</td>
  	    <td align="left"><s:textfield name="supply.supplyName"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">选择供货商类型:</td>
  	    <td align="left"><s:select name="supply.supplyType" list="#{ 1:'自有', 2: '在线'}" listKey="key" listValue="value"/> </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">选择销售类型:</td>
  	    <td align="left"><s:select name="supply.sellType" list="#{ 1:'空中充值', 2: '卡密'}" listKey="key" listValue="value"/> </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">联系人:</td>
  	    <td align="left"><s:textfield name="supply.contactPeople"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">地址:</td>
  	    <td align="left"><s:textfield name="supply.address"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">电话:</td>
  	    <td align="left"><s:textfield name="supply.mobile"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >描述:</td>
  	   <td colspan="4" align="left"><s:textarea name="supply.desc" cssClass="txtW5" rows="4"></s:textarea></td>
       
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
							添加供货商
						</p>

					</div>
					<div class="modify">
                         <center>
                           <s:form name="strutsForm" action="doAdd" namespace="/supply" method="post">
						  	<center><s:fielderror></s:fielderror></center>
						  	<s:token/>
						  	<s:hidden name="supply.supplyId"></s:hidden>
						  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
						  	  <tr>
						  	    <td colspan="5"></td>
						      </tr>
						      
						  	  <tr>
						  	    <td width="105" align="right">供货商名称:</td>
						  	    <td align="left"><s:textfield name="supply.supplyName"/></td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	    <td width="105" align="right">选择供货商类型:</td>
						  	    <td align="left"><s:select name="supply.supplyType" list="#{ 1:'自有', 2: '在线'}" listKey="key" listValue="value"/> </td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	    <td width="105" align="right">选择销售类型:</td>
						  	    <td align="left"><s:select name="supply.sellType" list="#{ 1:'空中充值', 2: '卡密'}" listKey="key" listValue="value"/> </td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	    <td width="105" align="right">联系人:</td>
						  	    <td align="left"><s:textfield name="supply.contactPeople"/></td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	    <td width="105" align="right">地址:</td>
						  	    <td align="left"><s:textfield name="supply.address"/></td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	    <td width="105" align="right">电话:</td>
						  	    <td align="left"><s:textfield name="supply.mobile"/></td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	   <td align="right" >描述:</td>
						  	   <td colspan="4" align="left"><s:textarea name="supply.desc" cssClass="txtW5" rows="4"></s:textarea></td>
						       
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

