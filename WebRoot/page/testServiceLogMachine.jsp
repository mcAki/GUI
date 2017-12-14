<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<%@ page import="com.sys.volunteer.common.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <%@ include file="../common/incHead.jsp" %>
	<link href="../css/criteria.css" rel="stylesheet" type="text/css">  
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
  
  <!--
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.3.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqueryAjax.js"></script>
  --><script language="JavaScript"><!--
  	function testList(){
  		var _name = $("#name").val();
  		var _message = $("#message").val();
  		var jsonData = {name:_name,message:_message};
  		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/jsontest!testList.html",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				for(var i=0;i<msg.length;i++){
					alert(msg[i].userName+"==="+msg[i].loginIp);
				}
				
			}
		});
  	}
  
  function testObject(){
  		var _name = $("#name").val();
  		var _message = $("#message").val();
  		var jsonData = {"employee.username":_name,"employee.password":_message};
  		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/jsontest!testObject.html",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				alert(msg.userName+"==="+msg.loginIp);
			}
		});
  	}
  	
  	 function testMultiply(){
  		var _name = $("#name").val();
  		var _message = $("#message").val();
  		var jsonData = {name:_name,message:_message};
  		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/jsontest!testMultiply.html",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				alert(msg.admin.userName);
				
				for(var i=0;i<msg.adminList.length;i++){
					alert(msg.adminList[i].userName+"==="+msg.adminList[i].loginIp);
				}
			}
		});
  	}
  	
  	function testExcetption(){
  		var _name = $("#name").val();
  		var _message = $("#message").val();
  		var jsonData = {name:_name,message:_message};
  		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/jsontest!testExcetption.html",
			data:jsonData,
			dataType:"json",
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				alert("调用服务器端错误");
			},
			success:function(msg){
				alert(msg.userName+"==="+msg.loginIp);
			}
			
		});
  	}
  	
  		//ajax异常回调函数
		function callerror(XMLHttpRequest, textStatus, errorThrown) 
		{ /**
			alert(textStatus);
			alert(errorThrown);*/
			alert("调用服务器端错误");
		}
		
		function testNormanFun(){
			var _name = $("#name").val();
	  		var _message = $("#message").val();
	  		var jsonData = {name:_name,message:_message};
	  		postJonRequest(jsonData,'${pageContext.request.contextPath}/jsontest!testMultiply.html',"服务器出错",
	  				function(msg){
						alert(msg.admin.userName);
						for(var i=0;i<msg.adminList.length;i++){
							alert(msg.adminList[i].userName+"==="+msg.adminList[i].loginIp);
						}
				})
	  		
		}

		  
  function testBlockAcax(block){
			var _name = $("#name").val();
	  		var _message = $("#message").val();
	  		var jsonData = {name:_name,message:_message};
	  		alert("步骤:1  提交json");	  
	  				
	  		ezAjax( '${pageContext.request.contextPath}/jsontest!testMultiply.html',
			  		jsonData,
			  		'服务器出错',
			  		!block,
			  		function(msg){
						alert(msg.admin.userName);
						for(var i=0;i<msg.adminList.length;i++){
							alert(msg.adminList[i].userName+"==="+msg.adminList[i].loginIp);
					    }
				    });
						  
	  		alert("步骤:2 获取了json");
			//for(var i=0;i<msg.adminList.length;i++){
			//	alert(msg.adminList[i].userName+"==="+msg.adminList[i].loginIp);
			//}
			alert("步骤:3 完成所有");
	  		
  	}
    function testGetString(){
    	str = ezAjaxGetString( '${pageContext.request.contextPath}/jsontest!testAjaxString.html');    	
    	alert(str);
    }
    
    function testGetStrings(){
    	str = ezAjaxGetStrings( '${pageContext.request.contextPath}/jsontest!testAjaxStrings.html');    	
    	alert(str);
    }
    function testMachin(){
    	var jsonData = {		
		"missionServiceLog.checkOnDate":$.trim("2010-10-21"),
		"time":$("#time").val(),
		"missionServiceLog.usersByUserId.userId":$.trim("402880eb2ba41b20012ba41f04160027"),
		"missionServiceLog.state":$("#state").val(),
		"missionId":28
	};
	
	//alert("步骤:1  提交json");
	
	ezAjax("${pageContext.request.contextPath}/ajax/saveServiceLogForMachine.do",
		jsonData,
		"服务器出错",
		false,
		function(msg){
			if(msg.flag==<%=Const.MISSION_SERVICELOG_CHECK_STATE_WORNG%>){
				alert(msg.description);
			}else{
				alert("成功");
			}
			
			//alert(msg.desc);		
		}
	);
    
    }
  --></script>
  <body>
	状态填写1或者2:<input name="state"  id="state" size="20" maxlength="1"> 
	<br>
	时间:<input name="time"  id="time" size="20" maxlength="20" value="2010-10-21 9:00:23"> 
	<br>
   <input type="button" value="测试打卡机" onclick="testMachin()"/>
	<br>
  
  </body>
</html>
