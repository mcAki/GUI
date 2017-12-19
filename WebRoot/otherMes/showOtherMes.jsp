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
<script type="text/javascript" src="../js/calendar/lang/en.js"></script>
<link rel="stylesheet" type="text/css" href="../js/calendar/jscal2.css"/>
<link rel="stylesheet" type="text/css" href="../js/calendar/border-radius.css"/>
<link rel="stylesheet" type="text/css" href="../js/calendar/win2k.css"/>
    
  <script>
     $(document).ready(function(){
        $("#show_choseType").click(function(){
            $("#divselect12").slideToggle();
        });
     })
  </script>

  </head>
  
  <body>
    <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed" style="min-height: 500px;">
                     <div class="cxjj fixed">
                            <p class="jjcx">查看公告</p>
                     </div>
                      <div class="country communi">
                        <center>
                        <p> <strong> ${otherMes.title }</strong>  </p>
                        <p> ${otherMes.createTime } </p>
                        <p> ${otherMes.message}  </p>
                        </center>
                      <!-- 
                          <table>
                             <tr>
                               <td> 标题：</td>
                               <td> ${otherMes.title }</td>
                             </tr>
                             <tr>
                               <td> 发布时间：</td>
                               <td> ${otherMes.createTime }</td>
                             </tr>
                             <tr>
                               <td> 内容：</td>
                               <td> ${otherMes.message }</td>
                             </tr>
                          </table>
                       -->
                      </div>
               </div>
          </div>
    </div>
  </body>
</html>
