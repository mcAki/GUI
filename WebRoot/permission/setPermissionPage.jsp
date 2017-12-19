
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/divselect.js"></script>
		<script type="text/javascript">
	
	$(document).ready(function() {
		
	})
	
</script>
		<style type="text/css">
 
</style>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>

		<div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<p class="jjcx">
							用户组列表
						</p>

					</div>
					<div class="modify">
						<div class="cxjjtable3">
						
							<table border="1" cellpadding="3" cellspacing="0" style="width: 60%;margin:auto" class="list_tb_002">
							   <tr >
							     <th>组ID</th>
							     <th>组名</th>
							     <th>备注</th>
							     <th>操作</th>
							   </tr>
							  <s:iterator value="listUsergroup">
							   <tr>
							     <td>${id } </td>
							     <td>${groupName }</td>
							     <td>备注</td>
							     <td> <a href="/MPRS/permission/setPermission!modifyPermissionPage.do?usergroupId=${id }"> 修改 </a>|  复制 | <a href="/MPRS/supplyInterface/list!setSupplyUsergroup.do?usergroupId=${id }"> 高级</a>  </td>
							   </tr>
							  </s:iterator> 
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>	
			<br />
			<br />
			<script type="text/javascript">
	displaytagExportLink();
</script>
			<a href="user!add.html"></a>
			<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
