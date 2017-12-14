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
  	<s:form name="strutsForm" action="showPersonalByUserId" namespace="/usersutils" method="post">
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
  	     <td align="left" ><fmt:formatNumber maxFractionDigits="1" value="${users.integral }"/></td>
      </tr>
      
     
      
      <tr>
       	<td align="right">培训时长:</td>
        <td align="left"><s:label name="users.trainingTime"/></td> 
        <td align="right"> 服务时长:</td>
		<td align="left">
			<fmt:formatNumber maxFractionDigits="0" value="${users.serviceTime/60-users.serviceTime%60/60}"/>:
				<c:choose>
					<c:when test="${users.serviceTime%60==0 }">${users.serviceTime%60 }0</c:when>
					<c:otherwise>${users.serviceTime%60 }</c:otherwise>
				</c:choose>
		</td>
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
		
	<s:form name="strutsForm" namespace="/usersutils" method="post">
			<table width="600px" border="0" align="center" cellspacing="3" class="form_tb">
				<s:hidden name="user.userId" />
				<caption>志愿者详细信息注册</caption>
				
				<tr>
					<td width="167" align="right">培训时长:</td>
					<td width="203" align="left"><s:label name="users.trainingTime"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td width="182" align="right">服务时长:</td>
					<td width="219" align="left"><s:label name="users.serviceTime"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
	  
				<tr>
					<td width="167" align="right">中文姓:</td>
					<td width="203" align="left"><s:label name="users.firstnameCn"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td width="182" align="right">中文名:</td>
					<td width="219" align="left"><s:label name="users.lastnameCn"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">英文姓:</td>
					<td align="left"><s:label name="users.firstnameEn"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">英文名:</td>
					<td align="left"><s:label name="users.lastnameEn"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">出生国家 /地区:</td>
					<td align="left"><s:label name="usersDetail.birthCountry"  ></s:label></td>
					<td align="right">证件所属国家 /地区:</td>
					<td align="left"><s:label name="usersDetail.idcardCountry"  ></s:label></td>	
				</tr>
				
				<tr>
					<td align="right">出生日期:</td>
					<td align="left"><s:label name="usersDetail.birthday"  /><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">电子邮箱地址:</td>
					<td align="left"><s:label name="users.email"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">&nbsp;</td>
					<td align="left">&nbsp;</td>
					<td align="right"> 民族:</td>
					<td align="left"><s:label name="usersDetail.ethnicity"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">曾用姓:</td>
					<td align="left"><s:label name="usersDetail.firstnameFormer"></s:label></td>
					<td align="right">曾用名:</td>
					<td align="left"><s:label name="usersDetail.lastnameFormer"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">身高:</td>
					<td align="left"><s:label name="usersDetail.height"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">体重:</td>
					<td align="left"><s:label name="usersDetail.weight"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right"> 鞋码:</td>
					<td align="left"><s:label name="usersDetail.shoes"></s:label></td>
					<td align="right">身体状况:</td>
					<td align="left"><s:label name="usersDetail.health"/></td>
				</tr>
				
				<tr>
					<td align="right">保险受益人:</td>
					<td align="left"><s:label name="usersDetail.beneficiary"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">与您的关系:</td>
					<td align="left"><s:label name="usersDetail.beneficiaryPerson"></s:label><span style="color: #ff6600;">&nbsp;</span></td> 
				</tr>
				
				<tr>
					<td colspan="4"> <center><b>户籍所在地:</b></center></td>
				</tr>
				
				<tr>
					<td align="right">国家 / 地区:</td>
					<td align="left"><s:label name="usersDetail.censusCountry"></s:label></td>
					<td align="right">省/自治区 / 直辖市:</td>
					<td align="left"><s:label name="usersDetail.censusProvince"></s:label></td>
				</tr>
				
				<tr> 
					<td align="right">市 / 县:</td>
					<td align="left"><s:label name="usersDetail.censusCity"></s:label></td>
					<td align="right">详细地址:</td>
					<td align="left"><s:label name="usersDetail.censusAddress"></s:label></td>
				 </tr>
				 
				 <tr>
					<td colspan="4"><center><b>身份证明:</b></center></td>
				 </tr>
				 
				<tr>
					<td align="right">证件号码:</td>
					<td colspan="3" align="left"><s:label name="users.idcardCode"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				<tr>
					<td align="right"> 证件类型:</td>
					<td colspan="3" align="left">
					<s:label>
						<c:choose>
							<c:when test="${users.idcardType==0}">护照</c:when>
							<c:when test="${users.idcardType==1}">中国居民身份证</c:when>
							<c:when test="${users.idcardType==2}">中国人民解放军军官证或士兵证</c:when>
							<c:when test="${users.idcardType==3}">中国武警证件</c:when>
							<c:when test="${users.idcardType==4}">台湾居民来往大陆通行证</c:when>
							<c:when test="${users.idcardType==5}">港、澳居民来往内地通行证</c:when>
							<c:when test="${users.idcardType==6}">公安现役警官证件或士兵证（边防系统）</c:when>
							<c:when test="${users.idcardType==7}">公安现役警官证件或士兵证（警卫系统）</c:when>
							<c:when test="${users.idcardType==8}">公安现役警官证件或士兵证（消防系统）</c:when>
						</c:choose>
					</s:label>
				</tr>
				
				<tr>
					<td align="right">证件有效期截止日期:</td>
					<td colspan="3" align="left"><s:label name="usersDetail.idcardExpires"></s:label><span style="color: #ff6600;">&nbsp;</span> </td>
				</tr>
				 
				<tr>
					<td colspan="4"><center><b>学校证明:</b></center></td>
				</tr>
				 
				 <tr>
					<td align="right">学校:</td>
					<td align="left"><s:label name="usersDetail.school"/></td>
					<td align="right">哪个校区:</td>
					<td align="left"><s:label name="usersDetail.schoolCampus"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">校区所在的区(市):</td>
					<td align="left"><s:label name="usersDetail.schoolCampusArea"/></td>
					<td align="right">院系:</td>
					<td align="left"><s:label name="usersDetail.department"/></td>
				</tr>
				
				 <tr>
					<td align="right">专业:</td>
					<td align="left"><s:label name="usersDetail.major"/></td>
					<td align="right">班级:</td>
					<td align="left"><s:label name="usersDetail.clazz"/></td>
				</tr>
				
				<tr>
					<td align="right">在读学历:</td>
					<td align="left"><s:label name="usersDetail.degree"/></td>
					<td align="right">学校详细地址:</td>
					<td align="left"><s:label name="usersDetail.schoolAddress"/></td>
				</tr>
				
				<tr>
					<td align="right">入学时间:</td>
					<td align="left"><s:label name="usersDetail.entranceDate"/></td>
					<td align="right">预计毕业时间:</td>
					<td align="left"><s:label name="usersDetail.graduationDate"/></td>
				</tr>
				
				<tr>
					<td colspan="4"><center><b>现在居住地址:</b></center></td>
				</tr>
				
				<tr>
					<td align="right">国家 / 地区:</td>
					<td align="left"><s:label name="usersDetail.country"></s:label></td>
					<td align="right">省/自治区 / 直辖市:</td>
					<td align="left"><s:label name="usersDetail.province"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">市 / 县:</td>
					<td align="left"><s:label name="usersDetail.city"></s:label></td>
					<td align="right">所在区:</td>
					<td align="left"><s:label name="usersDetail.area"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">详细地址:</td>
					<td align="left"><s:label name="usersDetail.address"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">邮政编码:</td>
					<td align="left"><s:label name="usersDetail.postcode"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td colspan="4"><center><b>实际情况:</b></center></td>
				</tr>
				
				<tr>
					<td align="right">实际工作单位/就读学校名称: </td>
					<td align="left"><s:label name="usersDetail.workingCompany"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">工作单位/就读学校详细地址:</td>
					<td align="left"><s:label name="usersDetail.workingAdress"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">目前的职业:</td>
					<td align="left"><s:label name="usersDetail.job"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">申请人移动电话:</td>
					<td align="left"><s:label name="users.mobile"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">申请人固定电话:</td>
					<td align="left"><s:label name="usersDetail.telephone"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
					<td align="right">其他联系电话:</td>
					<td align="left"><s:label name="usersDetail.contaceName"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				</tr>
				
				<tr>
					<td align="right">其他联系移动电话:</td>
					<td colspan="3" align="left"><s:label name="usersDetail.contaceMobile"></s:label><span style="color: #ff6600;">&nbsp;</span></td>
				 </tr>
				 
				<tr>
					<td colspan="4"><center><b>语言技能情况:</b></center></td>
				 </tr>
				 
				 <tr>
					<td align="right">语言</td>
					<td align="left"><s:label name="usersDetail.laguage"></s:label></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				 <tr>
					<td align="right">外语言一:</td>
					<td align="left"><s:label name="usersDetail.foreignLaguage1"></s:label></td>
					<td align="right">外语一水平:</td>
					<td align="left"><s:label name="usersDetail.foreignLaguageLevel1"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">外语言二:</td>
					<td align="left"><s:label name="usersDetail.foreignLaguage2"></s:label></td>
					<td align="right">外语一水平:</td>
					<td align="left"><s:label name="usersDetail.foreignLaguageLevel2"></s:label></td>
				</tr>
				
				<tr>
					<td colspan="4"><center><b>信息来源</b></center></td>
				</tr>
				<tr>
					<td align="right">来源单位:</td>
					<td align="left"><s:label name="usersDetail.sourceCompany"></s:label></td>
					<td align="right">亚组委招募部门:</td>
					<td align="left"><s:label name="usersDetail.agocCompany"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">志愿者身份:</td>
					<td align="left"><s:label name="usersDetail.userIdentity"></s:label></td>
					<td align="right">职位:</td>
					<td align="left"><s:label name="usersDetail.agocPosition"></s:label></td>
				</tr>
				
				 <tr>
					<td align="right">场馆:</td>
					<td align="left"><s:label name="usersDetail.venue"></s:label></td>
					<td align="right">业务口:</td>
					<td align="left"><s:label name="usersDetail.functionalArea"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">是否上传照片:</td>
					<td  align="left"><s:label name="usersDetail.isUploadedPhoto"></s:label></td>
					<td align="right">注册编码:</td>
					<td align="left"> <s:label name="usersDetail.registerNum"></s:label></td>
				</tr>
				 
				<tr>
					<td align="right">是否全程服务亚运会:</td>
					<td align="left"><s:label name="usersDetail.isServeAgoc"></s:label></td>
					<td align="right">是否全程服务亚餐运会:</td>
					<td align="left">cong<s:label name="usersDetail.isServeAgocPara"></s:label></td>
				</tr>	
		</table>
		</s:form>
	    </center>
	<br/>	
	<br/>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
