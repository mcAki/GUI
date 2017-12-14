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
  <script type="text/javascript">				

	function topage(pageIndex){
		document.forms(0).action="page!testPage.action?pageIndex="+pageIndex;
		document.forms(0).submit();
	}
	
</script>
  <body>
	<s:form>
   ONGL:
   <br/>
   <s:iterator value="#request.pageView.records">
   	<s:property value="username"/> ,<s:property value="password"/>, <br/>
   </s:iterator>
   
   <br/>
    当前页:第${pageView.currentpage}页 | 总记录数:${pageView.totalrecord}条 | 每页显示:${pageView.maxresult}条 | 总页数:${pageView.totalpage}页</font>　
<c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
    <c:if test="${pageView.currentpage==wp}"><b>第${wp}页</font></b></c:if>
    <c:if test="${pageView.currentpage!=wp}"><a href="javascript:topage('${wp}')">第${wp}页</a></c:if>
</c:forEach>

</s:form>

  </body>
</html>
