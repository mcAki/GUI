<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>

  </head>  
  <body>
  `
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="viewUserProfile!viewUserProfile" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="550" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>个人基本信息</caption>
  	  <tr>
  	    <td colspan="4"></td>
      </tr>
     
  	  <tr>
      	<td width="121" align="right">用户姓名:</td>
  	    <td align="left"><s:label name="users.userName"/></td>
  	    <td width="134" align="right">登录名称:</td>
  	    <td width="134" align="left"><s:label name="users.loginName"/></td>
  	  </tr>
      
  	  <tr>
       <td align="right" >用户英文名:</td>
  	   <td align="left"><s:label name="users.userEnglishName"/></td>
	   <td align="right" >性&nbsp;别:</td>
	   <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.gender==1}">男</c:when>
	    						<c:when test="${users.gender==2}">女</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
      </tr>
      
      <tr>
       <td align="right" >所属地区:</td>
  	   <td width="132" align="left" style=" white-space:nowrap;">
  	   		<s:hidden id="districtId" name="districtId"/><s:label name="users.districtName"/></td>
  	   <td align="right">email:</td>
  	   <td align="left" ><s:label name="users.email"/></td>
  	  </tr>
      
  	   <tr>
        <td align="right">用户状态:</td>
  	    <td align="left"><s:label>
  	    					<c:choose>
  	    						<c:when test="${users.state==0}">志愿者</c:when>
  	    						<c:when test="${users.state==1}">拟录用</c:when>
  	    						<c:when test="${users.state==2}">待录用</c:when>
  	    						<c:when test="${users.state==3}">已录用</c:when>
  	    					</c:choose>
  	    				</s:label></td>
  	    				<td align="right">邮政编码:</td>
       <td align="left"><s:label name="users.postcode"/></td>  
  	   
      </tr>
      
      <tr>
  	    <td align="right">手机号码:</td>
  	    <td align="left" ><s:label name="users.mobile"/></td>
  	     <td align="right">志愿者积分:</td>
  	    <td align="left" ><s:label name="users.integral"/></td>
      </tr>
      
     
      
      <tr>
       	<td align="right">培训时长:</td>
        <td align="left"><s:label name="users.trainingTime"/></td> 
        <td align="right"> 服务时长:</td>
		<td align="left"><s:label name="users.serviceTime" /></td>
      </tr>
      
      <tr>
        <td align="right"> 证件类型:</td>
		<td align="left"><s:label>
						 	<c:choose>
  	    						<c:when test="${users.idcardType==1}">身份证</c:when>
  	    						<c:when test="${users.idcardType==2}">护照</c:when>
  	    						<c:when test="${users.idcardType==3}">中国军人证件</c:when>
  	    						<c:when test="${users.idcardType==4}">中国武警证件</c:when>
  	    						<c:when test="${users.idcardType==5}">台湾同胞来往大陆通行证</c:when>
  	    						<c:when test="${users.idcardType==6}">港澳居民来往内地通行证</c:when>
  	    					</c:choose>
  	    				</s:label>
						 </td>
		<td align="right">证件号码:</td>
        <td align="left"><s:label name="users.idcardCode"/></td>
       </tr>
       
       <tr>
        <td align="right">工作单位:</td>
  	    <td align="left" colspan="3"><s:label cssClass="txtW4" name="users.userWork"/></td>
       </tr>
      
       <tr>
        <td align="right"> 联系地址:</td>
		<td align="left" colspan="3"><s:label cssClass="txtW4" name="users.address" /></td>   
       </tr>
       
       <tr>
        <td align="right" >备&nbsp;注:</td>
  	    <td align="left" colspan="3"><s:label name="users.remark" rows="3"/></td> 	  
       </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
