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
	<body style="min-width: 1000px;">
		<%@ include file="../common/incBanner.jsp"%>
		<%--
		
		<br />
		<br />
		<br />
		<center>
		<center>
			<display:table id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/goodsType/list.do">
				<display:caption>商品类型列表</display:caption>
				<display:column property="goodsTypeId" title="商品类型ID" />
				<display:column property="goodsTypeName" title="商品类型名称" />
				<display:column property="description" title="描述" />
				<display:column title="操作">
					<a href="page!update.do?goodsTypeId=${idListTb.goodsTypeId}">修改</a>&nbsp;
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
						<p class="jjcx">
							商品类型列表
						</p>

					</div>
					<div class="modify">
				      <center>
							<display:table id="idListTb" name="pageView" style="width:900px;"
								sort="external" pagesize="${pageSize}" cellspacing="1"
								class="list_tb_002"
								requestURI="${pageContext.request.contextPath}/goodsType/list.do">
								<display:column property="goodsTypeId" title="商品类型ID" />
								<display:column property="goodsTypeName" title="商品类型名称" />
								<display:column property="description" title="描述" />
								<display:column title="操作">
									<a href="page!update.do?goodsTypeId=${idListTb.goodsTypeId}">修改</a>&nbsp;
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
