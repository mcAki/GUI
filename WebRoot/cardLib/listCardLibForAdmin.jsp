<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
	</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="listCardLibForAdmin" namespace="/cardLib"
			method="post">
				<table>
					<tr>
						<td>&nbsp;有效期:&nbsp;</td>
						<td>&nbsp;<input id="d5221" name="startExpireTime" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-%M-{%d-10} %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
								至
								<input id="d5222" name="endExpireTime" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>&nbsp;</td>
						<td>&nbsp;进货日期:&nbsp;</td>
						<td>&nbsp;<input id="d5223" name="startIndate" class="Wdate" type="text" onFocus="var d5224=$dp.$('d5224');WdatePicker({onpicked:function(){d5224.focus();},minDate:'%y-%M-{%d-10} %H:%m:%s',maxDate:'#F{$dp.$D(\'d5224\')}'})"/>
								至
								<input id="d5224" name="endIndate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5223\')}'})"/>&nbsp;</td>
						<td>销售日期:&nbsp;</td>
						<td>&nbsp;<input id="d5225" name="startDate" class="Wdate" type="text" onFocus="var d5226=$dp.$('d5226');WdatePicker({onpicked:function(){d5226.focus();},minDate:'%y-%M-{%d-10} %H:%m:%s',maxDate:'#F{$dp.$D(\'d5226\')}'})"/>
								至
								<input id="d5226" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5225\')}'})"/>&nbsp;</td>
					</tr>
				</table>
				<table>
					<tr>
						<td><s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select></td>
						<td>&nbsp;<s:select name="goodsId" list="goodsList" listKey="goodsId" listValue="goodsName"></s:select>&nbsp;</td>
						<td>&nbsp;<s:select name="sellType" list="%{#{0:'===选择销售类型===',1:'空中充值',2:'卡密'}}" listKey="key"></s:select>&nbsp;</td>
						<td>&nbsp;<s:select name="supplyType" list="%{#{0:'===选择供货商类型===',1:'自有',2:'在线'}}"/>&nbsp;</td>
						<td>&nbsp;经销商:&nbsp;</td>
						<td><input type="text" name="username" size="10" value="${param.username}"/></td>
						<td>&nbsp;库存状态:&nbsp;</td>
						<td>&nbsp;<s:select name="state" list="%{#{0:'===库存状态===',1:'未用',2:'已提取',3:'已过期'}}"/>&nbsp;</td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				</s:form>
		</div>
		<center>
			<display:table export="true" id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/cardLib/listCardLibForAdmin.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption>商品销售情况(管理员)</display:caption>
				<display:column property="cardCode" title="卡密号码" />
				<display:column property="goodsName" title="商品名" />
				<display:column property="supplyName" title="供货商" />
				<display:column property="userName" title="经销商" />
				<display:column property="cardValue" title="面值" />
				<display:column property="stockPrice" title="进货价" />
				<display:column property="retailPrice" title="销售价" />
				<display:column property="indate" title="进货日期"
					format="{0,date,yyyy-MM-dd HH:mm}" />
				<display:column property="expireTime" title="有效期"
					format="{0,date,yyyy-MM-dd HH:mm}" />
				<display:column title="已购买">
					<c:choose>
						<c:when test="${idListTb.state==1}">未使用</c:when>
						<c:when test="${idListTb.state==2}">已提取</c:when>
						<c:when test="${idListTb.state==3}">已过期</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="buyTime" title="购买时间"
					format="{0,date,yyyy-MM-dd HH:mm}" />
				<display:column property="remark" title="备注" />
				<display:setProperty name="export.excel.filename" value="times.xls"/> 
			</display:table>
		</center>
		<br />
		<br />
		<script type="text/javascript">
			displaytagExportLink();
	    </script>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
