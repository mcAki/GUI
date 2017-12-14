<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>测试JSON</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
  </head>
  <%@ include file="../common/incHead.jsp" %>
  
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
  --></script>
  <body>
	name:<input name="name"  id="name" size="20"> 
	message:<input name="message"  id="message" size="20"> 
	<br>
   <input type="button" value="测试集合List" onclick="testList()"/>
	<br>
   <input type="button" value="测试对象" onclick="testObject()"/>
	<br>
   <input type="button" value="测试复杂属性" onclick="testMultiply()"/>
	<br>
   <input type="button" value="测试异常" onclick="testExcetption()"/>
	<br>
	<input type="button" value="测试公共函数" onclick="testNormanFun()"/>
	<br>
   <input type="button" value="测试阻塞公共函数"" onclick="testBlockAcax(true)"/><input type="button" value="测试无阻塞公共函数"" onclick="testBlockAcax(false)"/>
   <br>
   <input type="button" value="测试获取一个字符串" onclick="testGetString()"/>
   <br>
   <input type="button" value="测试获取一抽字符串(数组)" onclick="testGetStrings()"/>
   
  	<s:form action="jsontest!testAjaxForm" namespace="/" method="post">
  	
  		用户名:<s:textfield name="employee.username"/><br/>
  		密码:<s:textfield name="employee.password"/><br/>
		<input type="submit" value="保存"/>
  	</s:form>
  </body>
</html>
