<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
</head>

<body>
<s:form>
	<table width="670" border="1" align="center" cellspacing="3" class="form_tb">
    <caption>志愿者注册</caption>
  
  <tr>
    <td width="219" align="right">中文姓:</td>
    <td width="131" align="left"><s:textfield name="user.firstnameCn"></s:textfield>*</td>
     <td width="207" align="right">中文名:</td>
    <td width="232" align="left"><s:textfield name="user.lastnameCn"></s:textfield>*</td>
  </tr>
  
  <tr>
    <td align="right">英文姓:</td>
    <td align="left"><s:textfield name="user.firstnameEn"></s:textfield>*</td>
    <td align="right">英文名:</td>
    <td align="left"><s:textfield name="user.lastnameEn"></s:textfield>*</td>
  </tr>
  
  <tr>
    <td align="right">出生国家 / 地区:</td>
    <td align="left"><s:select label="请选择" list="" name="user.birthCountry"></s:select>*</td>
    <td align="right">证件所属国家 / 地区:</td>
    <td align="left"><s:select label="请选择" list="" name="user.idcardCountry"></s:select>*</td>
  </tr>
  
  <tr>
   <td align="right">出生日期:</td>
    <td align="left"><s:textfield name="user.birthday"></s:textfield>*</td>
    <td align="right">电子邮箱地址:</td>
    <td align="left"><s:textfield name="user.email"></s:textfield>*</td>
  </tr>
  
  <tr>
    <td align="right">电子邮件地址:</td>
    <td align="left"><s:textfield name="user.email"></s:textfield>*</td>
     <td align="right"> 民族:</td>
    <td align="left"><s:select label="请选择" list="" name="user.ethnicity"></s:select>*</td>
  </tr>
  
  <tr>
    <td align="right">曾用姓:</td>
    <td align="left"><s:textfield name="user.firstnameFormer"></s:textfield></td>
    <td align="right">曾用名:</td>
    <td><s:textfield name="user.lastnameFormer"></s:textfield></td>
  </tr>
  
  <tr>
    <td align="right">身高:</td>
    <td align="left"><s:textfield name="user.height"></s:textfield>*</td>
    <td align="right">体重:</td>
    <td align="left"><s:textfield name="user.weight"></s:textfield>*</td>
  </tr>
  
  <tr>
    <td align="right"> 鞋码:</td>
    <td align="left"><s:textfield name="user.shoes"></s:textfield></td>
    <td align="right">身体状况:</td>
    <td align="left"><s:textfield name="user.health"/></td>
  </tr>
  
  <tr>
    <td align="right">保险受益人:</td>
    <td align="left"><s:textfield name="user.beneficiary"></s:textfield>*</td>
    <td align="right">与您的关系:</td>
    <td align="left"><s:textfield name="user.beneficiaryPerson"></s:textfield>*</td> 
  </tr>
  
  <tr>
    <td colspan="4"> <center>户籍所在地:</center></td>
  </tr>
  
  <tr>
    <td align="right">国家 / 地区:</td>
    <td align="left"><s:select label="请选择" list="" name="user.censusCountry"></s:select>*</td>
    <td align="right">省/自治区 / 直辖市:</td>
   <td align="left"><s:select label="请选择" list="" name="user.censusProvince"></s:select>*</td>
  </tr>
  
  <tr> 
    <td align="right">市 / 县:</td>
    <td align="left"><s:select label="请选择" list="" name="user.censusCity"></s:select>*</td>
    <td align="right">详细地址:</td>
    <td align="left"><s:textfield name="user.censusAddress"></s:textfield></td>
  </tr>
  
  <tr>
    <td colspan="4"><center>身份证明:</center></td>
  </tr>
  
  <tr>
    <td align="right"><center>
      证件类别:
    </center></td>
    <td colspan="3"><s:radio list="#{'user.zjlx':'护照（适用于外籍申请人及长期旅居国外无中国居民身份证的中国内地申请人）。护照有效期至少到2010年12月31日。<br>','zjlx':'中国居民身份证（适用于中国内地申请人）<br>','user.zjlx':'中国军人证件（适用于中国内地申请人）<br>,'user.zjlx':'中国武警证件（适用于中国内地申请人）<br>,'zjlx':'台湾同胞来往大陆通行证（适用于中华台北申请人）<br>,'user.zjlx':'港澳居民来往内地通行证（适用于中国香港和澳门特别行政区申请人）<br>'}"></s:radio></td>
  </tr>
  
  <tr>
    <td align="right">证件号码:</td>
    <td colspan="3" align="left"><s:textfield name="user.idcardNumber"></s:textfield>*（使用英文输入法）</td>
  </tr>
  
  <tr>
    <td align="right">证件签发机构:</td>
    <td colspan="3" align="left"><s:textfield name="user.idcardOffice"></s:textfield></td>
  </tr>
  
  <tr>
    <td align="right">证件有效期截止日期:</td>
    <td colspan="3"><s:textfield name="user.idcardExpires"></s:textfield>*注意:对于身份证的有效期是永久的中国内地申请人，其身份证的失效期应统一填写为2030年12月31日 </td>
  </tr>
 <tr>
    <td colspan="4"><center>学校证明:</center></td>
  </tr>
  
  <tr>
    <td align="right">学校:</td>
    <td align="left"><s:textfield name="user.school"/></td>
     <td align="right">哪个校区:</td>
    <td align="left"><s:textfield name="user.schoolCampus"></s:textfield>*</td>
  </tr>
  
  <tr>
  	<td align="right">校区所在的区(市):</td>
    <td align="left"><s:textfield name="user.schoolCampusArea"/></td>
    <td align="right">院系:</td>
    <td><s:textfield name="user.department"/></td>
  </tr>
  
   <tr>
  	<td align="right">专业:</td>
    <td align="left"><s:textfield name="user.major"/></td>
    <td align="right">班级:</td>
    <td><s:textfield name="user.clazz"/></td>
  </tr>
  
  <tr>
  	<td align="right">在读学历:</td>
    <td align="left"><s:textfield name="user.degree"/></td>
    <td align="right">学校详细地址:</td>
    <td><s:textfield name="user.schoolAddress"/></td>
  </tr>
  
  <tr>
  	<td align="right">入学时间:</td>
    <td align="left"><s:textfield name="user.entranceDate"/></td>
    <td align="right">预计毕业时间:</td>
    <td><s:textfield name="user.graduationDate"/></td>
  </tr>
  <tr>
    <td colspan="4"><center>现在居住地址:</center></td>
  </tr>
  
  <tr>
    <td align="right">国家 / 地区:</td>
    <td align="left"><s:select label="请选择" list="" name="user.country"></s:select>*</td>
    <td align="right">省/自治区 / 直辖市:</td>
    <td align="left"><s:select label="请选择" list="" name="user.province"></s:select>*</td>
  </tr>
  
  <tr>
    <td align="right">市 / 县:</td>
    <td align="left"><s:select label="请选择" list="" name="user.city"></s:select>*</td>
    <td align="right">所在区:</td>
    <td align="left"><s:select label="请选择" list="" name="user.area"></s:select>*</td>
  </tr>
  
  <tr>
    <td align="right">详细地址:</td>
    <td align="left"><s:textfield name="user.address"></s:textfield>*</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td colspan="4"><center>实际情况:</center></td>
  </tr>
  <tr>
    <td align="right">实际工作单位/就读学校名称: </td>
    <td align="left"><s:textfield name="user.workingCompany"></s:textfield>*</td>
    <td align="right">工作单位/就读学校详细地址:</td>
    <td align="left"><s:textfield name="user.workingAdress"></s:textfield>*</td>
  </tr>
  
  <tr>
    <td align="right">目前的职业:</td>
    <td align="left"><s:textfield name="user.job"></s:textfield>*</td>
    <td align="right">申请人移动电话:</td>
    <td align="left"><s:textfield name="user.mobile"></s:textfield>*</td>
  </tr>
  
  <tr>
    <td align="right">申请人固定电话:</td>
    <td align="left"><s:textfield name="user.telephone"></s:textfield>*</td>
    <td align="right">其他联系电话:</td>
    <td align="left"><s:textfield name="user.contaceName"></s:textfield>*</td>
  </tr>
  
  <tr>
      <td align="right">其他联系移动电话:</td>
    <td colspan="3" align="left"><s:textfield name="user.contaceMobile"></s:textfield>*</td>
  </tr>
  
	<tr>
    <td colspan="4"><center>语言技能情况:</center></td>
  </tr>
  
  <tr>
  	<td align="right">语言</td>
    <td align="left"><s:select label="请选择" list="" name="user.laguage"></s:select></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
   <tr>
  	<td align="right">外语言一:</td>
    <td align="left"><s:textfield name="user.foreignLaguage1"></s:textfield></td>
    <td align="right">外语一水平:</td>
    <td><s:textfield name="user.foreignLaguageLevel1"></s:textfield></td>
  </tr>
  
  <tr>
  	<td align="right">外语言二:</td>
    <td align="left"><s:textfield name="user.foreignLaguage2"></s:textfield></td>
    <td align="right">外语一水平:</td>
    <td><s:textfield name="user.foreignLaguageLevel2"></s:textfield></td>
  </tr>
  
  <tr>
    <td colspan="4"><center>来源</center></td>
  </tr>
  <tr>
  	<td align="right">来源单位:</td>
    <td align="left"><s:textfield name="user.sourceCompany"></s:textfield></td>
    <td align="right">亚组委招募部门:</td>
    <td><s:textfield name="user.agocCompany"></s:textfield></td>
  </tr>
  
  <tr>
  	<td align="right">志愿者身份:</td>
    <td align="left"><s:textfield name="user.userIdentity"></s:textfield></td>
    <td align="right">职位:</td>
    <td><s:textfield name="user.agocPosition"></s:textfield></td>
  </tr>
  
   <tr>
  	<td align="right">场馆:</td>
    <td align="left"><s:textfield name="user.venue"></s:textfield></td>
    <td align="right">业务口:</td>
    <td><s:textfield name="user.functionalArea"></s:textfield></td>
  </tr>
  
     <tr>
       <td align="right">是否上传照片:</td>
       <td  align="left">
            <s:textfield name="user.isUploadedPhoto"></s:textfield></td>
            <td align="right">注册编码:</td>
       <td align="left"> <s:textfield name="user.registerNum"></s:textfield></td>
    </tr>
    
   <tr>
  	<td align="right">是否全程服务亚运会:</td>
    <td align="left"><s:textfield name="user.isServeAgoc"></s:textfield></td>
    <td align="right">是否全程服务亚餐运会:</td>
    <td><s:textfield name="user.isServeAgocPara"></s:textfield></td>
  </tr>
  
	<tr>
  	<td colspan="2" align="center"><s:submit value="保存"/></td>
    <td colspan="2" align="center"><s:reset value="重设"/></td>
  </tr
  
  ></table>
</s:form>
</body>
</html>
