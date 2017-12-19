<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
	
		function showGroupForLog(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/users/list!listGroupForLog.do?typeId="+${modifyUsers.usergroup.groupType};
			//为了处理缓存加入随机时间
			url += "&date=" + new Date().getTime();
			
			//弹出参数
			var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth="+ width +"px;dialogHeight=" + height + "px";
			
			//创建传入参数
			var obj = new Object();
			obj.name = "参数";
			var returnVal = window.showModalDialog(url,obj,"dialogWidth=600px;dialogHeight=500px");
			if(!$.isEmptyObject(returnVal)){
				$("#userId").val(returnVal.uid);
				$("#userName").val(returnVal.username);
			}
		}
		
		
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
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='
								+hasChildNodeFlag
								+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			
			//alert(returnVal);
			//document.getElementById("districtName").value=returnVal.districtName; 
			
            //document.getElementById("districtId").value=returnVal.districtId; 
            if(!$.isEmptyObject(returnVal)){
	            $("#districtId").val(returnVal.districtId);
	            $("#districtName").val(returnVal.districtName);
			}
		}
	
		var maxtime; 
	var timer;
	function CountDown(){  
	          var sedonds;
		      if(maxtime>=0){  
					seconds = Math.floor(maxtime);  
					msg = "等待获取验证码 ……"+seconds+"秒";  
					$(".sendCodeButton").text(msg);
					 --maxtime;  
				} else{  
					clearInterval(timer);  
					$(".sendCodeButton").removeAttr("disabled");
					$(".sendCodeButton").text("获取验证码"); 
					//window.location.reload(); 
				}  
	}  
	  
	  $(document).ready(function(){
	       $(".sendCodeButton").click(function(){
	      var userId = $("#userId").val();
	      if(userId!=""){
	        // $(".sendCodeButton").attr("disabled", "true");
	       //   maxtime = 60;
	        //   timer = window.setInterval(CountDown, 1000);
			         $.get("modifyUser!modifyUserMesSendCode.do",{userId:userId},function(data){
				             if(data=="success"){
				             //    clearInterval(timer);
				                 alert("验证码已经发送，如果五分钟之内没有收到，请重新获取。");
				              }else{
				             //    clearInterval(timer);
				            //     $(".sendCodeButton").removeAttr("disabled");
				                 $(".sendCodeButton").text("重新获取验证码"); 
				                 alert("验证码发送失败");
				              }
			    });
	       }
	     });
	  
	    $("#form_submit").click(function(){
	         var codeTemp = $("#code").val();
	         var mobile = $("#mobile").val();
	         var mobileTemp = $("#mobileTemp").val();
	         if(mobileTemp!==mobile){
		        if(codeTemp==""){
		            $(".show_verifiCode_mes").text("请输入验证码");
		            return;
		        }else{
			        $.get("modifyUser!modifyUser.do",$("#struts_form").serialize(),function(data){
			            if(data=="error"){
			               $(".show_verifiCode_mes").text("验证码输入有误");
			            }else{
			                strutsForm.submit();
			            }
			        });
		        }
	        }
	    });
	    $("#reset_paw_id").click(function(){
	        var codeTemp = $("#code_002").val();
	        if(codeTemp==""){
	            $(".show_verifiCode_mes_002").text("请输入验证码");
	        }else{
		        $.get("/MPRS/usersutils/resetPassword.do",$("#reset_form").serialize(),function(data){
		            if(data=="error"){
		               $(".show_verifiCode_mes_002").text("验证码输入有误");
		            }else{
		               reset_form.submit();
		            }
		        });
	        }
	    });
	    $("#reset_bpaw_id").click(function(){
	        var codeTemp = $("#code_003").val();
	        if(codeTemp==""){
	            $(".show_verifiCode_mes_003").text("请输入验证码");
	        }else{
		        $.get("/MPRS/usersutils/resetBusinessPassword.do",$("#resetbusiness_form").serialize(),function(data){
		            if(data=="error"){
		               $(".show_verifiCode_mes_003").text("验证码输入有误");
		            }else{
		               resetbusiness_form.submit();
		            }
		        });
	        }
	    });
	  })
	  function show(tag){
		 var light=document.getElementById(tag);
		 light.style.display='block';
		 }
	function hide(tag){
	 var light=document.getElementById(tag);
	 light.style.display='none';
	}
	</script>
				<style>

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 45%;
	width: 300px;
	padding: 6px 16px;
	border: 12px solid #D6E9F1;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: #f5f5f5;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.close {
	float: right;
	clear: both;
	width: 100%;
	text-align: right;
	margin: 0 0 6px 0
}

.close a {
	color: #333;
	text-decoration: none;
	font-size: 14px;
	font-weight: 700
}

