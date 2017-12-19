/******************************************************************************
Filename       : www.91ka.com/jsciprt/game_prod_seach.js
Author         : WEICHEN
Email          : chenjiawei2000@163.com
Date/time      : 
Purpose        : 
Mantis ID      : 
Description    : 网游卡密直充搜索javascript
Revisions      : 
Modify         : 
Inspect        :
******************************************************************************/
//创建本页的ajax对象 start
function createXMLHttpRequest(){
     var xmlhttp;
		 try{
			 xmlhttp = new XMLHttpRequest();        
		 }catch(e){
		  try{
		    xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
			}catch(e){
			  try{
			    xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');         
				}catch(e){
				  alert("创建XMLHttpRequest对象失败?");
				}
			}
		 }
		 return xmlhttp;
}
//创建本页的ajax对象 end

//搜索框获得焦点时的判断 start
function checkValue(){
   var obj_qWord = document.getElementById("qWord");
   if(obj_qWord.value.trim() == "请输入搜索关键词"){
      obj_qWord.value = '';
   }
   return;
} 
//搜索框获得焦点时的判断 end

//当搜索框的输入有变动时提示搜索的产品 start
var lis;                                                 //lis
var index=0;                                             //index
var status=0;                                            //http状态码
function changeValue(value,list_type){
  if(value == ""){
    document.getElementById('sug').style.display = 'none';
		return false;
  }
  document.getElementById('sug').style.display = 'block';  
  var xmlhttp = createXMLHttpRequest(); //创建 xmlHttpRequest对象
  if(list_type == "all_game"){
    var url = "game_card_online_prodSearch.php?list_type=all_game&qWord="+value;
  }else{
    var url = "game_card_online_prodSearch.php?list_type=all_game&qWord="+value;
  }
  //var url = "game_card_online_prodSearch.php?qWord="+value; 
  xmlhttp.open('GET',url,true);                           	
  xmlhttp.onreadystatechange = function (){
	  if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
	    document.getElementById('sug').innerHTML=xmlhttp.responseText;  
		  lis = document.getElementsByTagName('ol');  //获得Li的标签ID
			index=0;                                   
			status=0;       	 
		}
  } ;
  xmlhttp.setRequestHeader("cache-control","no-cache"); 
  xmlhttp.send(null);                                   	                                                       
}
//当搜索框的输入有变动时提示搜索的产品 end



function focus_mouse(){
  if(lis.length > 0 && lis && index > 0){
    lis[index].style.background='#ffffff';
  }
}



//按下方向键盘时所作的动作 start
var keyup_down = false;//判断是否是按过上下方向箭头	
function keydown(e){
  var keytype;
  var pay_type;
  var currKey = 0;
  if(navigator.appName == "Microsoft Internet Explorer"){
    currKey = event.keyCode;   
　}else{
    currKey = e.which;   
  }
  if(lis && lis.length >0){
    if(currKey == 38 || currKey == 40 ){
      for(i = 0;i<lis.length;i++){
        lis[i].style.background='#ffffff';
      }
    }
    
    
    if(currKey == 38){// 如果是向上方向键
		  keyup_down = true; 
			if(index >0){// 如果lis里面的数据条数不为空
			  lis[index-1].style.background='#0888D1'; 
	    }
			lis[index].style.background='#ffffff'; 
			if(index > 0)      
			index--;
			if(index == 0){
			  index = lis.length - 1;
			}        
		}
		if(currKey == 40){
		  keyup_down = true;
			//向下;       
			if(index < lis.length-1){  //判断是否到列表的尾部
			 index++;
			}                
			if(status == 0){
				 index=0;               
			 }
			 lis[index].style.background='#0888D1';
			 if(index >0){
				 lis[index-1].style.background='#ffffff';
			 }
			 status=1;      //设置状态
		} 
	 }
	 if(lis && lis.length >0 && index >=0 && !keyup_down && currKey == 13){
	  var obj_qWord = document.getElementById("qWord");
    var str_qWord = obj_qWord.value.trim();
		Search_prod();      
	 }else if(lis && lis.length >0 && index >=0 && keyup_down && currKey == 13){
	  triem(lis[index].title,lis[index].innerHTML,pay_type);
	 }   
} 

