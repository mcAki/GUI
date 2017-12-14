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
  	<s:form name="strutsForm" action="showUserProfile" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<s:hidden name="teamId"></s:hidden>
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
  	    <td width="134" align="right">&nbsp;</td>
  	    <td width="134" align="left">&nbsp;</td>
  	  </tr>
      
  	  <tr>
	    <td align="right" >性&nbsp;别:</td>
	    <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.gender==1}">男</c:when>
	    						<c:when test="${users.gender==2}">女</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
		<td align="right" >&nbsp;</td>
  	    <td align="left">&nbsp;</td>
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
  	    				<td align="right">&nbsp;</td>
       <td align="left">&nbsp;</td>  
  	   
      </tr>
      
      <tr>
  	    <td align="right">手机号码:</td>
  	    <td align="left" ><s:label name="users.mobile"/></td>
  	     <td align="right">志愿者积分:</td>
  	    <td align="left" ><fmt:formatNumber maxFractionDigits="1" value="${users.integral }"/></td>
      </tr>
      
     
      
      <tr>
        <td align="right"> 服务时长:</td>
		<td align="left">
			<fmt:formatNumber maxFractionDigits="0" value="${users.serviceTime/60-users.serviceTime%60/60}"/>:
				<c:choose>
					<c:when test="${users.serviceTime%60==0 }">${users.serviceTime%60 }0</c:when>
					<c:otherwise>${users.serviceTime%60 }</c:otherwise>
				</c:choose>
		</td>
		<td align="right">&nbsp;</td>
        <td align="left">&nbsp;</td> 
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
        <td align="left"><s:label name="users.idcardCodeShow"/></td>
       </tr>
       
		
		<tr>
		<td align="center" colspan="4"><input type="button" value="关闭" onclick="javascript:window.close();"/></td>
		</tr>
  </table>
  </s:form>
  <br/><center>
	<display:table id="idListTb" name="missionPersonals"  style="width:600px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do">
		<display:column property="mission.missionId" title="任务ID" />
		<display:column property="missionSubject" title="任务主题" />
		<display:column property="mission.missionState.stateName" title="项目状态" style="font-weight:bold;"/>
		<display:column title="实施时段">
            	<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.mission.startDate}"/> 至 <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.mission.endDate}"/>
            </display:column>
	</display:table>
		
		
		
	    </center>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
