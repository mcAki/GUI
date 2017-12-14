<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>员工添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
  </head>
  
  <body>
  	<s:form id="form1" name="form1"  action="manage_add" namespace="/employee" method="post">
  		用户名:<s:textfield name="employee.username"/><br/>
  		密码:<s:textfield name="employee.password"/><br/>
  		性别:<s:radio list="#{'MAN':'男','WOMEN':'女'}" listKey="key" listValue="value" name="employee.gender"/>
  		<input type="submit" value="保存"/>
  	</s:form>

  </body>
</html>
