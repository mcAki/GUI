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
				window.parent.addUser(uid,username);
         		$("#dtable tbody tr td input[type='hidden']").each(
    				function(){
    					if($(this).val()==uid){
    						$(this).parent().find("a").eq(1).hide();
    					}
    				}
        		);
			}
		}
		function batchaddUser(itemName)
		{
			var datas ="";
			var items = document.getElementsByName(itemName);
		    for (var i = 0; i < items.length; i++) {
			    if(items[i].checked)
			    {
				    var uid = items[i].value;
				    $("#dtable tbody tr td input[type='hidden']").each(
        				function(){
        					if($(this).val()==items[i].value){
	        					var username = $(this).parent().parent().find("td").eq(1).html();
	        					addUser(items[i].value,username);
        					}
        				}
	        		);
				    datas+=uid+",";
			    }
		    }		    
		    $.post("${pageContext.request.contextPath}/mission/personal!addTeamPersonal.action?userIds="+ datas +"&missionId=${param.missionId}&teamId=${param.teamId}",null,null);
		}
		function showUserProfile(userId) {
		//杨氏简易弹出窗  common.js
		//alert(missionId);
		var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/viewUserProfile!viewUserProfile.do?userId='
						+ userId +'&operateCode=1'
						, 700, 600);
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
		<center>
		<table width="800" border="0" align="center">
			<tr>
				<td>
					<center>
					<div align="center">
			<s:form id="form0" name="form0"
			action="personal!findTeamPersonalIframe" namespace="/mission"
			method="post"><s:hidden name="missionId"></s:hidden><input type="hidden" name="teamId" value="${param.teamId }"/>
				多个查询请以逗号分隔:
				<center>
					<table>
					<tr>
						<td><s:radio list="%{#{1:'按姓名查找',2:'按电话号码查找',3:'按身份证查找'}}" name="radioVal" value="1" ></s:radio><br/>
							<textarea name="item" id="idItem" cols="20" rows="10" ></textarea></td>
					</tr>
					</table>
				</center>
				<input icon="icon-search"  type="submit" value="搜索" />
			</s:form>
		</div>
					<s:form id="form1" name="form1"
			action="personal!addTeamPersonal" namespace="/mission"
			method="post"><s:hidden name="missionId"></s:hidden>
						<display:table id="dtable" name="pageView" style="width:650px;"
							sort="external" pagesize="${pageSize}" cellspacing="1"
							class="list_tb"
							requestURI="${pageContext.request.contextPath}/mission/personal!findTeamPersonalIframe.do">
							<display:caption>当前项目还未被分配的志愿者</display:caption>
							<display:column title="选择">
								<input type="checkbox"
									id="chk" name="uuids"
									value="${dtable.usersByUserid.userId}"/>
							</display:column>
							<display:column title="真实姓名" property="usersByUserid.userName"></display:column>
							<display:column title="性别">
								<c:choose>
									<c:when test="${dtable.usersByUserid.gender==1}">男</c:when>
									<c:when test="${dtable.usersByUserid.gender==2}">女</c:when>
									<c:otherwise>未知</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="手机号码" property="usersByUserid.mobile"></display:column>
							<display:column title="注册时间" property="usersByUserid.createDate"></display:column>
							<display:column title="加入时间" property="selectedDatetime"></display:column>
							<display:column title="操作">
								<input name="uuid" type="hidden" value="${dtable.usersByUserid.userId}" />
								<a href="javascript:void(0)" onclick="showUserProfile('${dtable.usersByUserid.userId}')">查看</a>
								<a href="javascript:addUser('${dtable.usersByUserid.userId}','${dtable.usersByUserid.userName}');");">加入队伍</a>
							</display:column>
						</display:table>
						<input type="hidden" name="teamId" value="${param.teamId}"/>
						<input type="checkbox" onclick="checkAll(this, 'uuids','no')" />
						全选&nbsp;
				<input icon="icon-add-row" type="button" value="加入" onclick="javascript:batchaddUser('uuids')"/>
				</s:form>
					</center>
				</td>
			</tr>
		</table>
		</center>
		 <s:fielderror/>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
