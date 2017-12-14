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
  	<s:form name="strutsForm" action="doUpdate" namespace="/users" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>志愿者信息</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td align="right">所属组ID:</td>
  	    <td align="left"><s:select cssClass="txtW2" list="usergroups" name="users.usergroup.id" listKey="id" listValue="groupName" ></s:select></td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
  	  <tr>
  	    <td width="105" align="right">登录名称:</td>
  	    <td align="left"><s:textfield name="users.loginName"/></td>
  	    <td align="right">登录密码:</td>
  	    <td width="212" align="left"><s:password name="users.userPassword" /></td>
        <td>&nbsp;</td>
      </tr>
      
  	  <tr>
  	    <td align="right">用户姓名:</td>
  	    <td align="left"><s:textfield name="users.userName"/></td>
  	    <td align="right" >用户英文名:</td>
  	   	<td align="left"><s:textfield name="users.userEnglishName"/></td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">工作单位:</td>
  	   <td align="left">&nbsp;</td>
       <td align="right">email:</td>
  	   <td align="left" ><s:textfield name="users.email"/></td>
       <td>&nbsp;</td>
      </tr>
      
  	  <tr>
  	    <td align="right">固定电话:</td>
  	    <td align="left" >&nbsp;</td>
        <td align="right">手机号码:</td>
  	   	<td align="left" ><s:textfield name="users.mobile"/></td>
        <td>&nbsp;</td>
      </tr>
      	
  	  <tr>
  	    <td align="right" >所属地区:</td>
  	    <td align="left" style=" white-space:nowrap;">
  	   		<s:hidden id="districtId" name="districtId"></s:hidden>
  	      	<s:textfield name="users.districtName" id="districtName"/>　
  	    	<input icon="icon-btncom" align="left" type="button" value="选择" onClick="showTree(false)"/></td>
  	    <td align="right">邮政编码:</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
       
  	  <tr>
  	   
        <td align="right" >性别:</td>
  	    <td align="left"><s:select cssClass="txtW2" name="users.gender" list="%{#{0:'===请选择性别===',1:'男',2:'女'}}"/></td>
  	     <td align="right">用户状态:</td>
  	    <td  align="left"><s:select cssClass="txtW2" name="users.state" list="stateMap" listKey="key" listValue="value"/></td> 
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right"> 证件类型:</td>
        <td align="left"><s:select cssClass="txtW2" name="users.idcardType" list="%{#{0:'==请选择证件类型==',1:'身份证',2:'护照',3:'中国军人证件',4:'中国武警证件',5:'台湾同胞来往大陆通行证',6:'港澳居民来往内地通行证'}}"></s:select>
		<td align="right">证件号码:</td>
        <td align="left"><s:textfield name="users.idcardCode"/></td>
        <td>&nbsp;</td>  
       </tr>
       
       <tr>
        <td align="right"> 服务时长:</td>
		<td align="left"><s:textfield name="users.serviceTime" /></td>
		<td align="right">培训时长:</td>
        <td align="left"><s:textfield name="users.trainingTime"/></td>  
        <td>&nbsp;</td>
       </tr>
      
      
      <tr>
  	   <td align="right" >备注:</td>
  	   <td colspan="4" align="left"><s:textarea name="users.remark" cssClass="txtW5" rows="4"></s:textarea></td>
       
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

