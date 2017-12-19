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
  <body style="min-width: 800px;">
  
  <%@ include file="../common/incBanner.jsp" %>
  
  <%--
  	<s:form name="strutsForm" action="doAdd" namespace="/supplyInterface" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="supplyInterface.id" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>增加供货商对应商品</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>
		<td width="145" align="right">
			供货商:
		</td>
		<s:hidden name="supplyInterface.supply.id" id="userId"></s:hidden>
		<td align="left" class="nowrap">
			<s:textfield name="supplyInterface.supplyName"
				id="userName" readonly="true" />
			<input icon="icon-btncom" type="button" value="选择"
				onclick='showSupplyForLog();'/>
		</td>
		<td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	 </tr>
	  --%>
      <%--
      <tr>
       <td align="right">选择商品:</td>
  	   <td align="left"><s:select name="supplyInterface.goods.goodsId" list="goodsList" listKey="goodsId" listValue="goodsName" /></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
       --%>
       <%--
      <tr>
       <td align="right">商品类型:</td>
  	   <td align="left"><s:select id="goods1" name="goods.goodsType.goodsTypeId" list="goodsTypes" listKey="goodsTypeId" listValue="goodsTypeName" onchange="ajax(this)"/></td>
       <td align="right"></td>
  	   <td align="left">
  	   <select id="goods2" name="goodsId">
            <option value="${goodsId}">--选择商品--</option>
  	   </select>
  	   </td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">进货价:</td>
  	   <td align="left"><s:textfield name="supplyInterface.stockPrice"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">销售价:</td>
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
  	   <td align="left"><s:radio name="supplyInterface.canReverse" list="%{#{0:'禁用',1:'可用'}}" listKey="key" value="1" /></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>

      <tr>
       <td align="right">客户来源可用:</td>
  	   <td align="left"><s:select name="supplyInterface.clientType" list="%{#{1:'所有可用',2:'系统专用',3:'新泛联专用'}}" listKey="key" value="1" /></td>
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
   --%>
   <div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<p class="jjcx">
							增加用户组
						</p>

					</div>
					<div class="modify">
                         <center>
                         <s:form name="strutsForm" action="doAddUsergroup" namespace="/users" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="usergroup.id" />
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>
       <td align="right">用户组名称:</td>
  	   <td align="left"><s:textfield name="usergroup.groupName"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">用户组类型:</td>
  	   <td align="left"><s:select name="usergroup.groupType" list="%{#{2:'员工',3:'查询',4:'一级商户',5:'二级商户'}}" listKey="key" value="2" /></td>
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
                          
                         </center>
					</div>
				</div>
			</div>
		</div>	
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>