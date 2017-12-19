<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="modifySupply" namespace="/supply" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<s:hidden name="supplyId"/>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>更新供货商</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td width="105" align="right">供货商名称:</td>
  	    <td align="left"><s:textfield name="modifySupply.supplyName"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">选择供货商类型:</td>
  	    <td align="left"><s:select name="modifySupply.supplyType" list="#{ 1:'自有', 2: '在线'}" listKey="key" listValue="value" /> </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">选择销售类型:</td>
  	    <td align="left"><s:select name="modifySupply.sellType" list="#{ 1:'空中充值', 2: '卡密'}" listKey="key" listValue="value"/> </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">联系人:</td>
  	    <td align="left"><s:textfield name="modifySupply.contactPeople"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">地址:</td>
  	    <td align="left"><s:textfield name="modifySupply.address"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td width="105" align="right">电话:</td>
  	    <td align="left"><s:textfield name="modifySupply.mobile"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >描述:</td>
  	   <td colspan="4" align="left"><s:textarea name="modifySupply.desc" cssClass="txtW5" rows="4"></s:textarea></td>
       
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

