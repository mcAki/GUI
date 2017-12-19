<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css" />
		<%@ include file="../common/incHead.jsp"%>
  <%@ include file="../common/incBanner.jsp" %>
<%@ include file="../common/incFooter.jsp" %>
 <script type="text/javascript" src="../js/divselect.js"></script>    
  <style type="text/css">
	  .formtable2 td{
	     font-size: 14px;
	 }
	 
	 </style>
  </head>  
  <body>
 
  	<!-- 
  	<s:form name="strutsForm" action="viewUserProfile!viewUserProfile" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="550" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>个人基本信息</caption>
  	  <tr>
  	    <td colspan="4"></td>
      </tr>
     
  	  <tr>
      	<td width="121" align="right">经销商名称:</td>
  	    <td align="left"><s:label name="users.userName"/></td>
  	    <td width="134" align="right">登录名称:</td>
  	    <td width="134" align="left"><s:label name="users.loginName"/></td>
  	  </tr>
      
  	  <tr>
	   <td align="right" >性&nbsp;别:</td>
	   <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.gender==1}">男</c:when>
	    						<c:when test="${users.gender==2}">女</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right">email:</td>
  	   <td align="left" ><s:label name="users.email"/></td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
      
      <tr>
  	    <td align="right">手机号码:</td>
  	    <td align="left" ><s:label name="users.mobile"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">账户余额:</td>
  	    <td align="left" ><s:label name="useraccount.balance"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">佣金余额:</td>
  	    <td align="left" ><s:label name="useraccount.commission"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
       
       <tr>
        <td align="right" >备&nbsp;注:</td>
  	    <td align="left" colspan="3"><s:label name="users.remark" rows="3"/></td> 	  
       </tr>
		
  </table>
  </s:form>
  	 -->
	
	<div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
            <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj1 tqyj fixed">
                     <strong class="tqxj">个人基本信息</strong>
                     <div class="hyxx1 hyxx" style="width:500px; min-height:400px; padding-top:30px; margin: 0 auto;"> 
                           <form action="" method="get">
                                  <table width="100%" border="2" bordercolor="blue" cellpadding="1" cellspacing="1" class="formtable2" >
                                 <tr align="center">
                                   <td colspan="4" align="center" style="font-size: 16px;font-weight: bold;"> 账户资料 </td>
                                 </tr>
                                  <tr align="center">
                                        <td width="100" ><strong>经销商名称：&nbsp;</strong></td>
                                        <td width="100">
                                        <!-- 
                                        <input name="" type="text"  class="zhm" value="${users.userName }" onfocus="FormFocus(this,'${users.userName }')" onblur="FormBlur(this,'${users.userName }')"/>
                                         -->
                                         ${users.userName }
                                        </td>
                                         
                                        <td width="100" align="center"><strong>登录名称:&nbsp;</strong></td>
                                        <td width="100">
                                        <!-- 
                                        <input name="" type="text"  class="zhm" value="${users.loginName }" onfocus="FormFocus(this,'${users.loginName }')" onblur="FormBlur(this,'${users.loginName }')"/>
                                         -->
                                        ${users.loginName }
                                        </td>
                                  </tr>
                                  <tr align="center">
                                        <td align="center"><strong>性&nbsp;&nbsp;&nbsp;&nbsp;别：&nbsp;</strong></td>
                                        <td>
                                        <c:choose>
					    						<c:when test="${users.gender==1}">
					    						<!-- 
                                                  <input name="" type="text"  class="zhm" value="男" onfocus="FormFocus(this,'男')" onblur="FormBlur(this,'男')"/>
					    						 -->
					    						 男
                                               </c:when>
					    					<c:when test="${users.gender==2}">
					    					<!-- 
                                                  <input name="" type="text"  class="zhm" value="女" onfocus="FormFocus(this,'女')" onblur="FormBlur(this,'女')"/>
					    					 -->
					    					 女
                                             </c:when>
					    					</c:choose>
					    					 <script>
											  function FormFocus(obj,str) {
											        if (obj.value == str) {
											            obj.value = "";
											        }
											    }
											
											    function FormBlur(obj, str) {
											        if (obj.value == "") {
											            obj.value = str;
											        }
											    }
											  </script>
                                        </td>
                                        <td align="center"><strong>电 子 邮 箱：&nbsp;</strong></td>
                                        <td>
                                        <!-- 
                                        <input name="" type="text" value="${users.email }"  class="zhm"/>
                                         -->
                                         ${users.email }
                                        </td>
                                  </tr>
                                  <tr align="center">
                                        <td align="center"><strong>绑定手机号码：&nbsp;</strong></td>
                                        <td>
                                        <!-- 
                                        <input name="" type="text"  class="zhm" value="${users.mobile }"onfocus="FormFocus(this,'${users.mobile }')" onblur="FormBlur(this,'${users.mobile }')"/>
                                         -->
                                         ${users.mobile }
                                        </td>
                                        <td align="center"><strong>当 前 余 额：&nbsp;</strong></td>
                                        <td>
                                        <!-- 
                                        <input name="" type="text"  class="zhm" value="${useraccount.balance }元"onfocus="FormFocus(this,'${useraccount.balance }元')" onblur="FormBlur(this,'${useraccount.balance }元')"/>
                                         -->
                                          <fmt:formatNumber maxFractionDigits="2" value="${useraccount.balance }"/>
                                         元
                                        </td>
                                  </tr>
                                  <tr align="center">
                                  
                                        <td align="center"><strong>终 端 机 号：&nbsp;</strong></td>
                                        <td>
                                         ${users.terminalNo }
                                        </td>
                                        <td align="center"><strong>佣 金 余 额：&nbsp;</strong></td>
                                        <td>
                                        <!-- 
                                        <input style="color: red;" name="" type="text"  class="zhm" value="${useraccount.commission }元" onfocus="FormFocus(this,'${useraccount.commission }元')" onblur="FormBlur(this,'${useraccount.commission }元')"/>
                                         -->
                                         <fmt:formatNumber maxFractionDigits="2" value="${useraccount.commission }"/>
                                         元
                                        </td>
                                  </tr>
                                  <tr align="center">
                                        <td align="center"><strong>QQ号码：&nbsp;</strong></td>
                                        <td  >
                                         ${users.qq}
                                        </td>
                                        <td align="center"><strong>加密key编码：&nbsp;</strong></td>
                                        <td  >
                                         ${users.otp.keyId }
                                        </td>
                                        
                                  </tr>
                                  <tr align="center">
                                      <td  align="center"><br /><strong>备注：&nbsp;&nbsp;</strong></td>
                                      <td colspan="3">
                                         ${users.remark }
                                      </td> 
                                      
                                  </tr>
                            </table> 
                           </form>
                           
                     </div>

             </div>
          </div>
      </div>
  </body>
</html>
