<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>地区AJAX树</title>
<base target="_self"/>
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
var tree = new WebFXTree("地区树");
var hasChildNodeFlag=${hasChildNodeFlag}
var node=new WebFXLoadTreeItem("广州市", '${pageContext.request.contextPath}/tree!showDistrictTreeByParentId.html?districtParentId=4028818811a15abe0111a1a517480004&date='+ new Date().getTime(),"javascript:selectNode('4028818811a15abe0111a1a517480004','广州市',true)");
tree.add(node);

//var fileShareNode=new WebFXTreeItem("查看共享文件", "javascript:queryShareFile()");
//tree.add(fileShareNode);
document.write(tree);
//alert(node.open);
tree.expand();
//tree.expandAll();


  	  function selectNode(districtId,districtName,hasChild){
  	  	
  	  	var tempObj=new Object();
  	  	tempObj.districtName=districtName;
  	  	tempObj.districtId=districtId;
		if(hasChildNodeFlag==true){//判断是否允许点击选择文件夹而非最终节点
			//alert("aa: " + tempObj.districtName+"=="+tempObj.districtId);//可以关闭模态窗口并且返回
			
			window.returnValue = tempObj;			
			window.close(); 


		}
		else{
			if(hasChild==true ){		
  				alert("只能选择最下层节点");
  			}else{
  				//alert(tempObj.districtName+"=="+tempObj.districtId);//可以关闭模态窗口并且返回
  				                 
                 window.returnValue = tempObj;
                 window.close(); 
  			}
		}
  	  }
	
</script>

</body>
</html>
