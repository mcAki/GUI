<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<HTML>
<head>
<title>网站管理员登陆</title>
<%@ include file="../common/incHead.jsp" %>
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
<script language="JavaScript">

	function swarp(){
		var shgl1 = document.getElementById("shgl1");
		if(shgl1.style.display=="none"){
					$(".shgl1").show();
					$("#comInfo2").css("width","84%");
			        $("#leftside").css("width","16%"); 
   					$(".close").css("left","210px");
				    $("#contain").css("width","97%");
		         }
		else{
				    $(".shgl1").hide();
					$("#comInfo2").css("width","100%");
				    $("#leftside").css("width","1%"); 
   					$("#contain").css("width","97%");
					$("#contain").css("margin","0px auto");	
   					$(".close").css("left","1px");
		}
	}

function sendMSM(){
    var loginName = document.getElementById("loginName").value;
   
    loginName = encodeURI(encodeURI(loginName));
    
    $.post("/MPRS/login!toMolibeJson.do?",{loginName:loginName},function(data){
       alert(data.message);
    },'json');
}

function refreshVerifyCode(){
	var i = Math.random(); 
	var idVerifyCode= document.getElementById("idVerifyCode");
    idVerifyCode.style.display="block";
    idVerifyCode.src="verify.do?rnd="+i;
}

function correctPNG()
{
    var arVersion = navigator.appVersion.split("MSIE")
    var version = parseFloat(arVersion[1])
    if ((version >= 5.5) && (document.body.filters)) 
    {
       for(var j=0; j<document.images.length; j++)
       {
          var img = document.images[j]
          var imgName = img.src.toUpperCase()
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
          {
             var imgID = (img.id) ? "id='" + img.id + "' " : ""
             var imgClass = (img.className) ? "class='" + img.className + "' " : ""
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
             var imgStyle = "display:inline-block;" + img.style.cssText 
             if (img.align == "left") imgStyle = "float:left;" + imgStyle
             if (img.align == "right") imgStyle = "float:right;" + imgStyle
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle
             var strNewHTML = "<span " + imgID + imgClass + imgTitle
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
             img.outerHTML = strNewHTML
             j = j-1
          }
       }
    }    
}
$(document).ready(function(){
   $("#divselect5").click(function(){
      var userType = $("#inputselect").val();
			if(userType==3){
				$("#mobile_v").show();
				$("#key_v").hide();
			}
			if(userType==2){
				$("#mobile_v").hide();
				$("#key_v").show();
			}
    });
})
//window.attachEvent("onload", correctPNG);
</script>
<!-- 
<link href="images/skin.css" rel="stylesheet" type="text/css">
 -->

</head>
<body>
<%-- 
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21"><br /></td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">登陆信息网后台管理</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle"><form name="myform" action="login!doLoginCard.html" method="post">
                        <table width="500" height="143" border="0" align="left" cellPadding="0" cellSpacing="0" style="text-align:left;">
                           <tr>
                            <td height="50" >商户类型：&nbsp;&nbsp;</td>
                            <td height="38" colspan="2" ><s:select name="userType" value="0" cssStyle="width:188px;"  list="%{#{0:'===选择商户类型===',2:'普通商户',3:'终端机'}}"></s:select> </td>
                          </tr>
                          <tr>
                            <td width="75" height="50" >用　户：&nbsp;&nbsp;</td>
                            <td height="38" colspan="2" ><input name="user.loginName" id="loginName" style="width:153px;" value="${user.loginName}" class="editbox4" size="20"></td>
                          </tr>
                          <tr>
                            <td height="50" >密　码：&nbsp;&nbsp;</td>
                            <td height="35" colspan="2" ><input name="user.userPassword" value="${user.userPassword}" style="width:153px;" class="editbox4" type="password" size="20">&nbsp;&nbsp;<img src="images/luck.gif" width="19" height="18"> </td>
                          </tr>
                          <tr>
                            <td height="50" >验证码：&nbsp;&nbsp;</td>
                            <td height="35" colspan="2" style="line-height:35px;"><input name="verifycode" id="inputVerifyCode" type=text value="${verifycode}" maxLength=5 style="float:left;width:69px; height:24px;" onfocus="refreshVerifyCode();"><div style="float:left;">&nbsp;&nbsp;</div><img id="idVerifyCode" style="float:left; cursor:pointer;display: none;" onclick="inputVerifyCode.focus();"/></td>
                          </tr>
                          <tr>
                            <td width="75" height="50" >手机短信验证码：&nbsp;&nbsp;</td>
                            <td height="38" colspan="2" >
                                      <input name="keyId" style="width:153px;" class="editbox4" value="${keyId}" size="20">
                                      <img border="0" src="${pageContext.request.contextPath}/img/phone.gif" style="cursor:hand" title="获取手机短信验证码" alt="获取手机短信验证码" onClick="sendMSM()">
                            </td>
                          </tr>
                          <tr>
                            <td height="50" >&nbsp;</td>
                            <td width="128" height="35" ><input icon="icon-apply" name="Submit" type="submit" class="button" id="Submit" value="登 陆"> </td>
                            <td width="419" ><input icon="icon-cancel" name="cs" type="button" class="button" id="cs" value="取 消" onClick="showConfirmMsg1()"></td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="122" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2010</span></td>
      </tr>
    </table></td>
  </tr>
