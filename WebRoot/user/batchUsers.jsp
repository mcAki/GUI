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
  	<s:form action="batchUsers!upload" namespace="/users" method="post" enctype="multipart/form-data">
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<table width="700" border="0" align="center" cellspacing="3" class="form_tb">
  			
			<caption>导入志愿者</caption>
			<tr>
				<td colspan="5"></td>
 			</tr>
 
			<tr>
				<td align="right"><h3>导入志愿者来自:</h3></td>
				<td align="left"><s:select cssClass="txtW2" name="importSource" list="%{#{0:'  ====请选择====',1:'赛会志愿者',2:'城市志愿者',3:'未过安检'}}"/></td>
				<td align="right"><h3>证件重复处理方式:</h3></td>
				<td align="left"><s:select cssClass="txtW2" name="importDuplicateProcess" list="%{#{0:'===请选择处理方式===',1:'提示且不处理数据',2:'直接覆盖原记录'}}"/></td>
				<td>&nbsp;</td>
			</tr>
	 
			<tr>
				<td align="right"><h3>选择文件:</h3></td>
				<td align="left"><h3><s:file name="upload" cssClass="txtW3"></s:file></h3></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="center" colspan="5"><s:submit icon="icon-apply" value="提交"></s:submit></td>
			</tr>
			
  		</table>
  	</s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

