<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css"/>
		<link href="../css/sys.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
		<%@ include file="../common/incHead.jsp"%>
		 <script type="text/javascript">
 //弹出考勤日期
function openMy97Checkondate(){

	WdatePicker({
		//eCont:'divCheckOnDate',
		onpicked:function(dp){
			//alert('你选择批量考勤的日期是: '+dp.cal.getDateStr())
				
			//$('#selectedDate').val(dp.cal.getDateStr());
			
			//$('#checkOnDate').val(dp.cal.getDateStr());
			//$('#strutsForm').submit();
			//alert($('#checkOnDate').val());
			//alert(dp.cal.getNewDateStr());
		},
		dateFmt:'yyyy年M月d日',
		//minDate:'%y-%M-{%d-7}',
		//maxDate:'%y-%M-%d',
		minDate:'${StartDate}',
		maxDate:'${EndDate}',
		//startDate:'${checkOnDate}',
		vel:'selectedDate',
		isShowClear:false,
		isShowToday:false
	})

}

function check(selectId){
	var id=selectId;
	var url;
	if(id==1){
		url='${pageContext.request.contextPath}/teamTree!showTeamTreeRoot.action?missionId=${missionId}';
	}else{
		url='${pageContext.request.contextPath}/teamTree!showTeamById.action?missionId=${missionId}'
	}
	document.getElementById("teamTree").src=url;
}
         </script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center>
		<form id="formDate">
		<input type="hidden" value="" id="selectedDate" />
		</form>
		<table width="100px" border="2" align="left">
		<tr>
			<td style="height=20px;">
			考勤日期:<input id="idCheckOnDate" type="text" class="txtW3 Wdate" style="cursor:pointer;" onmousedown="openMy97Checkondate();" value="<fmt:formatDate value="${DatecheckOnDate}" pattern="yyyy年M月d日"/>" /><br/>
			<s:radio list="%{#{1:'直接选择队伍',2:'根据队伍编号查找'}}" name="idRadio1" value="1" onclick="check(this.value)"></s:radio>
			</td>
			<td align="right" width="700" rowspan=2>
				<iframe name="iframe1" id="iframe1" width="750px" height="700" scrolling="Auto" frameborder="2"></iframe>
			</td>			
		</tr>
			<tr>
				<td>
					<iframe name="teamTree" id="teamTree" src ='${pageContext.request.contextPath}/teamTree!showTeamTreeRoot.action?missionId=${missionId}' width="300px" height="700" scrolling="Auto" frameborder="2"></iframe>				
				</td>
			</tr>
		</table>
		</center>
		<br/>
		<br />
		 <s:fielderror/>
		<a href="user!add.html"></a>
		
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