var time;  
function triem(key_value,cate_name,list_type){
	if(cate_name != ""){
	  document.getElementById('qWord').value = cate_name;
	}
	document.getElementById('sug').style.display = 'none'; 
	clearTimeout(time);
	if(list_type == "all_prod"){
	  window.location.href = "game_prodList.php?cate_name="+cate_name+"&cat="+key_value;
	}else{
	  window.location.href = "game_prodList.php?cate_name="+cate_name+"&cat="+key_value;
	}
	
}

//搜索按钮所用到的函数
function Search_prod(){
	var obj_qWord = document.getElementById("qWord");
  var str_qWord = obj_qWord.value.trim();
	if(str_qWord == "" || str_qWord =="请输入搜索关键词"){
		alert("请输入搜索关键词");
		obj_qWord.focus();
        return;
	}else{
	  window.location.href = "game_my_prod_list.php?qWord="+str_qWord;	
  }
}



//搜索最近两个月调整价格的产品
function Search_prod_changprice(){
	var obj_qWord = document.getElementById("qWord");
  var str_qWord = obj_qWord.value.trim();
	if(str_qWord == "" || str_qWord =="请输入搜索关键词"){
		alert("请输入搜索关键词");
		obj_qWord.focus();
        return;
	}else{
	  window.location.href = "game_prodList_change_price.php?qWord="+str_qWord;	
  }
}



//搜索最近两个月调整价格的产品
function Search_prod_newaddprod(){
	var obj_qWord = document.getElementById("qWord");
  var str_qWord = obj_qWord.value.trim();
	if(str_qWord == "" || str_qWord =="请输入搜索关键词"){
		alert("请输入搜索关键词");
		obj_qWord.focus();
        return;
	}else{
	  window.location.href = "game_prodList_newaddprod.php?qWord="+str_qWord;	
  }
}


//按下方向键盘时所作的动作 end

//当搜索框的输入有变动时提示搜索的产品 end







