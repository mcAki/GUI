<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
 <%@ include file="../common/incBanner.jsp" %>
		<%@ include file="../common/incHead.jsp"%>
		<%@ include file="../common/incFooter.jsp" %>
		<script type="text/javascript" src="../js/divselect.js"></script>           
<script type="text/javascript" src="../js/calendar/calendar.js"></script>
    
  <script type="text/javascript">
     
  </script>

  </head>
  
  <body>
    <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
              <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                     <div class="cxjj fixed">
                            <p class="jjcx">留言列表</p>
                     </div>
                     <div class="country communi" style="width:100%">
                            <table class="list_tb_002" style="width:100%;" align="center">
                              <tr style="height: 32px;">
                                 <th>标题</th>
                                 <th>留言用户名</th>
                                 <th>留言用户电话</th>
                                 <th>留言时间</th>
                                 <th>操作</th>
                              </tr>
                              <s:iterator value="#session.customerFeedbackList" var="list1">
                                 <tr>
                                     <td>
                                        <a href="/MPRS/customerFeedback/findOneById.do?cfId=${cfId }"> ${cfTitle } </a>
                                     </td>
                                     <td>
                                         ${userName }
                                     </td>
                                     <td>${userPhone} </a></td>
                                     <td>${cfTime } </td>
                                     <td>
                                       <a href="/MPRS/customerFeedback/findOneById.do?cfId=${cfId }"> 查看 </a>|
                                       <a href="/MPRS/customerFeedback/delCfb.do?cfId=${cfId }"> 删除 </a>
                                     </td>
                                 </tr>
                              </s:iterator>
                               
                            </table>
                     </div>
                    </div>
             </div>
          </div>
  </body>
</html>
