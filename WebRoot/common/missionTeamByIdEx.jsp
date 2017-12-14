<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<%@ include file="../common/incHead.jsp" %>
	
<title></title>
<style type="text/css">

body {
	background:	white;
	color:		black;
}

</style>

<script type="text/javascript" language="javascript">


function queryTeamById(teamId){
	var day = window.parent.document.getElementById('selectedDate').value;
	if(day==null||day==''||day==undefined){
		alert('请先选择考勤日期');
	}else{
		//alert(day);
		var url =  "${pageContext.request.contextPath}/missionservicelog/list!queryServiceLogEx.do?missionTeamId="
					+ teamId
					+ "&checkOnDate=" + day;
		//alert(url);
		var ifr1=window.parent.document.getElementById('iframe1');
	　　ifr1.contentWindow.location.href=url;
	}
}



    function Attendance(searchMissionId){
    	var str;
    	var searchTeamName=document.getElementById("idTeamId").value;
    	if(null==searchTeamName||searchTeamName==""){
    		alert("请填小队名称!");
    	}else{
	    	str = ezAjaxGetString( '${pageContext.request.contextPath}/ajax/getTeamByName.do?searchTeamName='+searchTeamName+'&searchMissionId='+searchMissionId);    	
	    	//alert(str);
	    	if(str=="none"){
	    		alert('you face at a really problem!');
	    	}else{
	    		queryTeamById(str);
	    	}
    	}
    	
    }
    </script>
</head>
<%
 response.setHeader("Cache-Control","no-store");
 response.setDateHeader("Expires", 0);
 response.setHeader("Pragma","no-cache");
%>
<body>

<p></p>
<center>
<table>
	<tr>
		<td>&nbsp;</td>
		<!--  <td><input type="button" value="考勤" onclick="queryTeamById()"/></td> -->
	</tr>
	<tr>
		<td>队名:<input type="text" id="idTeamId" name="idTeamId"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><input type="button" icon="icon-event" value="考勤" onclick="Attendance('${missionId}')"/></td>
	</tr>
</table>
</center>
</body>
</html>
