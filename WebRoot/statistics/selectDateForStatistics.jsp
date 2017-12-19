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

         </script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center>
		<s:form id="form1" name="form1" action="" namespace="/statistics"
			method="post" target="iframe1">
		<table width="700px" >
		<tr>
			<td style="line-height:34px;">选择统计时段:
			<input id="d5221" name="startDate" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
			至
			<input id="d5222" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>
			</td>
		</tr>
		<tr>
			<td style="line-height:34px;"><input type="submit" onclick="javascript:form1.action='list!listStatistics.do'" value="销售利润统计"> 
			</td>
		</tr>
			<tr>
				<td align="right" colspan="4" width="950px" rowspan=2>
				<iframe name="iframe1" id="iframe1" width="1024px" height="700" scrolling="Auto" frameborder="2"></iframe>
				</td>	
			</tr>
		</table>
		</center>
		</s:form>
		
		<br/>
		<br />
		<center>
		 <s:fielderror/>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
