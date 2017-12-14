<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	</head>  
	
	<body>
		<s:form name="strutsForm" action="detailed!modifyDetailed" namespace="/usersutils" method="post">
			<table width="800" border="0" align="center" cellspacing="3" class="form_tb">
				<s:hidden name="user.userId" />
				<caption>志愿者详细信息注册</caption>
				
				<tr>
					<td width="167" align="right">培训时长:</td>
					<td width="203" align="left"><s:textfield readonly="true" name="users.trainingTime"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td width="182" align="right">服务时长:</td>
					<td width="219" align="left"><s:textfield readonly="true" name="users.serviceTime"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
	  
				<tr>
					<td width="167" align="right">中文姓:</td>
					<td width="203" align="left"><s:textfield name="users.firstnameCn"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td width="182" align="right">中文名:</td>
					<td width="219" align="left"><s:textfield name="users.lastnameCn"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">英文姓:</td>
					<td align="left"><s:textfield name="users.firstnameEn"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">英文名:</td>
					<td align="left"><s:textfield name="users.lastnameEn"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">出生国家 /地区:</td>
					<td align="left"><s:select id="selBirthCountry" cssClass="txtW2" name="usersDetail.birthCountry" list="districtNamesMap" listKey="districtId" value="usersDetail.birthCountry" ></s:select></td>
					<td align="right">证件所属国家 /地区:</td>
					<td align="left"><s:select id="selIdcardCountry" cssClass="txtW2" name="usersDetail.idcardCountry" list="districtNamesMap" listKey="districtId" value="usersDetail.idcardCountry" ></s:select></td>	
				</tr>
				
				<tr>
					<td align="right">出生日期:</td>
					<td align="left"><s:textfield name="usersDetail.birthday" onclick="WdatePicker()" readonly="true" /><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">电子邮箱地址:</td>
					<td align="left"><s:textfield name="users.email"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">&nbsp;</td>
					<td align="left">&nbsp;</td>
					<td align="right"> 民族:</td>
					<td align="left"><s:textfield name="usersDetail.ethnicity"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">曾用姓:</td>
					<td align="left"><s:textfield name="usersDetail.firstnameFormer"></s:textfield></td>
					<td align="right">曾用名:</td>
					<td align="left"><s:textfield name="usersDetail.lastnameFormer"></s:textfield></td>
				</tr>
				
				<tr>
					<td align="right">身高:</td>
					<td align="left"><s:textfield name="usersDetail.height"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">体重:</td>
					<td align="left"><s:textfield name="usersDetail.weight"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right"> 鞋码:</td>
					<td align="left"><s:textfield name="usersDetail.shoes"></s:textfield></td>
					<td align="right">身体状况:</td>
					<td align="left"><s:textfield name="usersDetail.health"/></td>
				</tr>
				
				<tr>
					<td align="right">保险受益人:</td>
					<td align="left"><s:textfield name="usersDetail.beneficiary"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">与您的关系:</td>
					<td align="left"><s:textfield name="usersDetail.beneficiaryPerson"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td> 
				</tr>
				
				<tr>
					<td colspan="4"> <center><b>户籍所在地:</b></center></td>
				</tr>
				
				<tr>
					<td align="right">国家 / 地区:</td>
					<td align="left"><select id="idCountry" class="txtW2"></select><s:hidden name="country" id="hidCountry"></s:hidden></td>
					<td align="right">省/自治区 / 直辖市:</td>
					<td align="left"><select id="idProvince" class="txtW2"></select><s:hidden name="province" id="hidProvince"></s:hidden></td>
				</tr>
				
				<tr> 
					<td align="right">市 / 县:</td>
					<td align="left"><select id="idCity" class="txtW2"></select><s:hidden name="city" id="hidCity" ></s:hidden></td>
					<td align="right">详细地址:</td>
					<td align="left"><s:textfield name="users.censusAddress"></s:textfield></td>
				 </tr>
				 
				 <tr>
					<td colspan="4"><center><b>身份证明:</b></center></td>
				 </tr>
				 
				<tr>
					<td align="right">证件号码:</td>
					<td colspan="3" align="left"><s:textfield name="users.idcardCode"></s:textfield><span style="color: #ff6600;">&nbsp;*（使用英文输入法）</span></td>
				</tr>
				<tr>
					<td align="right"> 证件类型:</td>
					<td colspan="3" align="left"><s:select cssClass="txtW2" name="users.idcardType" list="%{#{'0':'护照','1':'中国居民身份证','2':'中国人民解放军军官证或士兵证','3':'中国武警证件','4':'台湾居民来往大陆通行证','5':'港、澳居民来往内地通行证','6':'公安现役警官证件或士兵证（边防系统）','7':'公安现役警官证件或士兵证（警卫系统）','8':'公安现役警官证件或士兵证（消防系统）'}}"></s:select>
				</tr>
				
				<tr>
					<td align="right">证件有效期截止日期:</td>
					<td colspan="3" align="left"><s:textfield name="usersDetail.idcardExpires"></s:textfield><span style="color: #ff6600;">&nbsp;*注意:对于身份证的有效期是永久的中国内地申请人，</br>其身份证的失效期应统一填写为2030年12月31日</span> </td>
				</tr>
				 
				<tr>
					<td colspan="4"><center><b>学校证明:</b></center></td>
				</tr>
				 
				 <tr>
					<td align="right">学校:</td>
					<td align="left"><s:textfield name="usersDetail.school"/></td>
					<td align="right">哪个校区:</td>
					<td align="left"><s:textfield name="usersDetail.schoolCampus"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">校区所在的区(市):</td>
					<td align="left"><s:textfield name="usersDetail.schoolCampusArea"/></td>
					<td align="right">院系:</td>
					<td align="left"><s:textfield name="usersDetail.department"/></td>
				</tr>
				
				 <tr>
					<td align="right">专业:</td>
					<td align="left"><s:textfield name="usersDetail.major"/></td>
					<td align="right">班级:</td>
					<td align="left"><s:textfield name="usersDetail.clazz"/></td>
				</tr>
				
				<tr>
					<td align="right">在读学历:</td>
					<td align="left"><s:textfield name="usersDetail.degree"/></td>
					<td align="right">学校详细地址:</td>
					<td align="left"><s:textfield name="usersDetail.schoolAddress"/></td>
				</tr>
				
				<tr>
					<td align="right">入学时间:</td>
					<td align="left"><s:textfield name="usersDetail.entranceDate"/></td>
					<td align="right">预计毕业时间:</td>
					<td align="left"><s:textfield name="usersDetail.graduationDate"/></td>
				</tr>
				
				<tr>
					<td colspan="4"><center><b>现在居住地址:</b></center></td>
				</tr>
				
				<tr>
					<td align="right">国家 / 地区:</td>
					<td align="left"><select id="idCountry" class="txtW2"></select> <s:hidden name="country" id="hidCountry"></s:hidden></td>
					<td align="right">省/自治区 / 直辖市:</td>
					<td align="left"><select id="idProvince" class="txtW2"></select><s:hidden name="province" id="hidProvince"></s:hidden></td>
				</tr>
				
				<tr>
					<td align="right">市 / 县:</td>
					<td align="left"><select id="idCity" class="txtW2"></select><s:hidden name="city" id="hidCity" ></s:hidden></td>
					<td align="right">所在区:</td>
					<td align="left"><select id="idArea" class="txtW2"></select><s:hidden name="area" id="hidArea" ></s:hidden></td>
				</tr>
				
				<tr>
					<td align="right">详细地址:</td>
					<td align="left"><s:textfield name="usersDetail.address"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">邮政编码:</td>
					<td align="left"><s:textfield name="usersDetail.postcode"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td colspan="4"><center><b>实际情况:</b></center></td>
				</tr>
				
				<tr>
					<td align="right">实际工作单位/就读学校名称: </td>
					<td align="left"><s:textfield name="usersDetail.workingCompany"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">工作单位/就读学校详细地址:</td>
					<td align="left"><s:textfield name="usersDetail.workingAdress"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">目前的职业:</td>
					<td align="left"><s:textfield name="usersDetail.job"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">申请人移动电话:</td>
					<td align="left"><s:textfield name="users.mobile"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">申请人固定电话:</td>
					<td align="left"><s:textfield name="usersDetail.telephone"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
					<td align="right">其他联系电话:</td>
					<td align="left"><s:textfield name="usersDetail.contaceName"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				</tr>
				
				<tr>
					<td align="right">其他联系移动电话:</td>
					<td colspan="3" align="left"><s:textfield name="usersDetail.contaceMobile"></s:textfield><span style="color: #ff6600;">&nbsp;*</span></td>
				 </tr>
				 
				<tr>
					<td colspan="4"><center><b>语言技能情况:</b></center></td>
				 </tr>
				 
				 <tr>
					<td align="right">语言</td>
					<td align="left"><s:textfield name="usersDetail.laguage"></s:textfield></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				 <tr>
					<td align="right">外语言一:</td>
					<td align="left"><s:textfield name="usersDetail.foreignLaguage1"></s:textfield></td>
					<td align="right">外语一水平:</td>
					<td align="left"><s:textfield name="usersDetail.foreignLaguageLevel1"></s:textfield></td>
				</tr>
				
				<tr>
					<td align="right">外语言二:</td>
					<td align="left"><s:textfield name="usersDetail.foreignLaguage2"></s:textfield></td>
					<td align="right">外语一水平:</td>
					<td align="left"><s:textfield name="usersDetail.foreignLaguageLevel2"></s:textfield></td>
				</tr>
				
				<tr>
					<td colspan="4"><center><b>信息来源</b></center></td>
				</tr>
				<tr>
					<td align="right">来源单位:</td>
					<td align="left"><s:textfield name="usersDetail.sourceCompany"></s:textfield></td>
					<td align="right">亚组委招募部门:</td>
					<td align="left"><s:textfield name="usersDetail.agocCompany"></s:textfield></td>
				</tr>
				
				<tr>
					<td align="right">志愿者身份:</td>
					<td align="left"><s:textfield name="usersDetail.userIdentity"></s:textfield></td>
					<td align="right">职位:</td>
					<td align="left"><s:textfield name="usersDetail.agocPosition"></s:textfield></td>
				</tr>
				
				 <tr>
					<td align="right">场馆:</td>
					<td align="left"><s:textfield name="usersDetail.venue"></s:textfield></td>
					<td align="right">业务口:</td>
					<td align="left"><s:textfield name="usersDetail.functionalArea"></s:textfield></td>
				</tr>
				
				<tr>
					<td align="right">是否上传照片:</td>
					<td  align="left"><s:textfield name="usersDetail.isUploadedPhoto"></s:textfield></td>
					<td align="right">注册编码:</td>
					<td align="left"> <s:textfield name="usersDetail.registerNum"></s:textfield></td>
				</tr>
				 
				<tr>
					<td align="right">是否全程服务亚运会:</td>
					<td align="left"><s:textfield name="usersDetail.isServeAgoc"></s:textfield></td>
					<td align="right">是否全程服务亚餐运会:</td>
					<td align="left"><s:textfield name="usersDetail.isServeAgocPara"></s:textfield></td>
				</tr>
				  
				<tr>
					<td colspan="2" align="center"><s:submit icon="icon-apply" value="保存"/></td>
					<td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"/></td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
