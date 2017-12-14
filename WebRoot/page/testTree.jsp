<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>测试Tree</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
  </head>
  <%@ include file="../common/incHead.jsp" %>

  <!--
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.3.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqueryAjax.js"></script>
  --><script language="JavaScript"><!--
  	
		function showTree(hasChildNodeFlag){
			/* window.location='${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag; */
			/* t = screen.Height / 2 - 225;
	        l = screen.width / 2 - 300;
	        h = 500;
	        w = 400;
	        feature = 'top=' + t + ',left=' + l + ',width=' + w + ',height=' + h + ',resizable=yes,toolbar=0,menu=0,fullscreen=0,scrollbars=yes';
	        window.open('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag=true', 'showMessage', feature);*/
			
			var features="resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth=400px;dialogHeight=500px";
			var obj = new Object();
			obj.name="51js";
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			alert(returnVal);
		}

   function showAjaxTree(hasChildNodeFlag){
			/* window.location='${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag; */
			/* t = screen.Height / 2 - 225;
	        l = screen.width / 2 - 300;
	        h = 500;
	        w = 400;
	        feature = 'top=' + t + ',left=' + l + ',width=' + w + ',height=' + h + ',resizable=yes,toolbar=0,menu=0,fullscreen=0,scrollbars=yes';
	        window.open('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag=true', 'showMessage', feature);*/
			
			var features="resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth=400px;dialogHeight=500px";
			var obj = new Object();
			obj.name="51js";
			//window.location='${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime();
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			alert(returnVal.districtName);
  	  		alert(returnVal.districtId);
		}
		
		function showTeamAjaxTree(){
						
			var features="resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth=400px;dialogHeight=500px";
			var obj = new Object();
			obj.name="51js";
			//window.location='${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime();
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/teamTree!showTeamTreeRoot.action?missionId=1&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");

		}
		function exportExcel(){
			document.forms(0).action="${pageContext.request.contextPath}/missionservicelog/list!exportExcelData.action?missionTeamId=6";
			document.forms(0).submit();
		}
  --></script>
  <body>
	<form id="formid" method="post">
	districtId:<input name="districtId"  id="districtId" size="20"> 
	districtName:<input name="districtName"  id="districtName" size="20"> 
	<br>
   <input type="button" value="点击任何节点都关闭窗口" onclick="showTree(true)"/>
	<br>
   <input type="button" value="点击没有子节点的才可关闭窗口" onclick="showTree(false)"/>
	<br>
   <input type="button" value="点击任何节点都关闭窗口ajax树" onclick="showAjaxTree(true)"/>
	<br>
   <input type="button" value="点击没有子节点的才可关闭窗口ajax树" onclick="showAjaxTree(false)"/>
	<br>
	<input type="button" value="测试team树" onclick="showTeamAjaxTree()"/>
	<br>
	<input type="button" value="测试导出" onclick="exportExcel()"/>
	</form>
  </body>
</html>
