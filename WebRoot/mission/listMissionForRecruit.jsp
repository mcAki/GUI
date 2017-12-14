<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  	<link href="../css/criteria.css" rel="stylesheet" type="text/css">
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
	<%@ include file="../common/incHead.jsp"%>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
 <br/><br/>
  <br/>  <center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list!listMissionForRecruit" namespace="/mission"
			method="post">
				<table>
				<tr>
					<td>项目类型:&nbsp;</td>
					<td>&nbsp;<s:select name="selection" list="%{#{0:'显示所有',1:'新生活·科技',2:'新生活·健康',3:'新生活·学习',4:'新生活·礼仪',5:'新生活·公益',6:'新生活·诚信',7:'新生活·环保',8:'第16届亚运会(赛会)',9:'第16届亚残运会(赛会)',10:'亚运城市文明',11:'亚运城市站点',12:'亚残城市文明',13:'亚残城市站点'}}" listKey="key"></s:select>&nbsp;</td>
					<td>&nbsp;项目主题:&nbsp;</td>
					<td>&nbsp;<input type="text" name="subject" size="10" value="${param.subject}"/>&nbsp;</td>
					<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
				</tr>
			</table>
			</s:form>
		</div> <center>
  <display:table id="idListTb" name="missions"  style="width:750px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/list!listMissionForRecruit.do">
			<display:column property="missionId" title="ID"></display:column>
            <display:column property="subject" title="项目主题" style="width:200px;" />
            <display:column title="实施时段">
            	<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.startDate}"/> 至 <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.endDate}"/>
            </display:column>
			<display:column title="岗位">
				<a href="${pageContext.request.contextPath}/mission/personal!recruitEx.do?missionId=${idListTb.missionId}">招募</a>
            </display:column>
		</display:table>
	    </center>
	<br/>	
	<br/>
		<script type="text/javascript">
		highlightTableRows("idListTb");
		</script>
<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
