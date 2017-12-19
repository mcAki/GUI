/******************************************************************************
Filename       : cp.91ka.com/jscript/showDesc.js
Author         : SouthBear
Email          : SouthBear819@163.com
Date/time      : 2008-10-30 11:14:15
Purpose        : 动态显示当前商品的商品描述
Mantis ID      : 
Description    : 
Revisions      : 
Modify         : M1 WEICHEN 2009-08-04 修改产品详细信息的提示方式
Inspect        :
******************************************************************************/
//m1
function showdescdiv(prod_id)
{
	var div_style = document.getElementById(prod_id);
	if(div_style.style.display=='block')
	{
		
        div_style.style.display='none';
    } 
    else
    {   
        div_style.style.display='block';
    }		 
}

function hidedescdiv(prod_id)
{	
	var div_style = document.getElementById(prod_id);
	if(div_style.style.display != 'none')
	{
		div_style.style.display = 'none';
	}
	else
	{
		div_style.style.display = 'block';
	}	
}



window._stopMouseOver = function(e){
	(window.event||e).cancelBubble=true;
}


function showDesc(event,prod_id){

	_stopMouseOver(event);
  var prod_id; 
  var company_info_layer;
  var uri;
  var strResultcheck_str = "loading...";
  
  if(prod_id == ''){
     alert('商品编号不能为空');
  }else{    
     var uri = "getProdDetail.php?prod_id="+prod_id;
     ajax.abort();
     ajax.open("GET", uri, true);
     ajax.onreadystatechange = process;
     ajax.send(null);
     
     company_info_layer = document.getElementById('company_info_layer');
     var wbt = event.srcElement || event.target;
     
	 	 company_info_layer.style.left = wbt.offsetLeft+realOffset(wbt).x+5+"px";
	 	 company_info_layer.style.top  = realOffset(wbt).y + wbt.offsetHeight-2+"px";
	 	 company_info_layer.style.visibility = "visible";       


  }
}

	function realOffset(o){
	  var x = y = 0; do{
	  x += o.offsetLeft || 0; 
	  y += o.offsetTop  || 0;
	  o  = o.offsetParent;}while(o);
	  return {"x" : x, "y" : y};
	};

  
  function process(){  
    if(ajax.readyState == 4 && ajax.status == 200){
       var resText = ajax.responseText;
       //alert(resText);	   
       /*if(preg_match(/Error:/, resText)){
          resText = resText.replace('Error:', '');
          alert(resText);
       }else{
					strResultcheck_str = unescape(resText); 
					var netbar_str = strResultcheck_str;
					
					$('div_company_name').innerHTML = netbar_str;

             }*/
	   //m1
	   strResultcheck_str = unescape(resText); 
	   var netbar_str = strResultcheck_str;			
	   document.getElementById('div_company_name').innerHTML = netbar_str;
    }
  }
  

	function hiddenDEesc(){
		 var company_info_layer;
		 
		 company_info_layer = document.getElementById('company_info_layer');
	   company_info_layer.style.visibility = "hidden";
	} 

//m1
 /* function preg_match(regStr, str){
      var objRegExp = new RegExp(regStr);
      return objRegExp.test(str);
  } */
  
  