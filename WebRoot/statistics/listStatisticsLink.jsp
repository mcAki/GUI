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
  <br/>
  <center>
  <table>
  	<tr>
  		 <td height="25" colspan="2" class="left_txt">这里增加链接</td>
  		 <td>&nbsp;</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td height="25" colspan="2" class="left_txt"><ul>
  			<li><a href="${pageContext.request.contextPath}/statistics/list!getCompetitionCount.do">统计赛会志愿者人数</a></li>
  		</ul></td>
  	</tr>
  </table>
	<br/>	
	<br/>
<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
