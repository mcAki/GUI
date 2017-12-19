<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.vo.SppinUsergroupVo"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/divselect.js"></script>
		<script type="text/javascript" src="../js/admin.js"></script>
		<script type="text/javascript">
	
	function batchAddPermission(itemName) {
	

			var datas = "";
			var datas2 = "";
			var items = document.getElementsByName(itemName);
			for ( var i = 0; i < items.length; i++) {
				if(items[i].type=="checkbox"){
					var pid = items[i].value;
					if (items[i].checked) {
						datas = pid + ","+datas;
					}else{
						datas2 = pid + ","+datas2;
					}
				}
			}
			//alert(datas);
			//alert(datas2);
			//document.form1.action="${pageContext.request.contextPath}/permission/doUpdate.do?pid="
			//		+ datas + "&dpid="+ datas2 +"&usergroupId=${usergroupId}";
			submitForm.action="${pageContext.request.contextPath}/supplyInterface/setSupplyUsergroup!addSupplyUsergroup.do";
			submitForm.spid.value = datas;
			submitForm.sdpid.value = datas2;
			submitForm.susergroupId.value = ${usergroupId};
			submitForm.submit();
		//window.close(); 
	}
	
</script>
<script>
		$().ready(
		function() {
			$("#goods1").change(
					function() {
						var params = $("#goods1").val();
						$.ajax({
							type : "post",
							url : "supplyInterface/goodsList.do",
							data : "province=" + params,
							cache : false,
							dataType : "json",
							success : function(json) {
								var str = "<option>"+"--选择商品--"+"</option>";
								$("#goods2").html("");
								for (var i = 0; i < json.length; i++) {
						        str += "<option value='" + json[i].goodsId
											+ "'>" + json[i].goodsName
											+ "</option>";
									}		
								$("#goods2").append(str);
							},
							error : function() {
								alert("请与管理员联系");
							}
						});
				});
		});
		
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
							权限修改
						</p>
						<div class="quota cxjjtable" style="width: 700px; float: left;">
							<s:form id="form1" name="form0"
							action="list!findByCondition" namespace="/supplyInterface"
							method="post">
								<table width="100%">
									<tr >
										<td><s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select>
										  <input type="hidden" name="usergroupId" value="${usergroupId }"/>
										</td>&nbsp;&nbsp;
										<td><s:select id="goods1" name="goodsTypeId" list="goodsTypeList" listKey="goodsTypeId" listValue="goodsTypeName" onchange="ajax(this)"/>
										&nbsp;&nbsp;
										<select id="goods2" name="goodsId">
								            <option value="${goodsId}">--选择商品--</option>
								  	   </select>
										</td>
										<td>面额：<input type="text" name="value" value="${vlaue }"/> </td>
										<td><s:select name="state" list="%{#{-1:'===选择状态===',0:'禁用',1:'可用'}}"/></td>
										<td><br /><br /></td>
										<td>&nbsp;&nbsp;&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
									</tr>
								</table>
							</s:form>
						</div>
					</div>
					<div class="modify">
						
						
			<s:form id="form1" name="form1" action="setSupplyUsergroup!addSupplyUsergroup.do"
					namespace="/supplyInterface" method="post" >
			<center>
			<input type="hidden" name="usergroupId" />
			<display:table id="dtable" name="sppinUsergroupList" style="width:600px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/supplyInterface/list!setSupplyUsergroup.do">
						<display:caption>所有商品</display:caption>
				<display:column title="选择">
					<input type="checkbox" name="chk" value="${dtable.id }"
						<%=((SppinUsergroupVo)pageContext.getAttribute("dtable")).getChecked()%> />
				</display:column>
				<display:column title="ID" property="id"></display:column>
				<display:column title="供货商" property="supplyName"></display:column>
				<display:column title="商品" property="goodsName"></display:column>
				<display:column title="进货价" >
							<fmt:formatNumber maxFractionDigits="4" value="${dtable.stockPrice }"/>
						</display:column>
						<display:column title="销售价" >
							<fmt:formatNumber maxFractionDigits="4" value="${dtable.retailPrice }"/>
						</display:column>
				<display:column title="面额" property="value"/>
				<display:column title="状态">
					<c:choose>
						<c:when test="${dtable.state==0}">
						禁用
					</c:when>
						<c:when test="${dtable.state==1}">
						可用
					</c:when>
					</c:choose>
				</display:column>
				<display:column title="能否冲正">
					<c:choose>
						<c:when test="${dtable.canReverse==0}">
						禁用
					</c:when>
						<c:when test="${dtable.canReverse==1}">
						可用
					</c:when>
					</c:choose>
				</display:column>
			</display:table> </center>&nbsp;&nbsp;&nbsp;&nbsp;
			<s:hidden name="usergroupId"></s:hidden>
			<input type="checkbox" onclick="checkAll(this, 'chk','no')" />
				全选
				<input icon="icon-save" type="button" value="保存"
				onclick="javascript:batchAddPermission('chk')" />
			<br /></center>
		</s:form>
		
		<form id="submitForm" name="submitForm">
			<input type="hidden" id="spid" name="spid"/>
			<input type="hidden" id="sdpid" name="sdpid" />
			<input type="hidden" id="susergroupId" name="usergroupId"/>
		</form> 
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