</table>
 --%>
 <div id="header">
     <div id="head">
         <div id="nav">
            <img src="img/pic_header2.jpg" width="189" height="27"  alt=""/> 
            <img src="img/pic1_header.jpg" width="189" height="27"  alt=""/>
            <img  style="CURSOR: pointer" onclick="javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=4000307517&eid=218808P8z8p8Q8y8x808x&o=&q=7&ref='+document.location, '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');"  border="0" SRC="img/pic2_header.jpg">
      </div>
         <div id="logo">
             <a href="javascript:window.top.location.href='/';"><img src="img/logo.png" width="360" height="65"  alt=""/></a>
         </div>
     </div>
</div>
<div id="login" class="fixed">
       <div id="loginbox">
               <form name="myform" action="login!doLoginCard.html" method="post">
               <p>
                  <strong style="color:red;">注：</strong>
                                           铁通, 联通,网通宽带用户请点击这里<a href="http://58.249.62.227:8123/MPRS/login.do"> 登录</a>
               </p>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                             <tr height="40">
                                    <td style="color:#4c6579" width="15%">登录方式：</td>
                                    <td width="80%">
                                                  <div id="divselect5" class="divselect2 divselect">
                                                        <cite>选择登录方式</cite>
                                                             <ul>
                                                                 <li><a href="javascript:;" selectid="2">UKey方式</a></li>
                                                                 <li><a href="javascript:;" selectid="3">短信方式</a></li>
                                                              </ul>
                                                  </div>
                                    
                                                 <input name="userType" type="hidden" value="" id="inputselect"/>
                                         
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect5","#inputselect");
														
													});
													</script>
                                     </td>
                              </tr>
                        </table>
               
                        
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                              <tr height="40">
                                    <td width="16%" style="color:#4c6579">用&nbsp;&nbsp;&nbsp;&nbsp;户：</td>
                                    <td width="79%"><input name="user.loginName" value="${user.loginName}" type="text" id="loginName" class="user"/></td>
                                    <td width="5%">&nbsp;</td>
                              </tr>
                              <tr height="40">
                                    <td style="color:#4c6579">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                                    <td><input name="user.userPassword" value="${user.userPassword}" type="password"  class="passwork user"/></td>
                                    <td>&nbsp;</td>
                              </tr>
                        </table>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                              <tr height="40">
                                    <td width="25%" style="color:#4c6579">验 证 码：</td>
                                    <td width="12%"><input name="verifycode" value="${verifycode }"  type="text"  class="sjyz" onfocus="refreshVerifyCode();"/></td>
                                    <td width="63%"><img id="idVerifyCode" style="float:left; cursor:pointer; display: none;" src="" onclick="inputVerifyCode.focus();"/></td>
                              </tr>
                              <tr height="40" id="mobile_v" style="display: none;">
                                    <td style="color:#4c6579">手机验证：</td>
                                    <td><input name="keyId" type="text"  size="20" class="sjyz"/></td>
                                    <td><a href="javaScript:;" class="sjyz" onClick="sendMSM()">点击发送手机验证码</a></td>
                              </tr>
                               <tr height="40" id="key_v" style="display: none;">
		                            <td style="color:#4c6579">加密&nbsp;key：</td>
		                            <td><input name="keyId"  class="sjyz" size="20"></td>
		                            <td>&nbsp;</td>
	                          </tr>
                        </table>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top:22px;">
                              <tr height="40">
                                <td width="34%" colspan="2" align="center" valign="top">
                                <button type="submit" class="denglu" ></button>
                                </td>
                                <td>&nbsp; </td>
                                <td width="65%">
                                <%--
                                <a href="#"><img src="img/pic_zhuce.gif" width="105" height="34"  alt=""/></a>
                                 --%>
                                </td>
                              </tr>
                              
                        </table>
               </form>
       </div>
</div>
<div id="footer">
      <p>Copyright © 2003-2012 妙鸣在线(517di.com). All Rights Reserved</p>
</div> 

</body>
</HTML>
