<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<HTML>
<head>
<%@ include file="../common/incHead.jsp" %>
<title>网站管理员登陆</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color:#102F00;
}
-->
</style>
<script language="JavaScript">
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
window.attachEvent("onload", correctPNG);
</script>
<link href="images/skin.css" rel="stylesheet" type="text/css">

</head>
<body>
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
                    <td height="164" colspan="2" align="middle"><form name="myform" action="login!doLogin.html" method="post">
                        <table width="500" height="143" border="0" align="left" cellPadding="0" cellSpacing="0" style="text-align:left;">
                          <tr>
                            <td width="75" height="50" >用　户：&nbsp;&nbsp;</td>
                            <td height="38" colspan="2" ><input name="user.loginName" style="width:153px;" class="editbox4" value="" size="20"></td>
                          </tr>
                          <tr>
                            <td height="50" >密　码：&nbsp;&nbsp;</td>
                            <td height="35" colspan="2" ><input name="user.userPassword" style="width:153px;" class="editbox4" type="password" size="20">&nbsp;&nbsp;<img src="images/luck.gif" width="19" height="18"> </td>
                          </tr>
                          <tr>
                            <td height="50" >验证码：&nbsp;&nbsp;</td>
                            <td height="35" colspan="2" style="line-height:35px;"><input name="verifycode" id="inputVerifyCode" type=text value="" maxLength=5 style="float:left;width:69px; height:24px;" onfocus="refreshVerifyCode();"><div style="float:left;">&nbsp;&nbsp;</div><img id="idVerifyCode" style="float:left; cursor:pointer;display: none;" onclick="inputVerifyCode.focus();"/></td>
                          </tr>
                          <tr>
                            <td width="75" height="50" >加密key：&nbsp;&nbsp;</td>
                            <td height="38" colspan="2" ><input name="keyId" style="width:153px;" class="editbox4" value="" size="20">&nbsp;如没有绑定请填写'000000'</td>
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
</HTML>
