<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function showArea(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/area/list.do?";
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
  <!-- 
  
  	<s:form name="strutsForm" action="doAdd" namespace="/goods" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="goods.goodsId" />
  	<s:hidden name="supplyInterface.id" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>增加商品</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td align="right">所属类别:</td>
  	    <td align="left"><s:select cssClass="txtW2" name="goods.goodsFlag" list="%{#{0:'===请选择类别===',10:'移动直充 ',11:'电信直充',12:'联通直充',20:'充值卡'}}"/></td>
  	    <td align="right">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">所属类型:</td>
  	    <td align="left"><s:select cssClass="txtW2" name="goods.goodsType.goodsTypeId" list="goodsTypes" listKey="goodsTypeId" listValue="goodsTypeName"/></td>
  	    <td align="right">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
       <tr>
		<td width="145" align="right">
			所属地区:
		</td>
		<s:hidden name="provinceCode" id="userId"></s:hidden>
		<td align="left" class="nowrap">
			<s:textfield name="goods.province"
				id="userName" readonly="true" />
			<input icon="icon-btncom" type="button" value="选择"
				onclick='showArea();'/>
		</td>
		<td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	 </tr>
      
      <tr>
       <td align="right">商品名称:</td>
  	   <td align="left"><s:textfield name="goods.goodsName"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">面额:</td>
  	   	<td align="left" ><s:textfield name="goods.value"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td> 
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">对应91KA的商品id:</td>
  	   	<td align="left" ><s:textfield name="goods.kaCardId"/>(如没有可不填)</td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td> 
        <td>&nbsp;</td>
      </tr>
      
      
      <tr>
  	   <td align="right" >描述:</td>
  	   <td colspan="4" align="left"><s:textarea name="goods.description" cssClass="txtW5" rows="4"></s:textarea></td>
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
   -->
   <div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<p class="jjcx">
							增加商品
						</p>
					</div>
					<div class="modify">
					    <center>
					      	<s:form name="strutsForm" action="doAdd" namespace="/goods" method="post">
						  	<center><s:fielderror></s:fielderror></center>
						  	<s:token/>
						  	<s:hidden name="goods.goodsId" />
						  	<s:hidden name="supplyInterface.id" />
						  	<p>&nbsp;</p>
						  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
						  	  <tr>
						  	    <td colspan="5"></td>
						      </tr>
						      
						  	  <tr>
						  	    <td align="right">所属类别:</td>
						  	    <td align="left"><s:select cssClass="txtW2" name="goods.goodsFlag" list="%{#{0:'===请选择类别===',10:'移动直充 ',11:'电信直充',12:'联通直充',13:'游戏直充',20:'充值卡',30:'广东电信单一缴费',31:'广东广东电信宽带',32:'广东电信综合缴费'}}"/></td>
						  	    <td align="right">&nbsp;</td>
						        <td align="left">&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	    <td align="right">所属类型:</td>
						  	    <td align="left"><s:select cssClass="txtW2" name="goods.goodsType.goodsTypeId" list="goodsTypes" listKey="goodsTypeId" listValue="goodsTypeName"/></td>
						  	    <td align="right">&nbsp;</td>
						        <td align="left">&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						       <tr>
								<td width="145" align="right">
									所属地区:
								</td>
								<s:hidden name="provinceCode" id="userId"></s:hidden>
								<td align="left" class="nowrap">
									<s:textfield name="goods.province"
										id="userName" readonly="true" />
									<input icon="icon-btncom" type="button" value="选择"
										onclick='showArea();'/>
								</td>
								<td>&nbsp;</td>
						        <td>&nbsp;</td>
						        <td>&nbsp;</td>
							 </tr>
						      
						      <tr>
						       <td align="right">商品名称:</td>
						  	   <td align="left"><s:textfield name="goods.goodsName"/></td>
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						        <td align="right">面额:</td>
						  	   	<td align="left" ><s:textfield name="goods.value"/></td>
						        <td>&nbsp;</td>
						  	    <td>&nbsp;</td> 
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						        <td align="right">对应91KA的商品id:</td>
						  	   	<td align="left" ><s:textfield name="goods.kaCardId"/>(如没有可不填)</td>
						        <td>&nbsp;</td>
						  	    <td>&nbsp;</td> 
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						        <td align="right">对应91KA商品基数:</td>
						  	   	<td align="left" ><s:textfield name="goods.kaBaseNum"/>(如没有可不填)</td>
						        <td>&nbsp;</td>
						  	    <td>&nbsp;</td> 
						        <td>&nbsp;</td>
						      </tr>
						      
						      
						      <tr>
						  	   <td align="right" >描述:</td>
						  	   <td colspan="4" align="left"><s:textarea name="goods.description" cssClass="txtW5" rows="4"></s:textarea></td>
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

