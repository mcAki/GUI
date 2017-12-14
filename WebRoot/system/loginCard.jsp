<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<HTML>
<head>
<link href="../css/criteria.css" rel="stylesheet" type="text/css">
<link href="../css/sys.css" rel="stylesheet" type="text/css">
<link href="images/skin.css" rel="stylesheet" type="text/css">
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


</head>
<body>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right" valign="top"><img src="images/logo.png" width="279" height="68"></td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="24%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt">最新公告消息</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><ul>
                        <li>登陆账号如果不能直接识别,请选定所属的证件类型</li>
                        <li>赛会期间，只允许队长级别登陆。</li>                        
                        <li>登陆验证码，无区分大小写</li>
                        <li><a href="${pageContext.request.contextPath}/demo/city1204.doc">城市志愿者操作指引V.1204</a></li>
                        <li><a href="${pageContext.request.contextPath}/demo/match1204.doc">赛会志愿者操作指引V.1204</a></li>
                        <li><a href="http://v.youku.com/v_show/id_XMjIzMzA4MTc2.html">城市站点志愿者考勤机使用视频</a></li>
                      </ul></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt">&nbsp;</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td width="41%" height="40">&nbsp;</td>
                      <td width="35%">&nbsp;</td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
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
                        <table width="500" height="250" border="0" align="left" cellPadding="0" cellSpacing="0" style="text-align:left;">
                          <tr>
                            <td height="50" >证件类型：&nbsp;&nbsp;</td>
                            <td height="38" colspan="2" ><s:select name="user.idcardType" value="255" cssStyle="width:188px;"  list="%{#{'255':'自动识别','0':'PAS-国际护照','1':'CID-中国居民身份证','2':'JGZ-中国人民解放军军官证或士兵证','3':'WJZ-中国武警证件','4':'TWT-台湾居民来往大陆通行证','5':'GAT-港、澳居民来往内地通行证','6':'GAB-公安现役警官证件或士兵证（边防系统）','7':'GAJ-公安现役警官证件或士兵证（警卫系统）','8':'GAX-公安现役警官证件或士兵证（消防系统）'}}"></s:select> </td>
                          </tr>
                          <tr>
                            <td width="102" height="50" >证件号码：&nbsp;&nbsp;</td>
                            <td height="38" colspan="2" ><input name="user.idcardCode" style="width:180px;" class="editbox4" value="" size="20"></td>
                          </tr>
                          <tr>
                            <td height="50" >用户密码：&nbsp;&nbsp;</td>
                            <td height="35" colspan="2" ><input name="user.userPassword" style="width:180px;" class="editbox4" type="password" size="20">&nbsp;&nbsp;<img src="images/luck.gif" width="19" height="18"> </td>
                          </tr>
                          <tr>
                            <td height="50" >验证码：&nbsp;&nbsp;</td>
                            <td height="35" colspan="2" style="line-height:35px;"><input name="verifycode" id="inputVerifyCode" type=text value="" maxLength=5 style="float:left;width:69px; height:24px;" onfocus="refreshVerifyCode();"><div style="float:left;">&nbsp;&nbsp;</div><img id="idVerifyCode" style="float:left; cursor:pointer;display: none;" onclick="inputVerifyCode.focus();"/></td>
                          </tr>
                          <tr>
                            <td height="50" >&nbsp;</td>
                            <td width="97" height="35" ><input icon="icon-apply" name="Submit" type="submit" class="button" id="Submit" value="登 陆"> </td>
                            <td width="301" ><input icon="icon-cancel" name="cs" type="button" class="button" id="cs" value="取 消" onClick="showConfirmMsg1()"></td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="351" height="164" align="right" valign="middle"><img src="images/default_logo.png" width="300" height="90"></td>
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
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2010     <a href="adminLogin.do">管理员入口</a></span> </td>
      </tr>
    </table></td>
  </tr>
</table>
</HTML>
