
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
		<script type="text/javascript" src="../js/calendar/calendar.js"></script>
		<script type="text/javascript" src="../js/calendar/lang/en.js"></script>
		<link rel="stylesheet" type="text/css"
			href="../js/calendar/jscal2.css" />
		<link rel="stylesheet" type="text/css"
			href="../js/calendar/border-radius.css" />
		<link rel="stylesheet" type="text/css" href="../js/calendar/win2k.css" />
		<script type="text/javascript">
	function showOrder(mainOrderId) {
		var returnVal = ezModal(
				'${pageContext.request.contextPath}/order/mainOrderShowModal.do?mainOrderId=' + mainOrderId,
				700, 600);
	}
	$(document).ready(function() {
		$("#wen_show").click(function() {
			$("#wen_selAllExDateCountList").slideToggle();
		});
		$("#wen_hide").click(function() {
			$("#wen_selAllExDateCountList").hide();
		});
	})
	function show(tag) {
		var light = document.getElementById(tag);
		light.style.display = 'block';
	}
	function hide(tag) {
		var light = document.getElementById(tag);
		light.style.display = 'none';
	}
</script>
		<style type="text/css">
#wen_selAllExDateCountList {
	display: none;
	width: 50%;
	background: none repeat scroll 0 0 #FFFFFF;
	border: 1px solid #D5EAF8;
	padding: 0px 10px 10px 10px;
	z-index: 10000;
	overflow: scroll;
	max-height: 400px;
}

#wen_hide {
	float: right;
	cursor: pointer;
	border: 1px solid #D5EAF8;
	padding: 3px;
	margin: 3px;
	background-color: #FEE396;
}
</style>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />

		<div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
					  <center>
						<div class="quota cxjjtable" style="min-width: 400px;">
						 
							 <s:form id="form0" name="form0"
								action="adviceOfSettlementForSupply" namespace="/useraccountdealdetail"
								method="post">
								按时间查询：
								<input id="d52211" name="startDate_wen" class="date1"
									type="text"
									onFocus="var d5222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},maxDate:'#F{$dp.$D(\'d52222\')}'})" />
								至
								<input id="d52222" name="endDate_wen" class="date2" type="text"
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})" />
								<button  type="submit"  class="sousuo1"></button>
							</s:form>
						</div>
					  </center>
						<p class="jjcx">
							供货商额度结算列表
						</p>

					</div>
					<div class="modify">

						
						<div class="cxjjtable3">
							<p style="width: 50%;">
								查询日期为：
								<s:if test="endDate_wen != null && startDate_wen !=null">
					                  <%=session.getAttribute("startDate_wen") %>至  <%=session.getAttribute("endDate_wen") %>
					           </s:if>
								<s:else>
			                                      今日	
		           </s:else>
								<br />
								所有供货商结算合计数为:
								<span style="color: red; font-size: 16px;"><fmt:formatNumber maxFractionDigits="2" value="${ExDateCount}"/> </span>元
								<br />
							</p>	
								<display:table export="true" id="idListTb" name="session.selAllExDateCountList" style="width:100%;"
									pagesize="30" sort="external" cellspacing="1" class="list_tb_002">
								         <display:column property="supplyName" title="供货商名称" />
									     <display:column title="结算数据" >
									       <fmt:formatNumber maxFractionDigits="2" value="${idListTb.sumDealMoney }"/>
									     </display:column>
									 <display:setProperty name="export.excel.filename" value=" ${startDate_wen }-${endDate_wen }.xls"/>     
								</display:table>
			
				<%--
							<table class="list_tb_002" style="width: 95%">
								<tr>
									<th>
										供货商名称
									</th>
									<th>
										当日结算合计数(/元)
									</th>
								</tr>
								<s:iterator value="#session.selAllExDateCountList" id="column">
									<tr>
										<td>
											<s:property value="supplyName" />
										</td>
										<td>
											<s:property value="itemPrice" />
										</td>
									</tr>
								</s:iterator>
							</table>
				 --%>

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
