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
		vel:'submitCheckOnDate',
		isShowClear:false,
		isShowToday:false
	})

}


         </script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center>
		<form id="formDate">		
		<input type="hidden" value="" id="selectedDate" />
		</form>
		<s:form id="form1" name="form0"
			action="list!batchServiceLogByPersonal" namespace="/missionservicelog"
			method="post" target="iframe1">
		<input type="hidden" value="" name="CheckOnDate" id="submitCheckOnDate" />
		<table width="100px" border="2" align="left">
		<tr>
			<td style="height=20px;">
			考勤日期:<input id="idCheckOnDate" type="text" class="txtW3 Wdate" style="cursor:pointer;" onmousedown="openMy97Checkondate();" value="<fmt:formatDate value="${DatecheckOnDate}" pattern="yyyy年M月d日"/>" /><br/>
			<s:radio list="%{#{1:'按姓名查找',2:'按电话号码查找'}}" name="radioVal" value="1" ></s:radio><br/>
				多个查询请以逗号分隔:(温馨提示:可以把要考勤人员的名单用文本保存好)
				<textarea name="item" id="idItem" cols="20" rows="20" ></textarea>
				<s:hidden name="missionId"></s:hidden><br/>
				<input icon="icon-search"  type="submit" value="搜索" />
				请先选择日期,然后把人员名单输入进以上文本框,然后在右边的列表中做相应考勤操作
			</s:form>
			</td>
			<td align="right" width="700" rowspan=2>
				<iframe name="iframe1" id="iframe1" width="750px" height="700" scrolling="Auto" frameborder="2"></iframe>
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