.con {
	text-indent: 1.5pc;
	line-height: 21px
}
</style></head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="modifyUser!modifyUser" namespace="/users" method="post" id="struts_form">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="userId"></s:hidden>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>用户信息修改</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      <c:choose>
      <c:when test="${sessionScope.user.usergroup.groupType==1}">
  	  <tr>
  	    <td width="145" align="right">
								用户组别:
							</td>
					  	    <s:hidden name="modifyUsers.usergroup.id" id="userId"></s:hidden>
					  	    <s:hidden name="modifyUsers.usergroup.groupType"></s:hidden>
							<td align="left" class="nowrap">
								<s:textfield name="modifyUsers.usergroup.groupName"
									id="userName" readonly="true" />
								<input icon="icon-btncom" type="button" value="选择"
									onclick='showGroupForLog();'/>
							</td>
							<td>&nbsp;</td>
					        <td>&nbsp;</td>
					        <td>&nbsp;</td>
      </tr>
      </c:when>
      <c:otherwise>
       <tr>
  	    <td align="right">所属组ID:</td>
  	    <td align="left">
			<s:hidden name="modifyUsers.usergroup.id" />
			${modifyUsers.usergroup.groupName }
		</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td> 
      </tr>
      </c:otherwise>
      </c:choose>
  	  <tr>
  	    <td align="right">用户名:</td>
  	    <td align="left"><s:textfield name="modifyUsers.userName"/></td>
  	    <td align="right" >email:</td>
  	   	<td align="left"><s:textfield name="modifyUsers.email"/></td>
        <td>&nbsp;</td> 
      </tr>
      	
  	  <tr>
  	    <td align="right">手机号码:</td>
  	   	<td align="left" ><s:textfield name="modifyUsers.mobile" id="mobile"/>
  	   	                  <input type="hidden" id="mobileTemp" value="${modifyUsers.mobile}"/>
  	   	</td>
         <td align="right" >性别:</td>
  	    <td align="left"><s:select cssClass="txtW2" name="modifyUsers.gender" list="%{#{0:'===请选择性别===',1:'男',2:'女'}}"/></td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">可冲正次数:</td>
  	   	<td align="left" ><s:textfield name="modifyUsers.reversalCount"/></td>
        <td align="right">是否显示在线充值:</td>
  	   	<td align="left" >
  	   	   <s:radio list="%{#{1:'显示',0:'不显示'}}" name="modifyUsers.isShowRecharge"></s:radio>
        </td>
       
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">最大可冲正次数:</td>
  	   	<td align="left" ><s:textfield name="modifyUsers.maxReversalCount"/></td>
        <td>QQ号码：</td>
  	    <td> <input type="text" name="modifyUsers.qq" value="${modifyUsers.qq}"/></td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >备注:</td>
  	   <td colspan="4" align="left"><s:textarea name="modifyUsers.remark" cssClass="txtW5" rows="4"></s:textarea></td>
      </tr>
      <tr>
  	   <td align="right" >输入验证码：</td>
  	   <td colspan="4" align="left"><s:textfield name="verificationCode" id="code"/>
  	    <button  class="sendCodeButton" type="button" >发送验证码</button> <span class="show_verifiCode_mes" style="color: red;"></span>
  	    </td>
      </tr>
      
      <tr>
  	    <td colspan="4" align="center">
  	     <button id="form_submit">提交</button>
  	     <button type="reset" > 重置</button>
  	    <button type="button"   onclick="show('light2')">重置登录密码</button>
  	    <button type="button"   onclick="show('light3')">重置交易密码</button>
  	    <!-- 
  	    <s:reset icon="icon-reload" value="重置"></s:reset>&nbsp;
  	     <input type="button" value="提交" id="form_submit"/>
  	    <input type="button" value="重置密码" onclick="window.location.href='${pageContext.request.contextPath}/usersutils/resetPassword.do?userId=${userId }'"/>
  	    <input type="button" value="重置登录密码" onclick="show('light2')"/>
  	    <input type="button" value="重置交易密码" onclick="show('light3')"/>
  	     -->
  	    </td>
        <td>&nbsp;</td> 
      </tr>
		
  </table>
  </s:form>
  <div id="light2" class="white_content">
      <div class="close"><a href="javascript:void(0)" onclick="hide('light2')"> 关闭</a></div>
      <div class="con"> 
      <form action="/MPRS/usersutils/resetPassword.do" name="reset_form" id="reset_form">
        <table width="95%">
           <tr>
             <td> 输入验证码：</td>
             <td> <s:textfield name="verificationCode" id="code_002"/> 
                  <s:hidden name="userId" ></s:hidden>
             </td>
           </tr>
           <tr>
              <td colspan="2"><span class="show_verifiCode_mes_002" style="color: red;"></span></td>
           </tr>
           <tr>
             <td colspan="2"> &nbsp; </td>
           </tr>
           <tr>
             <td  align="center"><button class="sendCodeButton" type="button">发送验证码</button></td>
             <td  align="center"><button type="button" id="reset_paw_id">提交</button></td>
           </tr>
        </table>
         </form>
  	    
      </div>
</div>
  <div id="light3" class="white_content">
      <div class="close"><a href="javascript:void(0)" onclick="hide('light3')"> 关闭</a></div>
      <div class="con"> 
      <form action="/MPRS/usersutils/resetBusinessPassword.do" name="resetbusiness_form" id="resetbusiness_form">
        <table width="95%">
           <tr>
             <td> 输入验证码：</td>
             <td> <s:textfield name="verificationCode" id="code_003"/> 
                  <s:hidden name="userId" ></s:hidden>
             </td>
           </tr>
           <tr>
              <td colspan="2"><span class="show_verifiCode_mes_003" style="color: red;"></span></td>
           </tr>
           <tr>
              <td colspan="2" > &nbsp; </td>
           </tr>
           <tr>
             <td  align="center"><button class="sendCodeButton" type="button">发送验证码</button></td>
           
             <td  align="center"><button type="button" id="reset_bpaw_id">提交</button></td>
           </tr>
        </table>
         </form>
  	    
      </div>
</div>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