/*
function chkQSearchForm(){
	var qWord = qSearchForm.qWord.value.trim();
	if(qWord == "" || qWord =="快速搜索"){
		alert("请输入搜索关键词");
		qSearchForm.qWord.focus();
		return false;
	}
	return true;
}


function search_prod(){
	var obj_qWord = document.getElementById("qWord");
    var str_qWord = obj_qWord.value.trim();
	if(str_qWord == "" || str_qWord =="请输入搜索关键词"){
		alert("请输入搜索关键词");
		obj_qWord.focus();
        return;
	}else{
        window.location.href = "prodSearch.php?qWord="+str_qWord;	
    }
}

function buy_bat(id){
	if(id == '') return;
	window.location.href = 'buy.php?type=bat&id='+id;
}

function checkValue(){
   var obj_qWord = document.getElementById("qWord");
   if(obj_qWord.value.trim() == "请输入搜索关键词"){
      obj_qWord.value = '';
   }
   return;
} 

//m1
function batSearch_prod(){
	var obj_qWord = document.getElementById("qWord");
  var str_qWord = obj_qWord.value.trim();
	
	if(str_qWord == "" || str_qWord =="请输入搜索关键词"){
		 alert("请输入搜索关键词");
		 obj_qWord.focus();
     return;
	}else{
     window.location.href = "prodBatSearch.php?qWord="+str_qWord;	
  }
}


//m2
function search_prod_szx(){
	var obj_qWord = document.getElementById("qWord");
  var str_qWord = obj_qWord.value.trim();
	
	if(str_qWord == "" || str_qWord =="请输入搜索关键词"){
		 alert("请输入搜索关键词");
		 obj_qWord.value = '';
		 obj_qWord.focus();
     return;
	}else{
     window.location.href = "prodSzxSearch.php?qWord="+str_qWord;	
  }
}

//m2
function search_prod_online(){
	var obj_qWord = document.getElementById("qWord");
  var str_qWord = obj_qWord.value.trim();
	
	if(str_qWord == "" || str_qWord =="请输入搜索关键词"){
		 alert("请输入搜索关键词");
		 obj_qWord.value = '';
		 obj_qWord.focus();
     return;
	}else{
     window.location.href = "prodSearch_oline.php?qWord="+str_qWord;	
  }
}


//m3
/*搜索提示开始*/
/*
function createXMLHttpRequest(){
     var xmlhttp;
		 try{
			 xmlhttp = new XMLHttpRequest();        
		 }catch(e){
		  try{
		    xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
			}catch(e){
			  try{
			    xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');         
				}catch(e){
				  alert("创建XMLHttpRequest对象失败?");
				}
			}
		 }
		 return xmlhttp;
}
var lis;                                                 //lis
var index=0;                                             //index
var status=0;                                            //http状态码

//type: online是直储搜索，szx是神州行搜索，batlist是批量搜索
function change(value,type){
  if(value == ""){
    document.getElementById('sug').style.display = 'none';
		return false;
  }
  document.getElementById('sug').style.display = 'block';  
  var xmlhttp = createXMLHttpRequest(); //创建 xmlHttpRequest对象
  if(type == "online"){
    var url = "prodSearch_oline.php?Type=div&qWord="+value;
  }else if(type == "szx"){
    var url = "prodSzxSearch.php?Type=div&qWord="+value;
  }else if(type == 'batlist'){
    var url = "prodBatSearch.php?Type=div&qWord="+value;
  }else{
    var url = "prodSearch.php?Type=div&qWord="+value;
  }
  xmlhttp.open('GET',url,true);                           	
  xmlhttp.onreadystatechange = function (){
	  if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
	    document.getElementById('sug').innerHTML=xmlhttp.responseText;  
		  lis = document.getElementsByTagName('li');  //获得Li的标签ID
			index=0;                                   
			status=0;       	 
		}
  } ;
  xmlhttp.setRequestHeader("cache-control","no-cache"); 
  xmlhttp.send(null);                                   	                                                       
}
var time;                                                      //定义时间变量 
//type: online是直储搜索，szx是神州行搜索，batlist是批量搜索
function triem(key_value,pay_type){
	if(key_value != ""){
	  document.getElementById('qWord').value = key_value;
	}
	document.getElementById('sug').style.display = 'none'; 
	clearTimeout(time);
	if(pay_type.trim() == "online"){
	  window.location.href = "prodSearch_oline.php?qWord="+key_value;
	}else if(pay_type.trim() == "szx"){
	  window.location.href = "prodSzxSearch.php?qWord="+key_value;
	}else if(pay_type.trim() == 'batlist'){
	  window.location.href = "prodBatSearch.php?qWord="+key_value;
	}else{
	  window.location.href = "prodSearch.php?qWord="+key_value;
	}
}
var keyup_down = false;         //判断是否是按过上下方向箭头	
function keydown(e,pay_type){
  var keytype;
  var pay_type;
  var currKey = 0;
  if(navigator.appName == "Microsoft Internet Explorer"){
    currKey = event.keyCode;   
　}else{
    currKey = e.which;   
  }
  if(lis && lis.length >0){
    if(currKey == 38){// 如果是向上方向键
		  keyup_down = true; 
			if(index >0){// 如果lis里面的数据条数不为空
			  lis[index-1].style.background='#d7ebff'; 
	    }
			lis[index].style.background='#ffffff'; 
			if(index > 0)      
			index--;        
		}
		if(currKey == 40){
		  keyup_down = true;
			//向下;       
			if(index < lis.length-1){  //判断是否到列表的尾部
			 index++;
			}                
			if(status == 0){
				 index=0;               
			 }
			 lis[index].style.background='#d7ebff';
			 if(index >0){
				 lis[index-1].style.background='#ffffff';
			 }
			 status=1;      //设置状态
		} 
	 }
	 if(lis && lis.length >0 && index >=0 && !keyup_down && currKey == 13){
	  var obj_qWord = document.getElementById("qWord");
    var str_qWord = obj_qWord.value.trim();
		triem(str_qWord,pay_type);      
	 }else if(lis && lis.length >0 && index >=0 && keyup_down && currKey == 13){
	  triem(lis[index].innerHTML,pay_type);
	 }   
} 
*/
/*搜索提示结束*/