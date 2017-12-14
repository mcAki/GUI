<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>任务队伍树</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/xloadtree/xtree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/xloadtree/xmlextras.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/xloadtree/xloadtree.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/xloadtree/xtree.css" />

<style type="text/css">

body {
	background:	white;
	color:		black;
}

</style>

<script type="text/javascript" language="javascript">
   
    </script>
</head>
<%
 response.setHeader("Cache-Control","no-store");
 response.setDateHeader("Expires", 0);
 response.setHeader("Pragma","no-cache");
%>
<body>

<p></p>

<script type="text/javascript">

/// XP Look
webFXTreeConfig.rootIcon		= "${pageContext.request.contextPath}/xloadtree/images/xp/folder.png";
webFXTreeConfig.openRootIcon	= "${pageContext.request.contextPath}/xloadtree/images/xp/openfolder.png";
webFXTreeConfig.folderIcon		= "${pageContext.request.contextPath}/xloadtree/images/xp/folder.png";
webFXTreeConfig.openFolderIcon	= "${pageContext.request.contextPath}/xloadtree/images/xp/openfolder.png";
webFXTreeConfig.fileIcon		= "${pageContext.request.contextPath}/xloadtree/images/xp/file.png";
webFXTreeConfig.lMinusIcon		= "${pageContext.request.contextPath}/xloadtree/images/xp/Lminus.png";
webFXTreeConfig.lPlusIcon		= "${pageContext.request.contextPath}/xloadtree/images/xp/Lplus.png";
webFXTreeConfig.tMinusIcon		= "${pageContext.request.contextPath}/xloadtree/images/xp/Tminus.png";
webFXTreeConfig.tPlusIcon		= "${pageContext.request.contextPath}/xloadtree/images/xp/Tplus.png";
webFXTreeConfig.iIcon			= "${pageContext.request.contextPath}/xloadtree/images/xp/I.png";
webFXTreeConfig.lIcon			= "${pageContext.request.contextPath}/xloadtree/images/xp/L.png";
webFXTreeConfig.tIcon			= "${pageContext.request.contextPath}/xloadtree/images/xp/T.png";

//var tree = new WebFXLoadTree("WebFXLoadTree", "tree1.xml");
//tree.setBehavior("classic");

var rti;
var count=0;
var tree = new WebFXTree("任务队伍");
var node=new WebFXLoadTreeItem('${missionTeam.missionTeamname}'+"[大队]", 
			"${pageContext.request.contextPath}/missionTeam/page!showTeamByParentId.action?teamId=" + '${missionTeam.missionTeamId}' + "&treeDay=2010-01-01"  + "&date="+ new Date().getTime(),
			"javascript:OnSelectNode('${missionTeam.missionTeamId}')");
tree.add(node);
document.write(tree);
//alert(node.open);
tree.expand();
//tree.expandAll();
function OnSelectNode(teamId){
	var day = window.parent.document.getElementById('selectedDate').value;
	if(day==null||day==''||day==undefined){
		alert('请先选择考勤日期');
	}else{
		//alert(day);
		var url =  "${pageContext.request.contextPath}/missionservicelog/list!queryServiceLog.do?missionTeamId="
					+ teamId
					+ "&checkOnDate=" + day;
		//alert(url);
		var ifr1=window.parent.document.getElementById('iframe1');
	　　ifr1.contentWindow.location.href=url;
	}
}
</script>

</body>
</html>
