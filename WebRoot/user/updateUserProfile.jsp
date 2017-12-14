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
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='
								+hasChildNodeFlag
								+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			
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
  	<s:form id="userProfileForm" name="userProfileForm" action="userProfile!updateUserProfile" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="700" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>个人基本信息设置</caption>
  	  <tr>
  	    <td colspan="4"></td>
      </tr>
      <tr>
  	   <td align="right" >用户英文名:</td>
  	   <td align="left"><s:textfield name="userUpdateProfile.userEnglishName"></s:textfield></td>
       <td align="right" >所属地区:</td>
  	    <td width="250" align="left" style=" white-space:nowrap;">
  	   		<s:hidden id="districtId" name="userUpdateProfile.district.districtId"></s:hidden>
  	      	<s:textfield name="userUpdateProfile.districtName" id="districtName"/>　
  	    	<input icon="icon-prop" align="left" type="button" value="选择" onClick="showTree(false)"/></td>
    
  	  </tr>
      
  	  <tr>
  	    <td align="right">email:</td>
  	    <td align="left" ><s:textfield name="userUpdateProfile.email" /></td>
         <td align="right">固定电话:</td>
  	    <td align="left" >&nbsp;</td>
  	  </tr>
      
      <tr>
  	    <td align="right">邮政编码:</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
          <td>&nbsp;</td>
      </tr>
      
      <tr>
      	<td align="right">工作单位:</td>
      	<td colspan="2" align="left">&nbsp;</td>
      	<td>&nbsp;</td>
      </tr>
      
       <tr>
        <td align="right"> 联系地址:</td>
		<td colspan="2" align="left">&nbsp;</td>
		<td>&nbsp;</td>  	  
       </tr>

      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="修改" /></td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reset" value="重设"/></td>
      </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
