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
                            <p class="jjcx">修改信息</p>
                     </div>
                     <div class="country communi">
                             <s:form name="strutsForm" id="strutsForm" action="updateOtherMesAct" namespace="/othermes" method="post">
                                <s:token/>
                                   <table style="width: 100%">
                                      <tr>
                                         <td width="100px;">类型</td>
                                         <td>
												<s:iterator value="#session.listType" >
		                                         <c:choose>
									                <c:when test="${otherMes.typeId == typeId}">
									                    ${typeName }
		                                             </c:when>
		                                          </c:choose>  
		                                         </s:iterator>
		                                         <a href="javaScript:;" id="show_choseType"> 修改类型</a>
		                                         <div id="divselect12" class="divselect3 divselect" style="display: none;">
                                                              <cite>选择类型</cite>
                                                              <ul style="height: 200px; overflow: scroll;">
                                                                   <s:iterator value="#session.listType" id="listType">   
																        <li > <a href="javascript:;" selectid="<s:property value='typeId'/> "> <s:property value="typeName"/> </a>
																         </li>
																       </s:iterator>
                                                                    </ul>
                                                      </div>
                                                       <input name="typeId" type="hidden" value="${otherMes.typeId}" id="inputselect"/>
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect12","#inputselect");
													});
													</script>
										</td>
                                      </tr>
                                      <tr>
                                         <td height="20" colspan="2"></td>
                                      </tr>
                                       <tr>
                                          <td>置顶：</td>
                                          <td>
                                            <c:choose>
                                                 <c:when test="${otherMes.newTop==0}">
                                             <s:select list="#{0:'否',1:'是'}" listKey="key" listValue="value" name="newTop" value="0" > </s:select>
                                                 </c:when>
                                                 <c:when test="${otherMes.newTop==1}">
                                             <s:select list="#{0:'否',1:'是'}" listKey="key" listValue="value" name="newTop" value="1" > </s:select>
                                                 </c:when>
                                            </c:choose>
                                          </td>
                                       </tr>
                                      <tr>
                                         <td height="20" colspan="2"></td>
                                      </tr>
                                       <tr>
                                        <td>
                                            URL：
                                        </td>
                                         <td>
                                            <input type="text" name="url" value="${otherMes.url }"/> 
                                         </td>
                                      </tr>
                                      <tr>
                                         <td height="20" colspan="2"></td>
                                      </tr>
                                       <tr>
                                        <td>
                                            	标题：
                                        </td>
                                         <td>
                                           <textarea rows="2" cols="80" class="beizhu" name="title">${otherMes.title }</textarea> 
                                         </td>
                                      </tr>
                                      <tr>
                                         <td height="20" colspan="2"></td>
                                      </tr>
                                      <tr>
                                         <td>内容</td>
                                         <td> <textarea rows="10" cols="80"  name="message">${otherMes.message } </textarea> 
                                          <input name="id" type="hidden" value="${otherMes.id }" />
                                          </td>
                                      </tr>
                                      <tr>
                                         <td colspan="2" align="center"><input type="submit" value="提交"/> </td>
                                      </tr>
                                   </table>
                             </s:form>
                     </div>
                    </div>
             </div>
          </div>
  </body>
</html>
