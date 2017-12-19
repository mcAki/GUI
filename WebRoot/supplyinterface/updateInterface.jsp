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
  	<s:form name="strutsForm" action="doUpdate" namespace="/supplyInterface" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="supplyInterface.id" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>修改供货商对应商品</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>
		<td width="145" align="right">
			供货商:
		</td>
		<td align="left" class="nowrap">
			<s:hidden name="supplyInterface.supply.id"></s:hidden>
			<s:textfield name="supplyInterface.supplyName" readonly="true" />
		</td>
		<td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	 </tr>
      
      <tr>
       <td align="right">选择商品:</td>
  	   <td align="left">
  	   		<s:hidden name="supplyInterface.goods.goodsId"></s:hidden>
  	   		<s:textfield name="supplyInterface.goodsName" readonly="true" />
  	   </td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">进货价:</td>
  	   <td align="left">
  	   	<s:hidden name="supplyInterface.state"></s:hidden>
  	   	<s:textfield name="supplyInterface.stockPrice"/>
  	   </td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">销售价:</td>
       <s:hidden name="supplyInterface.failedCount"></s:hidden>
  	   <td align="left" ><s:textfield name="supplyInterface.retailPrice"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">低库存警示:</td>
  	   	<td align="left" ><s:textfield name="supplyInterface.defaultAlarm"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td> 
        <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">可否冲正:</td>
  	   <td align="left"><s:radio name="supplyInterface.canReverse" list="%{#{0:'禁用',1:'可用'}}" listKey="key" /></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">客户来源可用:</td>
  	   <td align="left"><s:select name="supplyInterface.clientType" list="%{#{1:'所有可用',2:'系统专用',3:'新泛联专用'}}" listKey="key" /></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
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

