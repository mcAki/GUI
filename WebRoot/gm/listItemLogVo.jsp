<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
				<%@ include file="../common/incHead.jsp"%>
	</head>
	<body>
		<center>
		<%@ include file="../common/incBanner.jsp"%>
		<s:property value="#request.message" />
		<br />
							<s:form id="form1" name="form0"
							action="list!listItemLogVo.do" namespace="/gmmanage"
							method="post">
								<table >
									<tr >
									    <td>玩家昵称:<s:textfield name="rolename"/></td>
									    <td>道具id:<s:textfield name="itemTemplateId"/></td>
									    <td>请选择日期:&nbsp;</td>
										<td colspan="2">&nbsp;<input id="d52211" name="startDate" class="Wdate" type="text" onFocus="var d52222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();}, minDate:'%y-{%M-1}-%d' ,maxDate:'#F{$dp.$D(\'d52222\')||\'%y-%M-%d\'}'})"/>
												至
											<input id="d52222" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})"/>&nbsp;</td>
										<td>&nbsp;&nbsp;&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
									</tr>
								</table>
							</s:form>
		<br />
		<br />
		<display:table id="idListTb" name="pageView" style="width:650px;"
			pagesize="20" cellspacing="1" class="list_tb"
			requestURI="${pageContext.request.contextPath}/gmmanage/list!listItemLogVo.do">
			<display:caption>道具跟踪列表</display:caption>
			<display:column property="id" title="ID" />
			<display:column property="rolename" title="玩家昵称" />
			<display:column title="操作时间">
				<fmt:formatDate value="${idListTb.timeShow}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</display:column>
			<display:column property="itemname" title="道具名称" />
			<display:column title="操作逻辑" >
				<c:choose>
					<c:when test="${idListTb.handler=='addItem'}">增加道具</c:when>
					<c:when test="${idListTb.handler=='deductItem'}">删除道具</c:when>
				</c:choose>
			</display:column>
			<display:column property="remarksShow" title="备注" />
		</display:table> </center>
		<br />
		<br />

		<a href="staff!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
