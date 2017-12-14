// JavaScript Document

function topage(pageIndex) {
	document.forms(0).action = "do!del.html?pageIndex=" + pageIndex;
	document.forms(0).submit();
}

//设置banner是上还是下
function sycnBanner(side){
	document.getElementById('idListBottom').style.width = 	document.getElementById('idListTb').style.width;
	document.getElementById('idPagebannerBottom').innerHTML = document.getElementById('idPagebanner').innerHTML;
	document.getElementById('idPagelinksBottom').innerHTML = document.getElementById('idPagelinks').innerHTML;
	if(side=='' || side=='bottom'){
		document.getElementById('idPagebanner').innerHTML = '';
		document.getElementById('idPagelinks').innerHTML = '';
	}if(side=='top'){
		document.getElementById('idPagebannerBottom').innerHTML = '';
		document.getElementById('idPagelinksBottom').innerHTML = '';	
	}
}