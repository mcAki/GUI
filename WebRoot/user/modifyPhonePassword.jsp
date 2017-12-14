<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<!--<script type="text/javascript">
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
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			
			//alert(returnVal);
			//document.getElementById("districtName").value=returnVal.districtName; 
			
            //document.getElementById("districtId").value=returnVal.districtId; 
            $("#districtId").val(returnVal.districtId);
            $("#districtName").val(returnVal.districtName);
		}
	
	</script>
  --></head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="phonePassword!modifyPhonePassword" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="450" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>电话操作码修改</caption>
  	  <tr>
        <td width="30">&nbsp;</td>
  	    <td width="151" align="right">旧操作识别码：</td>
        <td width="158" align="left"><s:label name="users.operationCode"/></td>
        <td width="82">&nbsp;</td>
      </tr>
      
       <tr>
        <td width="30">&nbsp;</td>
  	    <td width="151" align="right">新操作识别码：</td>
        <td width="158" align="left"><s:textfield name="modifyPhonePassword.operationCode" value=""/></td>
        <td width="82">&nbsp;</td>
      </tr>
      
      <tr>
        <td width="30">&nbsp;</td>
  	    <td width="151" align="right">请确认操作识别码：</td>
        <td width="158" align="left"><s:textfield name="retypeNewOperationCode" value=""/></td>
        <td width="82">&nbsp;</td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
  	    <td width="151" align="right"><s:submit icon="icon-apply" value="修改"/></td>
        <td width="158"><s:reset icon="icon-reset" value="重设"/></td>
        <td>&nbsp;</td>
      </tr>
  	  
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
