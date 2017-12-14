<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css"/>
		<link href="../css/sys.css" rel="stylesheet" type="text/css"/>
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
		<script type="text/javascript">
         	function checkselect(){
         		var flag = false;
       		    if (!form1.uuids.length) {
       		        if (form1.uuids.checked == true) {
       		            flag = true;
       		        }
       		    }
       		    else {
       		        for (var i = 0; i < form1.uuids.length; i++) {
       		            if (form1.uuids[i].checked == true) {
       		                flag = true;
       		            }
       		        }
       		    }
       		    if (!flag) {
       		        alert("请至少选择一项!");
       		        return false;
       		    }
       		    else {
       		    	return true;
       		    }     
         	}
         	function delSelectUser()
         	{
       		    if(checkselect())
       		    {
       		    	document.form1.action="${pageContext.request.contextPath}/mission/personal!delPersonal.do";
  	         		document.form1.submit();
       		    }
			}
		</script>	
	</head>
	<body>
		<center> <s:form id="form1" name="form1"
							action="personal!findPersonalSend" namespace="/mission" method="post">
							<display:table id="dtable" name="pageView" style="width:250px;"
								sort="external" pagesize="${pageSize}" cellspacing="1"
								class="list_tb"
								requestURI="${pageContext.request.contextPath}/mission/personal!findPersonalAdd.action">
								<display:caption>当前项目所有选定志愿者</display:caption>
								<display:column title="选择">
									<input type="checkbox" id="uuids" name="uuids" value="${dtable.missionPersonlId}" />
								</display:column>
								<display:column title="真实姓名" property="usersByUserid.userName"></display:column>
								<display:column title="操作">
									<input name="uuid" type="hidden"
										value="${dtable.usersByUserid.userId}" />
									<a onclick="return confirm('是否确定删除?');" href="${pageContext.request.contextPath}/mission/personal!delPersonal.do?uuids=${dtable.missionPersonlId}&missionId=${param.missionId}&status=1">删除</a>
								</display:column>
							</display:table>&nbsp;
							<input type="checkbox" onclick="checkAll(this, 'uuids','no')" />全选
							<input type="button" value="删除" onclick="delSelectUser()"/>
							<input type="hidden" name="status" value="1" />
							<input type="hidden" name="uuidss" value="" />
							<input type="hidden" name="missionId" value="${param.missionId}" />
						</s:form> </center>
	</body>
</html>
