<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function showTree(hasChildNodeFlag){
			//window.location='${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag;
			t = screen.Height / 2 - 225;
	        l = screen.width / 2 - 300;
	        h = 500;
	        w = 400;
	        
	        //window.open('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag=true', 'showMessage', feature);
			
			var features="resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth=400px;dialogHeight=500px";
			var obj = new Object();
			obj.name="51js";
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			
			//alert(returnVal);
			//document.getElementById("districtName").value=returnVal.districtName; 
			
            //document.getElementById("districtId").value=returnVal.districtId; 
            if(!$.isEmptyObject(returnVal)){
            	$("#districtId").val(returnVal.districtId);
            	$("#districtName").val(returnVal.districtName);
			}
		}
	$(document).ready(function(){
	       $("#sendCodeButton").click(function(){
	      var userId = $("#userId").val();
	      $("#sendCodeButton").removeAttr("disabled");
	      if(userId!=""){
			         $.get("/MPRS/users/modifyUser!delKeySendCode.do",{userId:userId},function(data){
				             if(data=="noOtp"){
			                      alert("你没有绑定加密key，不能进行此操作");
			                 }else if(data=="success"){
				                 alert("验证码已经发送，如果五分钟之内没有收到，请重新获取。");
				              }else{
				                 alert("验证码发送失败");
				                 $("#sendCodeButton").text("重新获取验证码"); 
				                 $("#sendCodeButton").attr("disabled","true");
				              }
			    });
	       }
	     });
	  
	    $("#form_submit").click(function(){
	      if(confirm("你确定要取消加密key吗？")){
	        // var codeTemp = $("#code").val();
		     //   if(codeTemp==""){
		      //      $("#show_verifiCode_mes").text("请输入验证码");
		       //     return;
		      //  }else{
			        $.get("/MPRS/users/doDelKey.do",$("#struts_form").serialize(),function(data){
			            if(data=="success"){
			               alert("操作成功,您已经成功取消绑定加密key，稍后会有短信通知！");
			            }else if(data=="noOtp"){
			               $("#show_verifiCode_mes").text("你没有绑定加密key，不能进行此操作");
			            }else if(data=="error"){
			               $("#show_verifiCode_mes").text("验证码输入有误");
			            }else{
			               $("#show_verifiCode_mes").text("操作有误");
			            }
			        });
	      }
		      //  }
	       });
	    })
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
              <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed" style="min-height: 500px; ">
                     <div class="cxjj fixed">
                            <p class="jjcx">取消加密key</p>
                     </div>
                     <div class="country communi" style=" border: 1px solid #669999;padding: 20px;width:550px; margin-top:30px;">
                          <center> <span style="font-weight: bold;color:red;font-size: 14px;">点击提交，取消加密key </span> </center>
                           	<s:form id="struts_form" name="strutsForm" action="doDelKey" namespace="/users" method="post">
							  	<center><s:fielderror></s:fielderror>
							  	
							  	<p> <span id="show_verifiCode_mes" style="color:red;"></span> </p>
							  	</center>
							  	<s:token/>
							  		<s:hidden name="users.userId" id="userId" ></s:hidden>
							  	<table width="550" border="0" align="center" cellspacing="3" >
							  	  <tr>
							  	    <td colspan="5" align="center"></td>
							      </tr>
							      
							  	  <tr>
							  	    <td width="255" align="right" class="font_order">用户名称:</td>
							  	    <td align="center" style="font-size:14px;"><s:label name="users.userName"/></td>
							  	    <td align="right">&nbsp;</td>
							        <td align="left">&nbsp;</td>
							        <td>&nbsp;</td>
							      </tr>
							  	  <tr>
							  	    <td colspan="5" height="20"></td>
							      </tr>
							  	  <tr>
							  	    <td  align="right" class="font_order">加密key编码:</td>
							  	    <td align="center" style="font-size:14px;">${users.otp.keyId }</td>
							  	    <td align="right">&nbsp;</td>
							        <td align="left">&nbsp;</td>
							        <td>&nbsp;</td>
							      </tr>
							  	  <tr>
							  	    <td colspan="5" height="20"></td>
							      </tr>
							      <%--
							  	  <tr>
							        <td align="right">短信验证码:</td>
							  	   	<td align="left" ><s:textfield id="code" name="verificationCode"/></td>
							        <td> <span id="show_verifiCode_mes" style="color:red;"></span> </td>
							  	    <td>&nbsp;</td>
							        <td>&nbsp;</td>
							      </tr>
							       --%>
							      
							      <tr>
							      <%--
							  	    <td colspan="2" align="center"><input type="button" id = "sendCodeButton" value="发送验证码" /></td>
							       --%>
							  	    <td colspan="5" align="center"><button type="button" id ="form_submit" value="提交" /></td>
							      </tr>
									
							  </table>
							  </s:form>
                     </div>
                    </div>
             </div>
          </div>
  
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

