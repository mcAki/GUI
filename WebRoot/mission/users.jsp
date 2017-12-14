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
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			
			//alert(returnVal);
			//document.getElementById("districtName").value=returnVal.districtName; 
			
            //document.getElementById("districtId").value=returnVal.districtId; 
            $("#districtId").val(returnVal.districtId);
            $("#districtName").val(returnVal.districtName);
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
  	    <td colspan="6"></td>
      </tr>
      
  	  <tr>
  	    <td align="right">用户姓名:</td>
  	    <td colspan="2" align="left"><s:textfield name="users.userName" readonly="true"/></td>
  	    <td colspan="2" align="right" >用户英文名:</td>
  	   	<td align="left"><s:textfield name="users.userEnglishName" readonly="true"/></td>
      </tr>
      
      <tr>
       <td align="right">工作单位:</td>
  	   <td colspan="2" align="left"><s:textfield name="users.userWork" readonly="true"/></td>
       <td colspan="2" align="right">email:</td>
  	   <td align="left" ><s:textfield name="users.email" readonly="true"/></td>
      </tr>
      
  	  <tr>
  	    <td align="right">固定电话:</td>
  	    <td colspan="2" align="left" ><s:textfield name="users.phone" readonly="true"/></td>
        <td colspan="2" align="right">手机号码:</td>
  	   	<td align="left" ><s:textfield name="users.mobile" readonly="true"/></td>
      </tr>
      	
  	  <tr>
  	    <td align="right" >所属地区:</td>
  	    <td colspan="2" align="left" style=" white-space:nowrap;">
  	   		<s:hidden id="districtId" name="districtId"></s:hidden>
  	      	<s:textfield name="users.districtName" id="districtName"/>　
  	    	<input align="left" type="button" value="选择" onClick="showTree(false)"/></td>
  	    <td colspan="2" align="right">邮政编码:</td>
        <td align="left"><s:textfield name="users.postcode" readonly="true"></s:textfield></td>
      </tr>
      
  	  <tr>
  	   
        <td align="right" >性别:</td>
  	    <td colspan="2" align="left"><s:textfield cssClass="txtW2" name="users.gender"  readonly="true"/></td>
  	     <td colspan="2" align="right">用户状态:</td>
  	    <td  align="left"><s:textfield cssClass="txtW2" name="users.state" readonly="true"/></td> 
      </tr>
      
      <tr>
        <td align="right"> 证件类型:</td>
        <td colspan="2" align="left"><s:textfield cssClass="txtW2" name="users.idcardType"  readonly="true"/>
		<td colspan="2" align="right">证件号码:</td>
        <td align="left"><s:textfield name="users.idcardCode" readonly="true"/></td>  
       </tr>
       
       <tr>
        <td align="right"> 服务时长:</td>
		<td colspan="2" align="left"><s:textfield name="users.serviceTime"  readonly="true"/></td>
		<td colspan="2" align="right">培训时长:</td>
        <td align="left"><s:textfield name="users.trainingTime" readonly="true"/></td>  
       </tr>
      
       <tr>
         <td align="right"> 联系地址:</td>
         <td colspan="5" class="nowrap" style="text-align:left">         
	         <select id="idCountry" class="txtW1"></select>
	         <select id="idProvince" class="txtW1"></select> 
	         <select id="idCity" class="txtW2"></select> 
	         <select id="idArea" class="txtW2"></select>  
	         <s:hidden name="users.country" id="hidCountry"></s:hidden>
	         <s:hidden name="users.province" id="hidProvince"></s:hidden>
	         <s:hidden name="user.city" id="hidCity" ></s:hidden>
	         <s:hidden name="users.area" id="hidArea" ></s:hidden>
	         <script type="text/javascript">
				EzNPCA($('#idCountry')[0],$('#idProvince')[0],$('#idCity')[0],$('#idArea')[0],
					   $('#hidCountry')[0],$('#hidProvince')[0],$('#hidCity')[0],$('#hidArea')[0]);
				</script>         
    	</td>
      </tr>
      
      <tr>
       	<td>&nbsp;</td>
		<td colspan="4" align="left"><s:textfield cssClass="txtW4" name="users.address" readonly="true"/></td>
		<td>&nbsp;</td>  	  
       </tr>
      
      
      <tr>
  	   <td align="right" >备注:</td>
  	   <td colspan="5" align="left"><s:textarea name="users.remark" cssClass="txtW5" rows="4" readonly="true"></s:textarea></td>
       
      </tr>
      
      <tr>
  	    <td colspan="3" align="center"><s:submit value="提交"></s:submit>
        </td>
  	    <td colspan="3" align="center">&nbsp;<s:reset value="重设"></s:reset></td>
      </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

