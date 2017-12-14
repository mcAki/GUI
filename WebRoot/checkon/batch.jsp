<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>

<script type="text/javascript">

$(document).ready(function() {


	$(".rows").hover(
		function () {
			$(this).addClass("hover");
		},
		function () {
			$(this).removeClass("hover");					
		}
	);
	
	$(".close").hover(
		function () {
			$(this).addClass("hover");			
		},
		function () {
			$(this).removeClass("hover");					
		}
	);
	
	$(".close").click(
		function(){
			//alert("删,删左(ajax事件)");
			$(this).parent().fadeOut("slow",
				function(){
					//alert("删: "+$(this).parent());
					tmpLi = $(this).parent();
					tmpLi.remove();
				}			
			);
		}
	);
//$(".close").addClass("hover");
});

</script>
<style type="text/css">
.checkon_grid{
	padding:5px;
}

.checkon_grid li{
	padding:0px;
	margin:0px;
	list-style:none;
	list-style-type:none;
}

.checkon_grid a:link, 
.checkon_grid a:visited {
	color:#0C0;
} 
.checkon_grid a:hover,
.checkon_grid a:active {
	color: #FF6600;
} 


.checkon_grid .rows{
	height:24px;
	width:500px;
	white-space:nowrap;
	border-color:#060;
	border-width:1px 1px 0px 1px;
	border-style:solid;
	background-color:#F1FFBF;
	margin:-1px 0px 0px 0px;
}

.checkon_grid .hover{
	background-color:#FC0;
}

.checkon_grid .rows .uid{
	width:50px;
	overflow:hidden;
	float:left;
}
.checkon_grid .rows .username{
	width:130px;
	overflow:hidden;
	float:left;
}

.checkon_grid .rows .state{
	width:130px;
	overflow:hidden;
	float:left;
}

.checkon_grid .rows .minute{
	width:60px;
	overflow:hidden; 
	float:left;
}

.checkon_grid .rows .hour{
	width:50px;
	overflow:hidden; 	
	float:left;
}

.checkon_grid .rows .close{
	float:right;
	color:#FFF;
	font-weight:bold;
	background-color:#360;
	height:18px;
	width:18px;
	padding:1px;
}

.checkon_grid .rows .hover{
	background-color:#F90;
	cursor:pointer;
}

</style>
	</head>
	<body>
		<center>
			<%@ include file="../common/incBanner.jsp"%>
<s:form id="strutsForm" action="doUpdate" namespace="/admin" method="post">
				<s:token></s:token>
				<s:fielderror></s:fielderror>
</br></br></br>
<div class="checkon_grid">
<ul>
<li><div id="r1" class="rows">
    <div class="uid">0010</div>
    <div class="username">张三</div>
    <div class="state">正常出勤</div>
    <div class="minute">180</div>
    <div class="hour">8</div>
    <div class="close">×</div>
</div></li>
<li><div id="r2" class="rows">
    <div class="uid">0011</div>
    <div class="username">李四</div>
    <div class="state">正常出勤</div>
    <div class="minute">180</div>
    <div class="hour">8</div>
    <div class="close">×</div>
</div></li>
<li><div id="r3" class="rows">
    <div class="uid">0012</div>
    <div class="username">陈五</div>
    <div class="state">正常出勤</div>
    <div class="minute">180</div>
    <div class="hour">8</div>
    <div class="close">×</div>
</div></li>
<li><div id="r4" class="rows">
    <div class="uid">0013</div>
    <div class="username">赵六</div>
    <div class="state">正常出勤</div>
    <div class="minute">180</div>
    <div class="hour">8</div>
    <div class="close">×</div>
</div></li>
<li><div id="r5" class="rows">
    <div class="uid">0014</div>
    <div class="username">李七</div>
    <div class="state">正常出勤</div>
    <div class="minute">180</div>
    <div class="hour">8</div>
    <div class="close">×</div>
</div>
</li>
<li><div id="r6" class="rows">
    <div class="uid">0015</div>
    <div class="username">王八</div>
    <div class="state">正常出勤</div>
    <div class="minute">180</div>
    <div class="hour">8</div>
    <div class="close">×</div>
</div></li>
</ul>
</div>
</s:form>
			<%@ include file="../common/incFooter.jsp"%></center>
		
	</body>
</html>
