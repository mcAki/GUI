<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		
		<script type="text/javascript">
			function selectUser(id,username){
				var userpack=new Object();
				userpack.uid=id;				
				userpack.username=username;				
				window.returnValue = userpack;
				window.close(); 
			}

		</script>
		<!-- 不创建新窗口，在本窗口显示东西 -->
		<base target="_self">
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form1" name="form0"
			action="list" namespace="/area"
			method="post">
				地区:
				<input type="text" name="province" size="10" value="${param.province}"/>
				<input icon="icon-search"  type="submit" value="搜索"/>
			</s:form>
		</div>
				<br />
			<display:table id="idListTb" name="areas" style="width:500px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/area/list.do">
				<display:column maxLength="5" property="province" title="地区" />
				<display:column title="操作" ><a href="javacript:void(0)" onclick="selectUser('${idListTb.provinceCode}','${idListTb.province }')">选择</a></display:column>
			</display:table>
			
		</center>
		<br />
		<br />
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
