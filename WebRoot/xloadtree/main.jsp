<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>资料管理</title>
		<script type="text/javascript" language="javascript">
    <!--
    function autoResize()
    {
    var frm = document.getElementById("001");
    var subWeb = document.frames ? document.frames["001"].document : frm.contentDocument;
    if(frm != null && subWeb != null)
    { frm.height = subWeb.body.scrollHeight+20+'px';}
    
    }
    //-->
    </script>
		<meta http-equiv="Content-Type">
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0">

		<table id="__01" width="102%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="20%" height="100%" valign="top">
					<iframe src="<%=path%>/xloadtree/adminTree.jsp" width="170"
						height="100%" frameborder="0" scrolling="yes" name="menu"></iframe>
				</td>
				<td valign="top">
					<iframe src="" width="100%" height="100%"
						 frameborder="0" scrolling="yes"  name=mainFrame></iframe>
				</td>

			</tr>
		</table>
	</body>
</html>