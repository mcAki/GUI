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
<center>
  <br/>
    <img src="${pageContext.request.contextPath}/images/ico/m_broadcast.png" alt="" width="48" height="48" /><br/>
  <br/>
  <p class="msgMessage">
  	登陆超时,请重新登陆!
  </p>
  <p class="msgAutoRedirct">
  	
  </p>
  <br/>
  <p>
  
  <ul calss="msgLinks">
   <a href="${pageContext.request.contextPath}/login.do">点击登陆</a>
  </ul>
  </p>
</center>
</body>
</html>
