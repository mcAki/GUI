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
	
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form id="form1" name="strutsForm" action="doAddKey" namespace="/users" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>设置加密key</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td width="105" align="right">用户:</td>
  	    <td align="left"><s:label name="users.userName"/></td>
  	    <td align="right">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
  	  <tr>
        <td align="right">加密key:</td>
  	   	<td align="left" ><s:textfield name="users.otp.keyId"/>&nbsp;不填为清除key</td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td colspan="4" align="center"><input type="submit" value="提交" /></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
