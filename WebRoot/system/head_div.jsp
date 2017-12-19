<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript"> 

	function AutoScroll(obj) {
		$(obj).find("ul:first").animate( {
			marginTop : "-25px"
		}, 500, function() {
			$(this).css( {
				marginTop : "0px"
			}).find("li:first").appendTo(this);
		});
	}
	<%--function selOtherMes() {
		$.ajax( {
			type : 'post',
			url : '/MPRS/login/selOtherMesTopNewList.do',
			dataType : 'json',
			success : function(data) {
				$("#scrollDivul").html(""); //清空
				$.each(data, function(i, item) {
						$("#scrollDivul").append("<li> <a href='/MPRS/othermes/showOtherMes.do?id="+item.id+"'>"+
						item.title+" </a> <span  style='float:right; color:#777777'>"+
						format(item.createTime.time, 'yyyy-MM-dd HH:mm')+"</span></li> ");
					});
			},
			error : function(text) {
				$("#scrollDivul").html("未获取信息 ");
			}
		});
	}--%>
	$(document).ready(function() {
		setInterval('AutoScroll("#scrollDiv")', 2000);
		//setInterval('selOtherMes()',30000);
		})
</script> 
	<style type="text/css"> 
		ul,li{margin:0;padding:0} 
		#scrollDiv{position:relative;top:-25px;left:100px; width:230px;;height:25px;line-height:25px;overflow:hidden} 
		#scrollDiv li{height:25px;padding-left:10px;} 
	</style>
  </head>
  
  <body  >
     <div class="top_mem">
                   <table width="620" border="0" cellpadding="0" cellspacing="0">
                      <tr valign="top">
                      <%--
                      
                      <td  valign="top" style="width:480px; color:#777777;">
                      <p class="xywl"><a href="/MPRS/usersutils/viewUserProfile.do">${user.userName }</a>&nbsp;用户编号：${user.userCode }&nbsp;&nbsp;您好, 欢迎使用妙鸣在线！&nbsp;&nbsp;&nbsp;<a href="/MPRS/frame!main_front.do" target="_top">首页</a>
                        --%>
                       <%--
                        <td   style="color:#777777">余额：<strong class="yuan">${useraccount.balance }</strong>元</td>
                        
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <c:if test="${user.isShowRecharge==1}">
                        <a href="/MPRS/alipayCharge/alipayPage.do"><img src="<%=basePath %>img/bt_cose.jpg" width="52" height="19"  alt=""/> </a></p></td>
			             </c:if>
			             --%>
			             <td>
			               <a href="/MPRS/usersutils/viewUserProfile.do">${user.userName }</a>&nbsp;</td>
			               <td>
			               用户编号：${user.userCode }&nbsp;&nbsp;您好, 欢迎使用妙鸣在线！</td>
			               <td>
			               <a href="/MPRS/frame!main_front.do" target="_top">首页</a>
			             </td>
			             <c:if test="${user.isShowRecharge==1}">
			             <td>
                        <a href="/MPRS/alipayCharge/alipayPage.do"><img src="<%=basePath %>img/bt_cose.jpg" width="52" height="19"  alt=""/> </a></td>
			             </c:if>
			             
                        <td ><p class="zxtx" style=" color:#777777;">
                             <span>最新提醒：</span>
                       		  <div id="scrollDiv"> 
									<ul id="scrollDivul"> 
                       		       <s:iterator value="session.otherMesTopNewList">
										<li><a href="/MPRS/othermes/showOtherMes.do?id=${id}"> ${title } </a><span  style="float:right; color:#777777"><fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd" /></span></li> 
                                    </s:iterator>
									</ul> 
							  </div>                                 
                           </p>
                        </td>
			             
                      </tr>
                </table>
             </div>
  </body>
</html>
