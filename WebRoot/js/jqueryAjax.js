function postJonRequest(jsonData, _url, errorMsg, successFun) {
	$.ajax( {
		type : "post",
		url : _url,
		data : jsonData,
		dataType : "json",
		timeout : 30000, // 超时设置 30秒
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (errorMsg == null || errorMsg == '') {
				alert("AJAX服务器端出错");
			} else {
				alert(errorMsg);
			}

		},
		success : successFun
	});
}

// function Object.prototype.clone(){
// var newObj = new Object();
// for(elements in this){
// newObj[elements] = this[elements];
// }
// return newObj;
// }
//
// function Object.prototype.cloneAll(){
// function clonePrototype(){}
// clonePrototype.prototype = this;
// var obj = new clonePrototype();
// for(var ele in obj){
// if(typeof(obj[ele])=="object") obj[ele] = obj[ele].cloneAll();
// }
// return obj;
// }

/**
 * 阻塞调用json
 */
function ezAjax(_url, jsonData, errorMsg, isAsync, successFun) {
	if (isAsync != false) {
		isAsync = true;
	}
	if (errorMsg == null || errorMsg == '') {
		errorMsg = 'AJAX服务器端出错';
	}

	$.ajax( {
		type : "post",
		url : _url,
		data : jsonData,
		async : isAsync,
		dataType : "json",
		timeout : 30000, // 超时设置 30秒
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorMsg);
		},
		success : successFun
	});
}

function ezAjaxGetStrings(_url, jsonData) {
	var myArray = new Array();

	errorMsg = 'AJAX服务器端出错';
	$.ajax( {
		type : "post",
		url : _url,
		data : jsonData,
		async : false,
		dataType : "json",
		timeout : 30000, // 超时设置 30秒
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorMsg);
		},
		success : function(msg) {
			var i = 0;
			for (ele in msg) {
				myArray[i] = eval("msg." + ele);
				i++;
			}
		}
	});

	return myArray;
}

function ezAjaxGetString(_url, jsonData) {
	var myArray = ezAjaxGetStrings(_url, jsonData);
	if (myArray[0] != null || myArray[0] != undefined) {
		return myArray[0];
	} else {
		return '';
	}
}
