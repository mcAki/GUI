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
		<s:form id="form1" name="form1" action="" namespace="/order"
			method="post" target="iframe1">
		<table width="1600px" height="700px" align="center">
		<tr>
			<td  colspan="4">
				<table width="700px" align="center">
					<tr>
						<td style="line-height:34px;">
							电话号码:
						</td>
						<td style="line-height:34px;">
							<s:textfield name="mobile" />
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td style="line-height:34px;">
							充值金额:
						</td>
						<td style="line-height:34px;">
							<s:textfield name="amount" />
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td style="line-height:34px;" colspan="4">
							<input type="submit" onclick="javascript:form1.action='list!listReversalOrder.do'" value="查询订单"> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="4" width="1600px" height="700px">
				<iframe name="iframe1" id="iframe1" width="100%" height="100%" scrolling="Auto" frameborder="2"></iframe>
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
