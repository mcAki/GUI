<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function showTree(hasChildNodeFlag){
			//window.location='${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag;
			t = screen.Height / 2 - 225;
	        l = screen.width / 2 - 300;
	        h = 500;
	        w = 400;
	        
	        //window.open('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag=true', 'showMessage', feature);
			
			var features="resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth=400px;dialogHeight=500px";
			var obj = new Object();
			obj.name="51js";
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			
			//alert(returnVal);
			//document.getElementById("districtName").value=returnVal.districtName; 
			
            //document.getElementById("districtId").value=returnVal.districtId; 
            if(!$.isEmptyObject(returnVal)){
            	$("#districtId").val(returnVal.districtId);
            	$("#districtName").val(returnVal.districtName);
			}
		}
	
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  <%--
  
  	<s:form name="strutsForm" action="doAdd" namespace="/goodsType" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="goodsType.goodsTypeId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>增加商品类型</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
       <tr>
  	    <td align="right">所属类别:</td>
  	    <td align="left"><s:select cssClass="txtW2" name="goodsType.goodsFlag" list="%{#{0:'===请选择类别===',1:'直充',2:'卡密'}}"/></td>
  	    <td align="right">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">商品类型名称:</td>
  	   <td align="left"><s:textfield name="goodsType.goodsTypeName"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >所有面额:(用","区分.例如:空中充值则填写30,50,100,300)</td>
  	   <td colspan="4" align="left"><s:textarea name="goodsType.values" cssClass="txtW5" rows="4"></s:textarea></td>
      </tr>
      
      <tr>
  	   <td align="right" >描述:</td>
  	   <td colspan="4" align="left"><s:textarea name="goodsType.description" cssClass="txtW5" rows="4"></s:textarea></td>
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
							增加商品类型
						</p>

					</div>
					<div class="modify">
                           <center>
                           
                            <s:form name="strutsForm" action="doAdd" namespace="/goodsType" method="post">
						  	<center><s:fielderror></s:fielderror></center>
						  	<s:token/>
						  	<s:hidden name="goodsType.goodsTypeId" />
						  	<p>&nbsp;</p>
						  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
						  	  <tr>
						  	    <td colspan="5"></td>
						      </tr>
						      
						       <tr>
						  	    <td align="right">所属类别:</td>
						  	    <td align="left"><s:select cssClass="txtW2" name="goodsType.goodsFlag" list="%{#{0:'===请选择类别===',1:'直充',2:'卡密'}}"/></td>
						  	    <td align="right">&nbsp;</td>
						        <td align="left">&nbsp;</td>
						        <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						       <td align="right">商品类型名称:</td>
						  	   <td align="left"><s:textfield name="goodsType.goodsTypeName"/></td>
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						      </tr>
						      
						      <tr>
						  	   <td align="right" >所有面额:(用","区分.例如:空中充值则填写30,50,100,300)</td>
						  	   <td colspan="4" align="left"><s:textarea name="goodsType.values" cssClass="txtW5" rows="4"></s:textarea></td>
						      </tr>
						      
						      <tr>
						  	   <td align="right" >描述:</td>
						  	   <td colspan="4" align="left"><s:textarea name="goodsType.description" cssClass="txtW5" rows="4"></s:textarea></td>
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

