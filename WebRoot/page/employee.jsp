<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>员工列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
  </head>
  
  <body>
   ONGL:
   <br/>
   <s:iterator value="#request.employees">
   	<s:property value="username"/> ,<s:property value="password"/>, <s:property value="gender"/><br/>
   </s:iterator>
   
   <br/>
   
   <a href="<s:url action="manage_addUI" namespace="/employee"/>">员工添加</a>
   <br/>
   JSTL/EL:
   <br/>
   <c:forEach items="${employees}" var="employee">
   	${employee.username}, ${employee.password}<br/>
   </c:forEach>
  </body>
</html>
