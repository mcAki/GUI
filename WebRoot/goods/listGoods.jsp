<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
	</script>
	</head>
	<body style="min-width:1000px;">
		<%@ include file="../common/incBanner.jsp"%>
		<%--
		
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list!listGoods" namespace="/goods"
			method="post">
				<table>
					<tr>
						<td>&nbsp;商品类型:&nbsp;</td>
						<td><s:select name="type" list="goodsTypes" listKey="goodsTypeId" listValue="goodsTypeName"></s:select></td>
						<td><s:select name="isUsed" list="%{#{-1:'===选择状态===',0:'禁用',1:'可用'}}"/></td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				</s:form>
		</div>
		<center>
			<display:table id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/goods/list!listGoods.do">
				<display:caption>商品列表</display:caption>
				<display:column property="goodsId" title="商品ID" />
				<display:column property="goodsName" title="商品名称" />
				<display:column property="province" title="所属地区" />
				<display:column title="商品类别" >
					<c:choose>
						<c:when test="${idListTb.goodsFlag==10}">移动直充</c:when>
						<c:when test="${idListTb.goodsFlag==11}">电信直充</c:when>
						<c:when test="${idListTb.goodsFlag==12}">联通直充</c:when>
						<c:when test="${idListTb.goodsFlag==20}">卡密</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="goodsType.goodsTypeName" title="商品类型" />
				<display:column title="状态" >
					<c:choose>
						<c:when test="${idListTb.isUsed==0}">禁用</c:when>
						<c:when test="${idListTb.isUsed==1}">可用</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="操作">
					<a href="page!update.do?goodsId=${idListTb.goodsId}">修改</a>&nbsp;
					<c:choose>
						<c:when test="${idListTb.isUsed==0}"><a href="doRecover.do?goodsId=${idListTb.goodsId}">恢复</a>&nbsp;</c:when>
						<c:when test="${idListTb.isUsed==1}"><a href="doDel.do?goodsId=${idListTb.goodsId}">禁用</a>&nbsp;</c:when>
					</c:choose>
				</display:column>
			</display:table>
		</center>
		<br />
		<br />
 --%>
 <div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<div class="quota cxjjtable" style="width: 90%">
							<s:form id="form0" name="form0"
			action="list!listGoods" namespace="/goods"
			method="post">
				<table>
					<tr>
						<td>&nbsp;商品类型:&nbsp;</td>
						<td><s:select name="type" list="goodsTypes" listKey="goodsTypeId" listValue="goodsTypeName"></s:select></td>
						<td><s:select name="isUsed" list="%{#{-1:'===选择状态===',0:'禁用',1:'可用'}}"/></td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				</s:form>
						</div>
						<p class="jjcx">
							商品列表
						</p>

					</div>
					<div class="modify">
<center>
			<display:table id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb_002"
				requestURI="${pageContext.request.contextPath}/goods/list!listGoods.do">
				<display:column property="goodsId" title="商品ID" />
				<display:column property="goodsName" title="商品名称" />
				<display:column property="province" title="所属地区" />
				<display:column title="商品类别" >
					<c:choose>
						<c:when test="${idListTb.goodsFlag==10}">移动直充</c:when>
						<c:when test="${idListTb.goodsFlag==11}">电信直充</c:when>
						<c:when test="${idListTb.goodsFlag==12}">联通直充</c:when>
						<c:when test="${idListTb.goodsFlag==13}">游戏直充</c:when>
						<c:when test="${idListTb.goodsFlag==20}">卡密</c:when>
						<c:when test="${idListTb.goodsFlag==30}">广东电信单一缴费</c:when>
						<c:when test="${idListTb.goodsFlag==31}">广东电信宽带</c:when>
						<c:when test="${idListTb.goodsFlag==32}">广东电信综合缴费</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="goodsType.goodsTypeName" title="商品类型" />
				<display:column title="状态" >
					<c:choose>
						<c:when test="${idListTb.isUsed==0}">禁用</c:when>
						<c:when test="${idListTb.isUsed==1}">可用</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="操作">
					<a href="page!update.do?goodsId=${idListTb.goodsId}">修改</a>&nbsp;
					<c:choose>
						<c:when test="${idListTb.isUsed==0}"><a href="doRecover.do?goodsId=${idListTb.goodsId}">恢复</a>&nbsp;</c:when>
						<c:when test="${idListTb.isUsed==1}"><a href="doDel.do?goodsId=${idListTb.goodsId}">禁用</a>&nbsp;</c:when>
					</c:choose>
				</display:column>
			</display:table>
		</center>
					</div>
				</div>
			</div>
		</div>	
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
