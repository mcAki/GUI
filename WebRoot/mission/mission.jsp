<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
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

		//alert(returnVal.districtId);
		//alert(returnVal.districtName);
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
	function gotoSave(operateCode) {
		var formname = document.getElementById("form1");
		//var path = ${pageContext.request.contextPath};
		switch(operateCode){
			case 1:
				formname.action = '${pageContext.request.contextPath}/mission/doAdd.do?operateCode='+operateCode;
				break;
			case 20:
				formname.action = '${pageContext.request.contextPath}/mission/doAdd.do?operateCode='+operateCode;
				break;
			default:
				alert('提交出错');
				return;
		}
		
		formname.submit();
		
	}
	//保存并发送审核
	function gotoSaveEx() {
		
	}
	
	function showUserForManager(){
		
		//计算弹出位置
		
		height = 500;
		width = 400;
		//barHeight=30;
		//top = (screen.Height/2) - (height/2) - barHeight;
		//left = (screen.width/2) - (width/2);
		

		//要查询的链接
		var url="${pageContext.request.contextPath}/users/list!listUserForSelectManager.do?";
		//为了处理缓存加入随机时间
		url += "&date=" + new Date().getTime();
		
		//弹出参数
		var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth="+ width +"px;dialogHeight=" + height + "px";
		
		//创建传入参数
		var obj = new Object();
		obj.name = "参数";
		var returnVal = window.showModalDialog(url,obj,"dialogWidth=600px;dialogHeight=500px");
		if(!$.isEmptyObject(returnVal)){
			$("#userId").val(returnVal.uid);
			$("#userName").val(returnVal.username);
		}
	}
	
	
	//保存
	function saveState(operateCode) {
		var formname = document.getElementById("form1");
		//var path = ${pageContext.request.contextPath};
		
		switch(operateCode){
			case 25:
				formname.action = '${pageContext.request.contextPath}/missionVerification/doAdd.do?missionId=${mission.missionId}&operateCode='+operateCode;
				break;
			case 26:
				formname.action = '${pageContext.request.contextPath}/missionVerification/doAdd.do?missionId=${mission.missionId}&operateCode='+operateCode;
				break;
			default:
				alert('提交出错');
				return;
		}
		
		formname.submit();
		
	}
	
	</script>
