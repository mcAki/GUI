<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css"/>
		<link href="../css/sys.css" rel="stylesheet" type="text/css"/>
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="../js/admin.js"></script>
		<script type="text/javascript">
			function addUser(uid,username){
				var flag = window.parent.checkUser(uid);
				if(!flag)
				{
					window.parent.iframe2.form1.action="${pageContext.request.contextPath}/mission/personal!addOrUpdate.action";
					window.parent.iframe2.form1.uuidss.value=uid;  
					window.parent.iframe2.form1.missionId.value=${param.missionId};
					window.parent.iframe2.form1.submit();
	         		$("#dtable tbody tr td input[type='hidden']").each(
	    				function(){
	    					if($(this).val()==uid){
	    						$(this).parent().find("a").eq(1).hide();
	    					}
	    				}
	        		);
				}
			}
			function search()
			{
				var districtName = document.getElementById("districtName");
				if(districtName.value=="")
				{
					var districtId = document.getElementById("districtId");
					districtId.value = "";
				}
				document.form0.action="${pageContext.request.contextPath}/mission/page!searchUser.do";
				document.form0.submit();
			}
			function batchAddUser(itemName)
			{
				if(checkselect(form1))
				{
					var datas ="";
					var items = document.getElementsByName(itemName);
				    for (var i = 0; i < items.length; i++) {
					    if(items[i].checked)
					    {
						    var uid = items[i].value;
						    datas+=uid+",";
					    }
				    }
				    window.parent.iframe2.form1.action="${pageContext.request.contextPath}/mission/personal!addOrUpdate.action";
				    window.parent.iframe2.form1.uuidss.value=datas;  
				    window.parent.iframe2.form1.missionId.value=${param.missionId};
				    window.parent.iframe2.form1.submit();
				}
			}
        </script>
		<script type="text/javascript">
	function showTree(hasChildNodeFlag) {
		//window.location='${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag;
		t = screen.Height / 2 - 225;
		l = screen.width / 2 - 300;
		h = 500;
		w = 400;

		//window.open('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag=true', 'showMessage', feature);

		var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth=400px;dialogHeight=500px";
		var obj = new Object();
		obj.name = "51js";
		var returnVal = window
				.showModalDialog(
						'${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='
								+hasChildNodeFlag
								+ '&date='
								+ new Date().getTime(), obj,
						"dialogWidth=400px;dialogHeight=500px");

		//alert(returnVal);
		//document.getElementById("districtName").value=returnVal.districtName; 

		//document.getElementById("districtId").value=returnVal.districtId; 
		if(!$.isEmptyObject(returnVal)){
			$("#districtId").val(returnVal.districtId);
			$("#districtName").val(returnVal.districtName);
		}
	}
	//新建
	function gotoSetup(){
		document.getElementById("form1").action = '${pageContext.request.contextPath}/mission/page!add.do';
		document.getElementById("form1").submit();
	}
	//保存
	function gotoSave() {
		document.getElementById("form1").action = '${pageContext.request.contextPath}/mission/doAdd.do';
		document.getElementById("form1").submit();
	}
	//保存并发送审核
	function gotoSaveEx() {
		document.getElementById("form1").action = '${pageContext.request.contextPath}/mission/doAddEx.do';
		document.getElementById("form1").submit();
	}
	
	function showUserProfile(userId) {
		//杨氏简易弹出窗  common.js
		//alert(missionId);
		 ezModal(
				'${pageContext.request.contextPath}/usersutils/viewUserProfile!viewUserProfile.do?userId='
						+ userId + '&operateCode=1' ,
						 700, 600);
		//jq检测是否为空
		//if(!$.isEmptyObject(returnVal)){
		//赋值!
		//	$("#userId").val(returnVal.uid);
		//	$("#currentLeaderName").val(returnVal.username);
		//}
	}
</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
			<div align="center">
			<s:form id="form0" name="form0"
			action="page!searchUser" namespace="/mission"
			method="post" onsubmit="search()">
				搜索 地区
				<input name="districtId" id="districtId" type="hidden" value="${param.districtId}"/>
				<input name="missionId" id="missionId" type="hidden" value="${param.missionId}"/>
				<input name="districtName" id="districtName" value="${param.districtName}"/>
				<input icon="icon-prop" type="button" value="选择" onclick=showTree(true); />
				姓名:
				<input type="text" name="username" size="10" value="${param.username}"/>
				性别:
				<input type="radio" name="usersex" value="1" ${param.usersex==1?'checked':''}/>
				男
				<input type="radio" name="usersex" value="2" ${param.usersex==2?'checked':''}/>
				女&nbsp;
				<input icon="icon-search" onclick="search()" type="button" value="搜索"/>
				</s:form>
				<br />
				<c:if test="${not empty param.districtId||not empty param.username||not empty usersex}">
					<c:set value="searchUser.do" var="url"/>
				</c:if>
				<s:form id="form1" name="form1"	action="page!searchUser" namespace="/mission" method="post">
				<display:table id="dtable" name="pageView" style="width:600px;"
					sort="external" pagesize="${pageSize}" cellspacing="1"
					class="list_tb"
					requestURI="${pageContext.request.contextPath}/mission/page!${empty pageScope.url?'searchUser.do':pageScope.url}">
					<display:caption>所有志愿者</display:caption>
					<display:column title="选择">
						<input type="checkbox" id="chk" name="uuid" value="${dtable.userId}" />
					</display:column>
					<display:column title="真实姓名" property="userName"></display:column>
					<display:column title="证件号" property="idcardCode"></display:column>
					<display:column title="性别">
						<c:choose>
							<c:when test="${dtable.gender==1}">男</c:when>
							<c:when test="${dtable.gender==2}">女</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="注册时间" property="createDate" format="{0,date,yyyy-MM-dd hh:mm:ss}"></display:column>
					<display:column title="操作">
						<input name="uuids" type="hidden" value="${dtable.userId}" />
						<a href="javascript:void(0);" onclick="showUserProfile('${dtable.userId}');">查看</a>
						<a href="javascript:addUser('${dtable.userId}','${dtable.userName}');");">添加</a>
					</display:column>
				</display:table>&nbsp;
				<input type="checkbox" onclick="checkAll(this, 'uuid','no')" />
				全选
				<input type="hidden" name="missionId" value="${mission.missionId}" />
				<input icon="icon-add-row" type="button" value="加入" onclick="javascript:batchAddUser('uuid')"/><br/>
			</s:form>
			</div>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
