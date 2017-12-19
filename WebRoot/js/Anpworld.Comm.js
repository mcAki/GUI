//Ajax Request object
function CreateXmlHttp() {
    var request;
    var browser = navigator.appName;
    var arrVersions = ["Microsoft.XMLHttp", "MSXML2.XMLHttp.4.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp", "MSXML2.XMLHttp.5.0"];
    if (browser == "Microsoft Internet Explorer") {
        for (var i = 0; i < arrVersions.length; i++) {
            try {
                request = new ActiveXObject(arrVersions[i]);
                return request;
            }
            catch (exception) {
            }
        }
    }
    else {
        request = new XMLHttpRequest();
        return request;
    }
}
//Anpworld Ajax Method
function Anpworld() { }
Anpworld.GetRootPath = function() {
    var n = String(window.document.location);
    return n.substring(0, n.indexOf("/", 8)); // "http://"有7个字符
}
Anpworld.GetRootPath2 = function() {
    //var n = String(window.document.location);
    return "${pageContext.request.contextPath}";//n.substring(0, n.indexOf("/", 8)); // "http://"有7个字符
}
Anpworld.AjaxCall = function(url, uri, callbackfun, errfun) {
    var request = CreateXmlHttp();
    if (request) {
        request.onreadystatechange = function() {
            if (request.readyState == 4) {
                if (request.status == 200) {
                    if (callbackfun)
                        callbackfun(request.responseText);
                } else {
                    if (errfun)
                        errfun();
                }
            }
        }
        var reqUrl = Anpworld.GetRootPath() + url + "?" + uri;
        request.open("Post", reqUrl, true);
        request.send(null);
    }
    else {
        if (errfun)
            errfun();
    }
    return request;
}
Anpworld.AjaxCall2 = function (url, data, callbackfun, errfun) {
    var request = CreateXmlHttp();
    if (request) {
        request.onreadystatechange = function () {
            if (request.readyState == 4) {
                if (request.status == 200) {
                    if (callbackfun)
                        callbackfun(request.responseText);
                } else {
                    if (errfun)
                        errfun();
                }
            }
        }
        var reqUrl = Anpworld.GetRootPath() + url;
        request.open("Post", reqUrl, true);
        request.send(data);
    }
    else {
        if (errfun)
            errfun();
    }
    return request;
}
Anpworld.AjaxCall3 = function(url, uri, callbackfun, errfun) {
    var request = CreateXmlHttp();
    if (request) {
        request.onreadystatechange = function() {
            if (request.readyState == 4) {
                if (request.status == 200) {
                    if (callbackfun)
                        callbackfun(request.responseText);
                } else {
                    if (errfun)
                        errfun();
                }
            }
        }
        var reqUrl = Anpworld.GetRootPath2() + url + "?" + uri;
        request.open("Post", reqUrl, true);
        request.send(null);
    }
    else {
        if (errfun)
            errfun();
    }
    return request;
}