</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<s:form id="form1" name="form1" action="doUpdate" namespace="/mission"
			method="post">
			<center><s:fielderror></s:fielderror></center>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<table width="800" border="0" align="center" cellspacing="3"
				class="form_tb">
				<caption>
					<h2>
						<strong>项目发布</strong>
					</h2>
				</caption>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td align="right">项目名称:</td>
					<td width="183" align="left" style="white-space: pre-line"><s:textfield name="mission.subject" id="subj"/></td>
					<td align="right">项目类型:</td>
					<td align="left" style=""><s:select list="missionTypes" name="mission.missionType.missionTypeId" listKey="missionTypeId" listValue="typeName"></s:select></td>
					<td width="50">&nbsp;</td>
				</tr>
				
				<tr>
					<td width="145" align="right">项目负责人:</td>
					<s:hidden name="mission.usersByManagerId.userId" id="userId"></s:hidden>	
					<td align="left" class="nowrap"><s:textfield name="mission.usersByManagerId.userName" id="userName" readonly="true"/>
					<input icon="icon-btncom" type="button" value="选择" onclick="showUserForManager()"/>
					</span></td>
					<td width="150" align="right">所属区域:</td>
					<td width="238" align="left" style="white-space: nowrap;">
						<s:hidden name="mission.district.districtId"></s:hidden>
						<s:textfield name="mission.districtName" id="districtName" readonly="true"/>
						<input icon="icon-btncom" type="button" value="选择" onClick="showTree(true)"/>
					</td>
                    <td></td>
				</tr>
				
				<tr>
					<td align="right">报名需求人数上限:</td>
					<td align="left"><s:textfield name="mission.applyPersonUpperlimit"/></td>
					<td align="right">报名需求人数:</td>
					<td align="left"><s:textfield name="mission.applyPersonInneed"/></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="right">应具备服务时长:</td>
					<td align="left"><s:textfield name="mission.requireServiceTime"/></td>
					<td align="right">应具备培训时长:</td>
					<td align="left"><s:textfield name="mission.requireTrainingTime"/></td>
					<td></td>
                </tr>
				<tr>
					<td align="right">报名截止日期:</td>
					<td align="left"><s:textfield name="mission.applyDeadline" onclick="WdatePicker()" readonly="true" /></td>
						<td align="right">打卡模式:</td>
					<td align="left" class="nowrap"><s:radio listKey="key" list="%{#{1:'打卡机',2:'短信'}}" name="mission.checkOnMode" /></td>
                    <td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="right">是否发布至网站:</td>
					<td align="left"><s:radio name="mission.isPublishWebsite" list="%{#{'0':'是','1':'否'}}"></s:radio></td>
				<td align="right">最高权限级别:</td>
					<td align="left" class="nowrap"><s:radio name="mission.highestPosition" list="%{#{1:'大队长',2:'中队长',3:'小队长'}}"></s:radio></td>

					<td>&nbsp;</td>
                </tr>
				
				<tr>
					<td align="right">机动任务:</td>
					<td align="left" class="nowrap"><s:radio name="mission.isUntime" list="%{#{'0':'是','1':'否'}}"></s:radio></td>
							<td align="right">项目安全级别:</td>
					<td align="left"><s:radio name="mission.securityLevel" list="%{#{'0':'普通级别','1':'标准安保','2':'高度安全'}}" /></td>

                    <td>&nbsp;</td>
				</tr>
				
				<tr>
					<td height="25" align="right">实施日期:</td>
					<td colspan="3" align="left">
						<s:textfield maxlength="5" name="mission.startDate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						&nbsp;&nbsp;至&nbsp;&nbsp;
						<s:textfield id="myDate" name="mission.endDate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td height="9" align="right">举办地点:</td>
					<td colspan="3" align="left"><s:textfield cssClass="txtW5" name="mission.venue" /></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="right">详细地址:</td>
					<td colspan="3" align="left"><s:textfield cssClass="txtW5" name="mission.venueAddress" /></td>
					<td>&nbsp;</td>
				</tr>
       			 
       			<tr>
  	   				<td align="right">项目简介:</td>
  	  				<td colspan="4" align="left"><s:textarea name="mission.summary" cssClass="txtW5" rows="4"></s:textarea></td>
       			</tr>
				
				<tr>
  	   				<td align="right">项目备注:</td>
  	  				<td colspan="4" align="left"><s:textarea name="mission.remark" cssClass="txtW5" rows="4"></s:textarea></td>
       			</tr>
       			
				<tr>
  	   				<td align="right">项目内容:</td>
  	  				<td colspan="4" align="left"><s:textarea name="mission.detailInfo" cssClass="txtW5" rows="6"></s:textarea></td>
       			</tr>
       			
       			
       			<!--
                
             	
                <tr>
					<td align="right">&nbsp;</td>
					<td colspan="3" align="left">
						<fieldset class="fs">
							<legend>项目内容</legend><s:textarea name="mission.detailInfo" />
						</fieldset>
					</td>
					<td>&nbsp;</td>
				</tr>
				
             	<tr>
					<td align="right">&nbsp;</td>
					<td colspan="3" align="left">
						<fieldset class="fs">
							<legend>任务简介</legend><s:textarea cssClass="txtW45" name="mission.summary" />
						</fieldset>
					</td>
					<td>&nbsp;</td>
				</tr>
             	
             	<tr>
					<td align="right">&nbsp;</td>
					<td colspan="3" align="left">
						<fieldset class="fs">
							<legend>项目备注</legend><s:textarea name="mission.remark" />
						</fieldset>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				--><tr>
					<td align="right">  <s:hidden name="missionId"/>
																			   <s:hidden name="mission.missionId"/></td>
					<td colspan="3" align="left">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="right">&nbsp;</td>
					<td colspan="3" align="left">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		  <td>&nbsp;</td>
			
			<table width="800" border="0" align="center" cellspacing=5" class="form_tb">
				<caption>
					任 务 功 能
				</caption>
				<tr>
					<td>
						<c:choose>
							<c:when test="${mission.missionState.missionStateId==0}">
								<div>
									新建项目:&nbsp;&nbsp;
									<input type="button" value="新建" onClick="javascript: gotoSetup();">
								</div>
							</c:when>
							<c:otherwise>
								<div>
									
									<input icon="icon-save" type="button" value="保存" onClick="javascript: gotoSave(1);"/>
									直接提交审核:&nbsp;&nbsp;
									<input icon="icon-save" type="button" value="保存并申请审批" onClick="javascript: gotoSave(20);"/>
									&nbsp;&nbsp;&nbsp;
									<input icon="icon-reset" type="button" value="返回" />
								</div>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
