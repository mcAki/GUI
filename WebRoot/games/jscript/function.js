/******************************************************************************
Filename       : www.91ka.com/function.js
Author         : weichen
Email          : chengw@untx.com
Date/time      : 2010-3-16 
Purpose        : cp后台管理所用到的相关JS脚本
Mantis ID      : 
Description    : 
Revisions      : 
Modify         : 
Inspect        :
******************************************************************************/

//测试字符串是否符合正则表达式reg
String.prototype.regCheck = function(reg){
	return reg.test(this);
}


String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function checkLoginForm(){

		var eleUser = document.getElementById("cpname");
		var user = eleUser.value.trim();
		if(user == ""){
			alert("请输入您的用户名！");
			eleUser.focus();
			return false;
		}
		if(!(/^[0-9a-z_-]{4,20}$/.test(user))){
			alert("用户名中含有不允许的字符，请检查。");
			eleUser.focus();
			return false;
		}
		var elePwd = document.getElementById("cppwd");
		var pwd = elePwd.value.trim();
		if(pwd == ""){
			alert("请输入您的登录密码！");
			elePwd.focus();
			return false;
		}
		var eleVcode = document.getElementById('vcode');
		var vcode = eleVcode.value.trim();
		if(vcode.length < 4){
			alert("请输入您的验证码!");
			eleVcode.focus();
			return false;
		}
		return true;
}

//游戏卡密直充产品列表按A-D,E-F检索 start
function onGameMouse_click(obj_over,css_type){
  obj_over.className = css_type;
}

//修改密码的表单检查
function check_Chgpwd_form(){	
	var oldpwd = document.getElementById("oldpwd");
	var newpwd = document.getElementById("newpwd");
	var rtpwd  = document.getElementById("rtpwd");

	if(oldpwd.value.length < 5){
		alert("原密码输入错误，密码是五位以上，请输入您的原来密码。");
		oldpwd.focus();
		return false;
	}
	if(newpwd.value.length < 5){
		alert("必须输入五位以上的新密码！");
		newpwd.focus();
		return false;
	}
	
	if(newpwd.value != rtpwd.value){
		alert("新密码和确认密码不一致。");
		rtpwd.focus();
		return false;
	}
	if(newpwd.value != "" && rtpwd.value != ""){
		if(newpwd.value.length < 5){
			alert("密码必须在5位以上。");
			newpwd.focus();
			return false;
		}else if(newpwd.value.length > 20){
			alert("新密码太长，不能超过20个英文字符。");
			newpwd.focus();
			return false;
		}
	}

	if(oldpwd.value == newpwd.value){
	  
	  alert("新密码和旧密码不能相同");
	  newpwd.value = "";
	  rtpwd.value  = "";
	  newpwd.focus();
		return false;
	}
	if(newpwd.value == '' && rtpwd.value == ''){
		alert("必须要修改密码才能提交。");
		return false;
	}
	return true;
	
}