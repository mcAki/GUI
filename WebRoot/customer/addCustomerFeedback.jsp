<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
      $(document).ready(function(){
          $("#form_submit").click(function(){
              var title = $("#title").val();
              var message = $("#message").val();
              if(title==""||message==""){
                 alert("标题或者留言内容不能为空");
              }else{
                  $.get("/MPRS/customerFeedback/savecfb.do",$("#strutsForm").serialize(),function(data){
                     if(data=="success"){
                        alert("留言成功！");
                     }else{
                         alert("留言失败，请重新留言！");
                     }
                  });
              }
          });
      })
   </script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
              <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed" style="min-height: 500px; ">
                     <div class="cxjj fixed">
                            <p class="jjcx">增加反馈信息</p>
                     </div>
                     <div class="tishi_id">
                                                                  你好，我是妙鸣在线的产品经理，欢迎您给我们提产品的使用感受和建议！
                     </div>
                     <div class="country communi" style="width:620px;margin-top:20px;padding: 20px;">
                             <s:form name="strutsForm" id="strutsForm" action="savecfb" namespace="/customerFeedback" method="post">
                            	<s:hidden name="mainorder.mainOrderId" />
                            	<s:hidden name="orderdetail.orderDetailId" />
                                <s:token/>
                                   <table>
                                      <tr>
                                         <td colspan="2"> &nbsp; </td>
                                      </tr>
                                      <tr>
                                        <td class="font_order">
                                            	标题：
                                        </td>
                                         <td>
                                           <input type="text" name="customerFeedback.cfTitle" id="title" style="width:400px;">
                                           <!-- -
                                           <textarea rows="1" cols="60" id="title" name="customerFeedback.cfTitle"></textarea> 
                                            -->
                                         </td>
                                      </tr>
                                        <tr>
                                         <td style="height: 10">&nbsp; </td>
                                      </tr>
                                      <tr>
                                         <td class="font_order">内容：</td>
                                         <td> <textarea rows="8" cols="60" id="message" name="customerFeedback.cfMessage"></textarea> 
                                          </td>
                                      </tr>
                                        <tr>
                                         <td style="height: 10">&nbsp; </td>
                                      </tr>
                                      <tr>
                                        
                                         <td colspan="2" align="center"><button id="form_submit" type="button" value="提交"/> </td>
                                      </tr>
                                   </table>
                                   
                             </s:form>
                     </div>
                    </div>
             </div>
          </div>
  
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

