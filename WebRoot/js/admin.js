function checkform(){
    var flag = false;
    if (!myform.chk.length) {
        if (myform.chk.checked == true) {
            flag = true;
        }
    }
    else {
        for (var i = 0; i < myform.chk.length; i++) {
            if (myform.chk[i].checked == true) {
                flag = true;
            }
        }
    }
    if (!flag) {
        alert("请选择一项删除!");
        return false;
    }
    else {
        if (confirm("是否确定删除!")) {
            event.returnValue = true;
        }
        else {
            event.returnValue = false;
        }
    }
}

function checkselect(formName){
    var flag = false;
    if (!formName.chk.length) {
        if (formName.chk.checked == true) {
            flag = true;
        }
    }
    else {
        for (var i = 0; i < formName.chk.length; i++) {
            if (formName.chk[i].checked == true) {
                flag = true;
            }
        }
    }
    if (!flag) {
        alert("请至少选择一项!");
        return false;
    }
    else {
       return true;
    }
}

function checkAll(e, itemName, isall){
    var aa = document.getElementsByName(itemName);
    for (var i = 0; i < aa.length; i++) {
        if (isall == "yes") {
            if (aa[i].checked) {
                aa[i].checked = !e.checked;
            }
            else {
                aa[i].checked = e.checked;
            }
        }
        else {
            aa[i].checked = e.checked;
        }
    }
}


function checkopt()
{
    var flag = false;
    var opt = document.getElementById("opt");
    if (!opt.length) {
        if (opt.options[i] == true) {
            flag = true;
        }
    }
    else {
        for (var i = 0; i < opt.length; i++) {
            if (opt.options[i].selected) {
                flag = true;
            }
        }
    }
    if (!flag) {
        alert("请选择一项!");
        return false;
    }
    else {
        return true;
    }
}