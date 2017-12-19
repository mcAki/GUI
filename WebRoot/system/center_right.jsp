<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/incHead.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>妙鸣贸易</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Robots" content="all" />
<meta name="Keywords" content="妙鸣贸易" />
<meta name="Description" content="妙鸣贸易" />
<script type="text/javascript" src="js/jquery17mis.js"></script>
<link href="css/main_new.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
  function readyNews(id){
     
     $.post("othermes/selBackOneNews.do",{id:id},function(data){
           $(".xtsm").html("<strong>"+data.title+"</strong><p style='font-size: 14px;'>"+data.message+"</p>");
     });
      
  } 
</script>

<style >
  .news strong{
    font-size: 18px;
  }
  .fixed a{
    font-size: 14px;
  }
</style>
</head>

<body>

<div id="comInfo">         
   <div id="contain" class="fixed">
       <div class="top">
       <%--
                 <p><span class="history1">上次登录时间：2012-11-02  14：53：23</span><br/>
                 &nbsp; <p class="xywl"><a href="/MPRS/usersutils/viewUserProfile.do">${user.userName }</a>&nbsp;用户编号：${user.userCode }您好, 欢迎使用妙鸣在线！</p></p>
        --%>   
          <p>
                 <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td ><a href="/MPRS/usersutils/viewUserProfile.do">${user.userName }</a>&nbsp;用户编号：${user.userCode }您好, 欢迎使用妙鸣在线！ </td>
                       <td align="right"><span class="history1"> </span></td>
                    </tr>
                    <tr>
                      <td align="right"> </td>
                      <td align="right"><span class="history"> </span></td>
                    </tr>
                 </table>
          </p>
                 
                 <table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="250" style="color:#777777">当前余额：<strong class="yuan">
                           <fmt:formatNumber maxFractionDigits="2" value="${useraccount.balance }"/>
                           </strong>元，佣金余额：<strong class="yuan">
                           <fmt:formatNumber maxFractionDigits="2" value="${useraccount.commission }"/>
                           </strong>元
                         <c:if test="${user.isShowRecharge==1}">
                        <a href="alipayCharge/alipayPage.do"><img src="img/bt_cose.jpg" width="52" height="19"  alt=""/></a>
                        </c:if>
                           
                           </td>
                        <%--
                        <td width="50">
                        </td>
                        <td  align="right" style="color:#777777"><span class="history">历史登录时间：2012-11-01  10：12：55</span></td>
                         --%>
                      </tr>
                </table>
              </div>
       <div class="news fixed">
                        <div class="xtsm">
                                  <strong>${otherMesTopNewFirst.title }</strong>
                                  <p style="font-size: 14px;">${otherMesTopNewFirst.message }</p>
                        </div>
                        <div class="zxgg">
                                <strong>最新公告</strong>
                                <ul class="fixed">
                                    <s:iterator value="#session.otherMesTopNewList">
                                      <li><span> <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd" /></span><a href="javascript:;" onclick="readyNews(${id})"> ${title }</a> </li>
                                    </s:iterator>
                                    <s:iterator value="#session.otherMesCommonNewList">
                                      <li><span> <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd" /></span><a href="javascript:;" onclick="readyNews(${id})">${title }</a></li>
                                    </s:iterator>
                                    <%-- 
                                      <li><span>2012-09-20</span><a href="javascript:;" onclick="readyNews(2)">控件下载 </a></li>
                                      <li><span>2012-04-14</span><a href="javascript:;" onclick="readyNews(3)">全国充值查询处理流程</a></li>
                                      <li><span>2012-01-09</span><a href="javascript:;" onclick="readyNews(4)">充值成功，用户不到账</a></li>
                                      <li><span>2012-01-09</span><a href="javascript:;" onclick="readyNews(5)">财付通转账疑问</a></li>
                                      <li><span>2012-01-09</span><a href="javascript:;" onclick="readyNews(6)">打开网站速度为什么这么慢</a></li>
                                      <li><span>2012-09-20</span><a href="javascript:;" onclick="readyNews(7)">控件下载</a></li>
                                      <li><span>2012-04-14</span><a href="javascript:;" onclick="readyNews(8)">全国充值查询处理流程</a></li>
                                    --%> 
                                </ul>
                        </div>
                </div>
       <div id="contact">
                     <p><strong>广州市妙鸣贸易有限公司</strong>&nbsp;&nbsp;&nbsp;联系方式     邮箱：4000307517@b.qq.com&nbsp;&nbsp;&nbsp;电话：4000307517</p>
                </div>
   </div>
</div>

</body>
</html>
