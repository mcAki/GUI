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
  	<s:form name="strutsForm" action="doAdd" namespace="/cardLib" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>卡密导入</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
       <tr>
		<td width="145" align="right">
			供货商:
		</td>
		<s:hidden name="cardLib.supply.id" id="userId"></s:hidden>
		<td align="left" class="nowrap">
			<s:textfield name="cardLib.supplyName"
				id="userName" readonly="true" />
			<input icon="icon-btncom" type="button" value="选择"
				onclick='showSupplyForLog();'/>
		</td>
		<td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	 </tr>
      
  	  <tr>
  	    <td align="right">选择对应商品:</td>
  	    <td align="left"><s:select cssClass="txtW2" list="goodsList" name="cardLib.goods.goodsId" listKey="goodsId" listValue="goodsName" ></s:select></td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr height="300px">
  	   <td align="right">卡密内容:</td>
  	   <td colspan="4" align="left"><s:textarea name="content" cssClass="txtW5" rows="10"></s:textarea>
  	   	<p>导入请严格按照以下格式:</p>
  	   	<p>有赠品时:卡号+空格+密码+空格+赠品</p>
  	   	<p>无赠品时:卡号+空格+密码</p>
  	   	<p>无卡号时:直接输入密码</p>
  	   	<p>每一组用","分隔</p>
  	   </td>
      </tr>
      
      <tr>
		<td align="right">有效期:</td>
        <td align="left"><s:textfield name="cardLib.expireTime"  readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>  
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

