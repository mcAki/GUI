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
    

  </head>
  
  <body>
    <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
              <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed" style="min-height: 500px;">
                     <div class="cxjj fixed">
                            <p class="jjcx">留言信息</p>
                     </div>
                     <div class="country communi" >
                                   <table style="width: 80%;">
                                      <tr>
                                         <td height="30px;" colspan="2">&nbsp; </td>
                                      </tr>
                                      <tr>
                                         <td width="100px;" align="right"> <strong>标题：</strong></td>
                                         <td align="center">
                                            ${customerFeedback.cfTitle }
                                          </td>
                                      </tr>
                                      <tr>
                                         <td height="30px;" colspan="2">&nbsp; </td>
                                      </tr>
                                      
                                       <tr>
                                        <td align="right"><strong> 留言时间：</strong></td>
                                         <td align="center">
                                            ${customerFeedback.cfTime } 
                                         </td>
                                      </tr>
                                       <tr>
                                         <td height="30px;" colspan="2">&nbsp; </td>
                                      </tr>
                                      <tr>
                                          <td align="right"><strong>留言用户名：</strong></td>
                                          <td align="center">
                                           ${customerFeedback.userName }
                                          </td>
                                       </tr>
                                        <tr>
                                         <td height="30px;" colspan="2">&nbsp; </td>
                                      </tr>
                                      <tr>
                                          <td align="right"><strong>留言用户电话：</strong></td>
                                         <td align="center">
                                              ${customerFeedback.userPhone } 
                                         </td>
                                      </tr>
                                       <tr>
                                         <td height="30px;" colspan="2">&nbsp; </td>
                                      </tr>
                                      <tr>
                                         <td align="right"><strong>留言内容：</strong> </td>
                                         <td align="center">  ${customerFeedback.cfMessage }  
                                          </td>
                                      </tr>
                                   </table>
                     </div>
                    </div>
             </div>
          </div>
  </body>
</html>
