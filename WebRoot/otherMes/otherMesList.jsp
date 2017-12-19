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
    
  <script type="text/javascript">
     
  </script>

  </head>
  
  <body>
    <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
          
              <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                     <div class="cxjj fixed">
                            <p class="jjcx">增加信息</p>
                     </div>
                     <div class="country communi" style="width:100%">
                            <table class="list_tb_002" style="width:90%;" align="center">
                              <tr style="height: 32px;">
                                 <th>类型</th>
                                 <th>置顶</th>
                                 <th>公告主题</th>
                                 <th>发布时间</th>
                                 <th>操作</th>
                              </tr>
                              <s:iterator value="#session.otherMesList" var="list1">
                                 <tr>
                                     <td>
                                        <s:iterator value="#session.listType" var = "list2" >
                                         <c:choose>
							                <c:when test="${list1.typeId == list2.typeId}">
							                    ${typeName }
                                             </c:when>
                                          </c:choose>  
                                         </s:iterator>
                                     </td>
                                     <td>
                                        <c:choose>
                                                 <c:when test="${newTop==0}">否</c:when>
                                                 <c:when test="${newTop==1}">是</c:when>
                                            </c:choose>
                                     </td>
                                     <td><a href="/MPRS/othermes/findOneResult.do?id=${id }">  ${title} </a></td>
                                     <td> <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                     <td>
                                       <a href="/MPRS/othermes/updateOtherMesPage.do?id=${id }"> 修改 </a>|
                                       <a href="/MPRS/othermes/findOneResult.do?id=${id }"> 查看 </a>|
                                       <a href="/MPRS/othermes/delOtherMes.do?id=${id }"> 删除 </a>
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
