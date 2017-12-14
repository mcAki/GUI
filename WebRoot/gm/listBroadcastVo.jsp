<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
  <s:property value="#request.message"/>
  <br/><br/>
<br/><center><display:table id="idListTb" name="pageView"  style="width:900px;" pagesize="20" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/gmmanage/list!listBroadcast.do">
			<display:caption>广播消息列表</display:caption>
			<display:column property="uuid" title="ID" />
			<display:column property="content"  title="内容" />
            <display:column title="状态" >
					<c:choose>
						<c:when test="${idListTb.type==1}">滚屏</c:when>
						<c:when test="${idListTb.type==2}">聊天框</c:when>
						<c:when test="${idListTb.type==3}">所有</c:when>
					</c:choose>
				</display:column>
			<display:column title="发送结果" >
				<c:choose>
					<c:when test="${idListTb.result==1}">成功</c:when>
					<c:otherwise>失败</c:otherwise>
				</c:choose>
			</display:column>
            <display:column ><a href="page!removeBroadcast.do?uuid=${idListTb.uuid}" >移除消息</a></display:column >
		</display:table>
	    </center>
	<br/>	
	<br/>

<a href="staff!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
