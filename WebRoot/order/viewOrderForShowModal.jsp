<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>

  </head>  
  <body>
  `
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="showUserProfile" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="550" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>订单信息</caption>
  	  <tr>
  	    <td colspan="4"></td>
      </tr>
     
  	  <tr>
      	<td width="121" align="right">订单流水号:</td>
  	    <td align="left"><s:label name="mainorder.mainOrderId" /></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
  	  </tr>
  	  
  	   <tr>
  	    <td align="right">订单类型:</td>
  	    <td align="left" ><s:label>
  	    	<c:choose>
				<c:when test="${mainorder.orderType==1}">直充</c:when>
				<c:when test="${mainorder.orderType==2}">卡密</c:when>
				<c:otherwise>未知</c:otherwise>
			</c:choose>
  	    	</s:label>
  	    </td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">充值号码:</td>
  	    <td align="left" ><s:label name="mainorder.mobile"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>

      <tr>
  	    <td align="right">充值日期:</td>
  	    <td align="left" ><s:label name="mainorder.createTime"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
        <tr>
  	    <td align="right">充值金额:</td>
  	    <td align="left" ><s:label name="mainorder.totalMoney"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
        <tr>
  	    <td align="right">号码余额:</td>
  	    <td align="left" ><s:label name="mainorder.cBalance"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
       
         <tr>
  	    <td align="right">商品名称:</td>
  	    <td align="left" ><s:label name="mainorder.goodsName"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
       
        <tr>
  	    <td align="right">进货价:</td>
  	    <td align="left" ><s:label name="mainorder.stockPrice"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
        <tr>
  	    <td align="right">订单状态:</td>
  	    <td align="left" ><s:label>
  	    	<c:choose>
  	    		<c:when test="${mainorder.state==-1}">已冲正</c:when>
				<c:when test="${mainorder.state==0}">申请处理</c:when>
				<c:when test="${mainorder.state==1}">处理成功</c:when>
				<c:when test="${mainorder.state==2}">处理失败</c:when>
				<c:when test="${mainorder.state==3}">处理中</c:when>
				<c:when test="${mainorder.state==5}">处理中</c:when>
				<c:when test="${mainorder.state==255}">用户取消</c:when>
  	    	</c:choose>
  	    	</s:label>
  	    </td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
        <tr>
  	    <td align="right">冲正状态:</td>
  	    <td align="left" ><s:label>
  	    		 <c:choose>
                      <c:when test="${mainorder.reversalState==-1 }">未冲正</c:when>
                      <c:when test="${mainorder.reversalState==0 }">处理中</c:when>
                      <c:when test="${mainorder.reversalState==1 }">成功</c:when>
                      <c:when test="${mainorder.reversalState==2 }">失败</c:when>
                      <c:when test="${mainorder.reversalState==5 }">处理中</c:when>
                </c:choose>
  	    	</s:label>
  	    </td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
        <tr>
  	    <td align="right">冲正日期:</td>
  	    <td align="left" >
  	    	${mainorder.reversalTime }
  	    </td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      <%--
      
      
      <tr>
  	   <td align="right">供货商:</td>
  	   <td align="left" ><s:label name="mainorder.supplyName"/></td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
  	  
  	  <tr>
	   <td align="right" >经销商:</td>
	   <td align="left"><s:label name="mainorder.userName" /></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
      
      <tr>
	   <td align="right" >终端机号:</td>
	   <td align="left"><s:label name="mainorder.terminalNo" /></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
      
      
      <tr>
  	    <td align="right">卡密序号:</td>
  	    <td align="left" ><s:label name="mainorder.cardLibIds"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
     
      
      <tr>
  	    <td align="right">销售价:</td>
  	    <td align="left" ><s:label name="mainorder.retailPrice"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
       --%>
    
    
      
      <tr>
       <td align="right" >描&nbsp;述:</td>
  	   <td align="left" colspan="3"><s:label name="mainorder.desc" rows="3"/></td> 	  
      </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
