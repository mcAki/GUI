<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function showTree(hasChildNodeFlag){
			//window.location='${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag;
			t = screen.Height / 2 - 225;
	        l = screen.width / 2 - 300;
	        h = 500;
	        w = 400;
	        
	        //window.open('${pageContext.request.contextPath}/tree!showDistrictTreeById.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag=true', 'showMessage', feature);
			
			var features="resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth=400px;dialogHeight=500px";
			var obj = new Object();
			obj.name="51js";
			var returnVal=window.showModalDialog('${pageContext.request.contextPath}/tree!showAjaxTree.html?districtId=4028818811a15abe0111a1a517480004&hasChildNodeFlag='+hasChildNodeFlag+'&date='+ new Date().getTime(),obj,"dialogWidth=400px;dialogHeight=500px");
			
			//alert(returnVal);
			//document.getElementById("districtName").value=returnVal.districtName; 
			
            //document.getElementById("districtId").value=returnVal.districtId; 
            if(!$.isEmptyObject(returnVal)){
            	$("#districtId").val(returnVal.districtId);
            	$("#districtName").val(returnVal.districtName);
			}
		}
	
		function showGroupForLog(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/users/list!listGroupForLog.do?typeId=5";
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
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  
  <%--
  
  	<s:form name="strutsForm" action="doAdds" namespace="/users" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<s:hidden name="useraccount.useraccountId"/>
  	<s:hidden name="parentUsers.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>商户信息</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td align="right">所属组:</td>
  	    <td align="left">二级商户<s:hidden name="users.usergroup.id" value="5"/></td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">商户等级:</td>
  	    <td align="left"><s:select cssClass="txtW2" list="userLevels" name="users.userLevel.userLevelId" listKey="userLevelId" listValue="userLevel"  ></s:select></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">默认警示界限:</td>
        <td align="left"><s:textfield name="users.defaultAlarm"/>&nbsp;(不填默认根据等级区分)</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
  	  <tr>
  	    <td width="105" align="right">登录名称:</td>
  	    <td align="left"><s:textfield name="users.loginName"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">登录密码:</td>
  	    <td width="212" align="left"><s:password name="users.userPassword" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">用户名称:</td>
  	   <td align="left"><s:textfield name="users.userName"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">用户编码:</td>
  	   <td align="left"><s:textfield name="users.userCode"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">法人代表:</td>
  	   <td align="left"><s:textfield name="users.corporateRepresentative"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">联系人:</td>
  	   <td align="left"><s:textfield name="users.contactPeople"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">email:</td>
  	   <td align="left" ><s:textfield name="users.email"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
  	  <tr>
        <td align="right">手机号码:</td>
  	   	<td align="left" ><s:textfield name="users.mobile"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      	
  	  <tr>
        <td align="right" >联系地址:</td>
  	    <td colspan="4" align="left"><s:textarea name="users.address" cssClass="txtW5" rows="4"></s:textarea></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right" >性别:</td>
  	    <td align="left" ><s:select cssClass="txtW2" name="users.gender" list="%{#{0:'===请选择性别===',1:'男',2:'女'}}"/></td>
      </tr>
      
      <tr>
        <td align="right" >是否显示在线充值:</td>
  	    <td align="left" >
  	    <input type="radio" name="users.isShowRecharge" value="1" checked="checked" /> 显示
  	    <input type="radio" name="users.isShowRecharge" value="0" /> 不显示
  	    </td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
  	    
      </tr>
     <tr>
  	    <td align="right">用户类型:</td>
  	    <td  align="left"><s:hidden name="users.userType" value="3" />二级经销商</td> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">交易类型:</td>
  	    <td  align="left"><s:select cssClass="txtW2" name="users.tradeType" list="%{#{0:'===请选择交易类型===',10:'所有',20:'网站',30:'POS机'}}"/></td> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">终端机号:</td>
  	   	<td align="left" ><s:textfield name="users.terminalNo"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">可冲正次数:</td>
  	   	<td align="left" ><s:textfield name="users.reversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">最大可冲正次数:</td>
  	   	<td align="left" ><s:textfield name="users.maxReversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >备注:</td>
  	   <td colspan="4" align="left"><s:textarea name="users.remark" cssClass="txtW5" rows="4"></s:textarea></td>
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
   --%>
    <div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<p class="jjcx">
							添加商户
						</p>
					</div>
					<div class="modify">
					<center>
	<s:form name="strutsForm" action="doAdds" namespace="/users" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<s:hidden name="useraccount.useraccountId"/>
  	<s:hidden name="parentUsers.userId" />
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>商户信息</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td width="145" align="right">
								用户组别:
							</td>
					  	    <s:hidden name="users.usergroup.id" id="userId"></s:hidden>
							<td align="left" class="nowrap">
								<s:textfield name="users.usergroup.groupName"
									id="userName" readonly="true" />
								<input icon="icon-btncom" type="button" value="选择"
									onclick='showGroupForLog();'/>
							</td>
							<td>&nbsp;</td>
					        <td>&nbsp;</td>
					        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">商户等级:</td>
  	    <td align="left"><s:select cssClass="txtW2" list="userLevels" name="users.userLevel.userLevelId" listKey="userLevelId" listValue="userLevel"  ></s:select></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">默认警示界限:</td>
        <td align="left"><s:textfield name="users.defaultAlarm"/>&nbsp;(不填默认根据等级区分)</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
  	  <tr>
  	    <td width="105" align="right">登录名称:</td>
  	    <td align="left"><s:textfield name="users.loginName"/></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">登录密码:</td>
  	    <td width="212" align="left"><s:password name="users.userPassword" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
  	    <td align="right">交易密码:</td>
  	    <td width="212" align="left"><s:password name="users.businessPassword" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">用户名称:</td>
  	   <td align="left"><s:textfield name="users.userName"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">用户编码:</td>
  	   <td align="left"><s:textfield name="users.userCode"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">法人代表:</td>
  	   <td align="left"><s:textfield name="users.corporateRepresentative"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">联系人:</td>
  	   <td align="left"><s:textfield name="users.contactPeople"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">email:</td>
  	   <td align="left" ><s:textfield name="users.email"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      <tr>
       <td align="right">QQ号码:</td>
  	   <td align="left" ><s:textfield name="users.qq"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
  	  <tr>
        <td align="right">手机号码:</td>
  	   	<td align="left" ><s:textfield name="users.mobile"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      	
  	  <tr>
        <td align="right" >联系地址:</td>
  	    <td colspan="4" align="left"><s:textarea name="users.address" cssClass="txtW5" rows="4"></s:textarea></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right" >性别:</td>
  	    <td align="left" ><s:select cssClass="txtW2" name="users.gender" list="%{#{0:'===请选择性别===',1:'男',2:'女'}}"/></td>
      </tr>
      
      <tr>
        <td align="right" >是否显示在线充值:</td>
  	    <td align="left" >
  	    <input type="radio" name="users.isShowRecharge" value="1" checked="checked" /> 显示
  	    <input type="radio" name="users.isShowRecharge" value="0" /> 不显示
  	    </td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
  	    
      </tr>
      
      <tr>
        <td align="right" >是否显示供货商:</td>
  	    <td align="left" >
  	    <input type="radio" name="users.isShowSupply" value="1" checked="checked" /> 显示
  	    <input type="radio" name="users.isShowSupply" value="0" /> 不显示
  	    </td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
  	    
      </tr>
     <tr>
  	    <td align="right">用户类型:</td>
  	    <td  align="left"><s:hidden name="users.userType" value="3" />二级经销商</td> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">交易类型:</td>
  	    <td  align="left"><s:select cssClass="txtW2" name="users.tradeType" list="%{#{0:'===请选择交易类型===',10:'所有',20:'网站',30:'POS机'}}"/></td> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">终端机号:</td>
  	   	<td align="left" ><s:textfield name="users.terminalNo"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">可冲正次数:</td>
  	   	<td align="left" ><s:textfield name="users.reversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
        <td align="right">最大可冲正次数:</td>
  	   	<td align="left" ><s:textfield name="users.maxReversalCount"/></td>
        <td>&nbsp;</td>
  	    <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right" >备注:</td>
  	   <td colspan="4" align="left"><s:textarea name="users.remark" cssClass="txtW5" rows="4"></s:textarea></td>
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
  </center>

					</div>
				</div>
			</div>
		</div>	
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

