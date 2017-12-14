<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

  <%@ include file="incPrefix.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>选择地区</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>

  </head>

  <body>
  	<div class="dtree">
  	<a href="javascript: d.openAll();">展开</a> | <a href="javascript: d.closeAll();">关闭</a>
    <script type="text/javascript">
    initTree();
    var hasChildNodeFlag=${hasChildNodeFlag};
    function initTree(){
    	${districtTreeStr}
    }
    
    
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
    </div>
  </body>
</html>
