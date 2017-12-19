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
    

  </head>
  
  <body>
    <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
              <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed" style="min-height: 500px;">
                     <div class="cxjj fixed">
                            <p class="jjcx">增加信息</p>
                     </div>
                     <div class="country communi" >
                                   <table style="width: 100%">
                                      <tr>
                                         <td width="100px;"> <strong>类型</strong></td>
                                         <td>
                                         
                                         <s:iterator value="#session.listType" >
                                         <c:choose>
							                <c:when test="${otherMes.typeId == typeId}">
							                    ${typeName }
                                             </c:when>
                                          </c:choose>  
                                         </s:iterator>
                                          </td>
                                      </tr>
                                      <tr>
                                         <td height="30px;">&nbsp; </td>
                                      </tr>
                                      
                                       <tr>
                                        <td>标题：</td>
                                         <td>
                                           <textarea rows="2" cols="80" class="beizhu" name="title" readonly="readonly">${otherMes.title }</textarea> 
                                         </td>
                                      </tr>
                                       <tr>
                                         <td height="30px;">&nbsp; </td>
                                      </tr>
                                      <tr>
                                          <td>置顶：</td>
                                          <td>
                                            <c:choose>
                                                 <c:when test="${otherMes.newTop==0}">
                                                     	否
                                                 </c:when>
                                                 <c:when test="${otherMes.newTop==1}">
                                                   		   是
                                                 </c:when>
                                            </c:choose>
                                          </td>
                                       </tr>
                                       <tr>
                                         <td height="30px;">&nbsp; </td>
                                      </tr>
                                      <tr>
                                          <td>URL：</td>
                                         <td>
                                             <input value="${otherMes.url }"/>
                                         </td>
                                      </tr>
                                      <tr>
                                         <td height="30px;">&nbsp; </td>
                                      </tr>
                                      <tr>
                                         <td><strong>内容</strong> </td>
                                         <td> <textarea rows="10" cols="80" class="beizhu"  name="message" readonly="readonly">${otherMes.message } </textarea> 
                                          </td>
                                      </tr>
                                   </table>
                     </div>
                    </div>
             </div>
          </div>
  </body>
</html>
