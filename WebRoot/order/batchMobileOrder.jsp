<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<style  type="text/css">
	.form_tb {
		height: 60px;
		line-height: 20px;
		font-size: 20px;
		font-weight: normal;
		color: #333;
		background-color: #FFF;
		text-align: center;
		border: double 4px #446633;
		border-collapse: collapse;
	}
	/*==文本框====================*/
	.z-txt{
		color:#334433;
		/*margin:+1px +1px 0px +1px;*/
		border-left:dotted 1px #669966;
		border-right:dotted 1px #669966;
		border-top:dotted 1px #669966;
		border-bottom:solid 1px #006600;
		height:30px;
		line-height:30px;
		padding:1px 6px;
		background-color:#FAFFFA;
	}
	</style>
	<script type="text/javascript">
		function showSupplyInterface(){
		
			//计算弹出位置
			
			height = 500;
			width = 400;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/supplyInterface/list!listInterfaceForSupply.do?";
			//为了处理缓存加入随机时间
			url += "&date=" + new Date().getTime();
			
			//弹出参数
			var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth="+ width +"px;dialogHeight=" + height + "px";
			
			//创建传入参数
			var obj = new Object();
			obj.name = "参数";
			var returnVal = window.showModalDialog(url,obj,"dialogWidth=600px;dialogHeight=500px");
			if(!$.isEmptyObject(returnVal)){
				$("#userId").val(returnVal.uid);
				$("#userName").val(returnVal.username);
			}
		}
		
		function enterChange(){
			var objId = event.srcElement.id;
			//alert(objId);
			if (window.event.keyCode==13) {
				if(objId==12){
					//alert((objId)+"submit");
					strutsForm.submit();
				}else{
					//alert((objId+1)+"onfocus");
					var id = Number(objId);
					//alert(id+1);
					document.getElementById(id+1).focus();
				}
			}
			
		}
		
		function showValue(){
			var val = document.getElementById("10");
			for(i=0;i <document.strutsForm.price.length;i++) { 
				if(document.strutsForm.price[i].checked){
					val.value = document.strutsForm.price[i].value;
					document.getElementById(11).focus();
					return;
				}
			} 
		}
		
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:actionerror/>
  	<s:form name="strutsForm" id="strutsForm" action="doBatchMobileOrderEx" namespace="/order" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:hidden name="mainorder.mainOrderId" />
  	<s:hidden name="orderdetail.orderDetailId" />
  	<s:token/>
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="900" border="0" align="center" cellspacing="4" class="form_tb">
    <caption>批量充值订单</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>   
      	<td align="right" >请填写手机:</td>
	 	<td align="left"><s:textarea name="mobile" cols="30" rows="3" id="11" cssStyle="font-size:30px;"/></td>
	 	<td>格式:手机号+空格+金额.例"13800000000 30"</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td></td>
      </tr>

      <tr>
      	<td align="right" >输入密码:</td>
	 	<td align="left"><s:password  name="businessPassword" id="12" onkeydown="enterChange()" cssStyle="font-size:30px;"/></td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
	 	<td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="充值"></s:submit>
        </td>
  	    <td colspan="2" align="center">&nbsp;<s:reset icon="icon-reload" value="重设"></s:reset></td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
  <table width="900" border="0" align="center" cellspacing="1">
    <center>
              温馨提示:每输完一次手机号码请按回车键再输入下一个手机号码(批量充值)
    </center>
  </table>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>

