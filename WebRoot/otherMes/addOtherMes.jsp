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
    $(document).ready(function(){
    $("#addtype_id").click(function(){
       $("#typeName_input").toggle();
    });
    $("#typeName_submit").click(function(){
        var typeName = $("#typeName_text").val();
        if(typeName==""){
           alert("不能为空");
           return;
        }
        $.post( "/MPRS/othermes/addType.do", {typeName:typeName},  function(data){
            if(data=="error"){
               alert("添加失败");
            }else{
               alert("添加成功");
               $("#typeName_input").hide();
              $("#strutsForm_typeId").append("<option  value='"+data.typeId+"'>"+data.typeName+"</option>");
            //  $("#divselect9").append("<cite>请选择类型</cite> <ul id='list_ul' style='height:200px; overflow: scroll;'><li> <a href='javascript:;' selectid='"+ data.typeId+ "'>" +data.typeName+ "</a></li></ul>");
            }
        });
    });
    $("#form_submit").click(function(){
       var typeId = $("#inputselect").val();
       var biaoti = $("#biaoti").val();
       var message = $("#message").val();
        if(typeId<=0){
           alert("没有选择类型");
        }else if(biaoti==""||message==""){
            alert("没有输入内容");
        }else{
         strutsForm.submit();
        }
    });
});
  </script>

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
                             <s:form name="strutsForm" id="strutsForm" action="addOtherAct" namespace="/othermes" method="post">
                            	<s:hidden name="mainorder.mainOrderId" />
                            	<s:hidden name="orderdetail.orderDetailId" />
                                <s:token/>
                                   <table>
                                      <tr>
                                         <td width="100px;">类型</td>
                                         <td>
                                            <s:select list="#session.listType" listKey="key" listValue="value" name="typeId"> </s:select>
													<a href="javaScript:;" id="addtype_id"> 添加类型 </a>
													<span id="typeName_input" style="display: none;">
													   <input id="typeName_text" type="text" name="typeName" />
													   <input id="typeName_submit" type="button" value="确定添加"/>
													</span>
                                         </td>
                                      </tr>
                                      <tr>
                                         <td>置顶</td>
                                         <td>
                                            <s:select list="%{#{0:'否',1:'是'}}" listKey="key" listValue="value" name="newTop"> </s:select>
                                         </td>
                                      </tr>
                                      <tr>
                                        <td>
                                            URL：
                                        </td>
                                         <td>
                                            <input type="text" name="url"/> 可不填
                                         </td>
                                      </tr>
                                      <tr>
                                         <td style="height: 10">&nbsp; </td>
                                      </tr>
                                      <tr>
                                        <td>
                                            	标题：
                                        </td>
                                         <td>
                                           <textarea rows="2" cols="80" id="biaoti" name="title"></textarea> 
                                         </td>
                                      </tr>
                                        <tr>
                                         <td style="height: 10">&nbsp; </td>
                                      </tr>
                                      <tr>
                                         <td>内容</td>
                                         <td> <textarea rows="10" cols="80" id="message" name="message"></textarea> 
                                          </td>
                                      </tr>
                                      <tr>
                                         <td colspan="2" align="center"><input id="form_submit" type="button" value="提交"/> </td>
                                      </tr>
                                   </table>
                                   
                             </s:form>
                     </div>
                    </div>
             </div>
          </div>
  </body>
</html>
