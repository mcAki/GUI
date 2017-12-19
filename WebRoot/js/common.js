// JavaScript Document

//删除记录时候公共提示
function del(msg) {
	if (msg == '' || msg == undefined) {
		msg = '您真的确定要删除吗？\n\n请确认！';
	}
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}

// 建议确认公共提示
function conf(msg) {
	if (msg == '' || msg == undefined) {
		msg = '确定要执行此操作吗？\n\n请确认！';
	}
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}

// 延时
function sleep(n) 
{ 
	var start=new Date().getTime(); 
	while(true) 
		if(new Date().getTime()-start> n){
			break; 
		}
} 

/*
 * DreamWeaver的专用函数
 */
function MM_swapImgRestore() { // v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
/*
 * DreamWeaver的专用函数
 */
function MM_preloadImages() { // v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
/*
 * DreamWeaver的专用函数
 */
function MM_findObj(n, d) { // v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}
/*
 * DreamWeaver的专用函数
 */
function MM_swapImage() { // v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

//window.open('page.html','newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
//脚本运行后，page.html将在新窗体newwindow中打开，宽为100，高为400，距屏顶0象素，屏左0象素，无工具条，无菜单条，无滚动条，不可调整大小，无地址栏，无状态栏。请对照
function ezModal(url,width,height,paramsObj,isResizable,isScroll,isCenter,isStatus){
	// 为了处理缓存加入随机时间
	if(url.lastIndexOf('?')<0){
		url += '?';	
	}
	url += "&date=" + new Date().getTime();

	// 弹出参数
	var features = "alwaysRaised=yes,toolbar=no,menubar=no,";
	    
	if(width>0){
		features += "width="+ width +",";
	}else{
		features += "width=500,";
	}
	
	if(height>0){
		features += "height=" + height + ",";
	}else{
		features += "height=500,";
	}
	
	if(isResizable==true){
		features += 'resizable=yes,';		
	}else{
		features += 'resizable=no,';
	}

	if(isScroll!=false){
		features += 'scroll=yes,';		
	}else{
		features += 'scroll=no,';
	}

	if(isStatus==true){
		features += 'status=yes';			
	}else{
		features += 'status=no';
	}
	//return window.showModalDialog(url,paramsObj,features);	
	
	return window.open(url,paramsObj,features);
}

/**
 * 弹出窗口 url地址,宽,高,参数对象,可调大小(默认:关),滚动条(开),居中(开),状态栏(关)
 */
function ezModal2(url,width,height,paramsObj,isResizable,isScroll,isCenter,isStatus){

	
	// 为了处理缓存加入随机时间
	if(url.lastIndexOf('?')<0){
		url += '?';	
	}
	url += "&date=" + new Date().getTime();

	// 弹出参数
	var features = 'help=false;';
	
	if(width>0){
		features += "dialogWidth="+ width +"px;";
	}else{
		features += "dialogWidth=500px;";
	}
	
	if(height>0){
		features += "dialogHeight=" + height + "px;";
	}else{
		features += "dialogHeight=500px;";
	}
	
	if(isResizable==true){
		features += 'resizable=yes;'			
	}else{
		features += 'resizable=no;'
	}

	if(isScroll!=false){
		features += 'scroll=yes;'			
	}else{
		features += 'scroll=no;'
	}

	if(isCenter!=false){
		features += 'center=yes;'			
	}else{
		features += 'center=no;'
	}

	if(isStatus==true){
		features += 'status=yes;'			
	}else{
		features += 'status=no;'
	}

	// alert(features);
	
	// 创建传入参数
	// var obj = new Object();
	// obj.name = "参数";
	
	//return window.showModalDialog(url,paramsObj,features);	
	
	return window.open(url,paramsObj,features);
}

//显示
function displaytagExportLink(count){
	if(count==undefined){
		count=9999;
	}
	url=$(".exportlinks a").attr("href");
	url= url + "&pageSize=" + count;
	//alert(url);
	$(".exportlinks a").attr("href", url);
}

function highlightTableRows(tableId) {   
    var previousClass = null;   
    var table = document.getElementById(tableId);    
    var tbody = table.getElementsByTagName("tbody")[0];   
    if (tbody == null) {   
        var rows = table.getElementsByTagName("tr");   
    } else {   
        var rows = tbody.getElementsByTagName("tr");   
    }   
    for (i=0; i < rows.length; i++) {   
        rows[i].onmouseover = function() { 
        	this.cOrg2=this.style.backgroundColor;
        	this.style.backgroundColor="#FFAA88";
        };   
        rows[i].onmouseout = function() { 
        	this.style.backgroundColor=this.cOrg2;
        };
//        if(i % 2 == 0)
//            rows[i].style.backgroundColor="red";
//        if(i % 2 == 1)
//            rows[i].style.backgroundColor="yellow";   
//        rows[i].onclick = function() {   
//            var cell = this.getElementsByTagName("td")[0];
//            
//            if(this.isOrg==true){
//            	this.style.backgroundColor=this.cOrg;
//            	this.isOrg=false;
//            }else{
//            	this.cOrg=this.style.backgroundColor;
//            	this.style.backgroundColor="#FF6600";
//            	this.isOrg=true;
//            }
//        }   
    }   
} 