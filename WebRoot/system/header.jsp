<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<HTML>
<head>
<%@ include file="../common/incHead.jsp" %>
<title>网站管理员登陆</title>
<script type="text/javascript" src="js/jquery17mis.js"></script>
<link href="css/main_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/divselect.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	/**background-color:#102F00;*/
}
-->
</style>
<script type="text/javascript">
function logout(){
	if (confirm("您确定要退出控制面板吗？")){
		parent.location = "${pageContext.request.contextPath}/logout.do";
		return false;
	}else{
		return;
	}
}
</script>


</head>
<body>

 <div id="header">
     <div id="head">
         <div id="nav">
            <img src="img/pic_header2.jpg" width="189" height="27"  alt=""/> 
            <img src="img/pic1_header.jpg" width="189" height="27"  alt=""/>
           <img  style="CURSOR: pointer" onclick="javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=4000307517&eid=218808P8z8p8Q8y8x808x&o=&q=7&ref='+document.location, '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');"  border="0" SRC="img/pic2_header.jpg">
           <a href="javascript:logout();"><img src="img/pic3_header.jpg" alt="" /> </a>
          
      </div>
         <div id="logo">
             <a   href="javascript:window.top.location.href='/';"><img src="img/logo.png" width="360"   alt=""/></a>
             <%--
             <a   href="javascript:window.top.location.href='/';"><img src="img/logo.png" width="360" height="65"  alt=""/></a>
              --%>
         </div>
     </div>
</div>

</body>
</HTML>
