<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
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
	
		//保存
	function gotoSave(operateCode) {
		var formname = document.getElementById("form1");
		//var path = ${pageContext.request.contextPath};
		switch(operateCode){
			case 1:
				formname.action = '${pageContext.request.contextPath}/supply/doRecharge.do';
				break;
			case 2:
				formname.action = '${pageContext.request.contextPath}/supply/doDrawing.do';
				break;
			default:
				alert('提交出错');
				return;
		}
		
		formname.submit();
		
	}
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form id="form1" name="strutsForm" action="doRecharge" namespace="/supply" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="supply.id" />
  	<s:hidden name="useraccountdealdetail.seqno"/>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>设置供货商额度</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td align="right">供货商:</td>
  	    <td align="left"><s:label name="supply.supplyName"/></td>
  	    <td align="right">余&nbsp;额:</td>
        <td align="left"><s:label name="supply.balance"/></td>
        <td>&nbsp;</td>
      </tr>
      
  	  <tr>
        <td align="right">拨款金额:</td>
  	   	<td align="left" ><s:textfield name="recharge"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">选择凭证类型:</td>
  	   	<td align="left" ><s:select name="useraccountdealdetail.voucherType" list="%{#{1:'现金凭证',2:'支付凭证',3:'转账凭证'}}" listKey="key" /></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">凭证号:</td>
  	   	<td align="left" ><s:textfield name="useraccountdealdetail.voucherCode"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >备注:</td>
  	   <td colspan="4" align="left"><s:textarea name="useraccountdealdetail.remark" cssClass="txtW5" rows="4"></s:textarea></td>
      </tr>
      	
      <tr>
  	    <td colspan="2" align="center"><input type="button" value="划款" onClick="javascript: gotoSave(1);" /></td>
  	    <td colspan="2" align="center">&nbsp;<input type="button" value="提款" onClick="javascript: gotoSave(2);" /></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

