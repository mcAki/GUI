<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css" />
		<link href="../css/sys.css" rel="stylesheet" type="text/css" />
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
		<script type="text/javascript">
         	function checkUser(uid){
             	var flag = false;
             	$('#dtable tbody tr').each(
        				function(){
            				var nodata =$(this).eq(0).find("td").size(); 
            				if(nodata==1)
            				{
            					$(this).remove();
            				}
        				}
        			);
    			$('#dtable tbody tr td input').each(
    				function(){
    					if($(this).val()==uid){
    						flag = true;
    					}
    				}
    			);
    			return flag;
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
				<tr>
					<td colspan="5" align="center"><input icon="icon-back" type="button" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/mission/list!manage.do'"/>&nbsp;&nbsp;&nbsp;
					<input type="button" value="录用明细列表" onclick="javascript:window.location.href='${pageContext.request.contextPath}/mission/personal!findPersonalSend.action?missionId=${param.missionId}'";/></td>
				</tr>
			</table>
<br />
			<table width="950" border="0" align="center">
				<tr>
					<td align="left" width="700">
						<iframe
							src="${pageContext.request.contextPath}/mission/page!searchUser.do?missionId=${param.missionId}"
							name="iframe1" width="750px" height="800" scrolling="No"
							frameborder="0"></iframe>
							
					</td>
					<td width="250">
						<iframe
							src="${pageContext.request.contextPath}/mission/personal!findPersonalAdd.do?missionId=${param.missionId}"
							name="iframe2" id="iframe2" width="750px" height="800" scrolling="No"
							frameborder="0"></iframe>
					</td>
				</tr>
			</table>
		</div>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
