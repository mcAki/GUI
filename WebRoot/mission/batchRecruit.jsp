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
		<div align="center">
			<table width="800" border="0" align="center" cellspacing="3"
				class="form_tb">
				<caption>
					<h2>
						<strong>项目内容查看</strong>
					</h2>
				</caption>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td align="right">
						项目名称:
					</td>
					<td width="228" align="left">
						${mission.subject}
					</td>
					<td align="right">
						项目类型:
					</td>
					<td align="left">
						${mission.missionType.typeName}
					</td>
					<td width="47"></td>
				</tr>
				<tr>
					<td align="right">
						建立人:
					</td>
					<td align="left">
						${mission.createUsername}
					</td>
					<td align="right">
						打卡模式:
					</td>
					<td align="left">
						${mission.checkOnMode==1?'打卡机':'短信'}
					</td>
					<td></td>
				</tr>
				<tr>
					<td width="108" align="right">
						项目负责人:
					</td>
					<td align="left">
						${mission.usersByManagerId.userName}
					</td>
					<td width="154" align="right">
						所属区域:
					</td>
					<td width="245" align="left" style="white-space: nowrap;">
						${mission.districtName}
					</td>
					<td></td>
				</tr>
				</table>
				</div>
		<center>
		<br/>
		<s:form id="form1" name="form1"
			action="personal!listUserForRecruit" namespace="/mission"
			method="post" target="iframe1">
		<input type="hidden" value="" name="CheckOnDate" id="submitCheckOnDate" />
		<table width="950px" border="2" align="center">
	      <tr>
		      <td style="height=20px; text-align:center;"><input icon="icon-check" type="button" value="查看当前被招募人员" onclick="javascript:window.iframe1.window.location='${pageContext.request.contextPath}/mission/personal!listPersonalForLeader.do?missionId=${mission.missionId }'"/></td>
		      <td align="center" width="700" rowspan=3>
		        <iframe name="iframe1" id="iframe1" width="750px" height="700" scrolling="Auto" frameborder="2"></iframe>
	          </td>
	      </tr>
	      <tr>
			<td style="height=20px;">
				多个查询请以回车分隔:
				<textarea name="idCardCode" id="idCardCode" cols="20" rows="30" ></textarea>
				<s:hidden name="missionId"></s:hidden><br/>
				<input icon="icon-search"  type="submit" value="搜索" /><br/>
				人员身份证输入进以上文本框,然后进行招募
				
			
			</td>
		  </tr></table></s:form>
		</center>
		<br/>
		<br />
		 <s:fielderror/>
		<a href="user!add.html"></a>
		
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
