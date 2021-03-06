<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>title</title>
<%@ include file="../common/incHead.jsp" %>
<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery17mis.js"></script>
<link href="css/main_new.css" rel="stylesheet" type="text/css" />
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #F5FFEA;
	margin: 0px;
}

a{blr:expression(this.onFocus=this.close());} /* 只支持IE，过多使用效率低 */
a{blr:expression(this.onFocus=this.blur());} /* 只支持IE，过多使用效率低 */
a:focus { -moz-outline-style: none; } /* IE不支持 */

#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(images/menu_bgs.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>

</head>

<body style="background:#D5EAF8;">
<!-- 
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" onselectstart="return false;">
  <tr>
    <td width="182" valign="top"><div id="container">
	<s:iterator value="#session.menuTree">
      <h1 class="type"><a href="javascript:void(0)"  onFocus="this.blur()"><s:property value="name"/></a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
			<s:iterator value="subtrees" id="subtree">
         	 <li><a href="${subtree.url}" target="main" onFocus="this.blur()">${subtree.name }</a></li>
			</s:iterator>
        </ul>
      </div>
	</s:iterator>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_bg_bottom.gif" width="182" height="15" /></td>
          </tr>
        </table>
    </div>
       
        <script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[0]);
	</script>
        </td>
  </tr>
</table>
 -->
 <div id="leftside1" class="fixed">
   <div class="shgl1" id="shgl1">
         <div class="shgl">
             <ul class="fixed">
               <s:iterator value="#session.menuTree" status = "counts">
	               <c:choose>
		               <c:when test="${counts.index==0 }">
		               <li class="sel" rel="#shgl${counts.index }"><a href="javascript:void(0)"  onFocus="this.blur()"><s:property value="name"/></a>
		                 <ul class="cur fixed" id="${counts.index }">
			                     <s:iterator value="subtrees" id="subtree">
			                             <li onmouseover="this.className='cur'" onmouseout="this.className=''"><a target="mainrig"  href="${subtree.url}">${subtree.name }</a></li>
						         </s:iterator>
		                      </ul>
		               </li>
		               </c:when>
		               <c:when test="${counts.index>=0 }">
		               <li  rel="#shgl${counts.index }"><a href="javascript:void(0)"  onFocus="this.blur()"><s:property value="name"/></a>
		                      <ul class="fixed" id="${counts.index }">
			                     <s:iterator value="subtrees" id="subtree">
			                             <li onmouseover="this.className='cur'" onmouseout="this.className=''"><a target="mainrig"  href="${subtree.url}">${subtree.name }</a></li>
						         </s:iterator>
		                      </ul>
		                     
		               </li>
		               </c:when>
	               </c:choose>
             </s:iterator>
           </ul>
         </div>
             <br/>
             <br/>
             <center>
             </center>
             <!-- 
              -->
               <a target="mainrig" href="/MPRS/customerFeedback/page!saveCfbPage.do"> <img src="/MPRS/img/feedback.png" alt="" width="80" style="margin-left: 20px;"/></a>
     </div>
<script>
$(document).ready(function(){	
	$(".shgl>ul>li").click(function(){
				$(this).addClass("sel").siblings("li").removeClass("sel");
				$(this).children("ul").show();
				$(this).siblings("li").children("ul").hide()	
		}) 
    	$(".shgl ul li ul a").click(function(){
			   $(".shgl ul li ul a").css("color","#5f5f5f");
			     $(".shgl ul li ul a").css("font-weight","normal");
				 $(this).css("color","#488ed7");
				 $(this).css("font-weight","bold");
			});
				$(".area1>a").click(function(){
				$(this).addClass("cur").siblings("a").removeClass("cur");
				$($(this).attr("rel")).show().siblings(".cont_area").hide()
		})

})
</script>
</body>

<script>
document.onselectstart=function(e){
    var evt=e||event;
    var srcEl=evt.srcElement||evt.target;
    var tagName=srcEl.tagName;        
    
    return tagName.toLowerCase()=="textarea"
};
</script>
</html>
