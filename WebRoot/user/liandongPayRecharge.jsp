<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
			<link href="../css/main_new.css" rel="stylesheet" type="text/css" />
				<%@ include file="../common/incHead.jsp"%>

				<script type="text/javascript">
	function showSupplyForLog() {

		//计算弹出位置

		height = 500;
		width = 400;
		//barHeight=30;
		//top = (screen.Height/2) - (height/2) - barHeight;
		//left = (screen.width/2) - (width/2);

		//要查询的链接
		var url = "${pageContext.request.contextPath}/supply/list!listSupplyForLog.do?";
		//为了处理缓存加入随机时间
		url += "&date=" + new Date().getTime();

		//弹出参数
		var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=yes;dialogWidth="
				+ width + "px;dialogHeight=" + height + "px";

		//创建传入参数
		var obj = new Object();
		obj.name = "参数";
		var returnVal = window.showModalDialog(url, obj,
				"dialogWidth=600px;dialogHeight=500px");
		if (!$.isEmptyObject(returnVal)) {
			$("#userId").val(returnVal.uid);
			$("#userName").val(returnVal.username);
		}
	}
</script>
	</head>
	<body>

		<%@ include file="../common/incBanner.jsp"%>
		
   <!-- 
  	<s:form name="strutsForm" action="payRecharge" namespace="/liandong" method="post" target="_blank">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>在线充值账户</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
      <tr>
       <td align="right">用&nbsp;户:</td>
  	   <td align="left"><label>${sessionScope.user.userName }</label></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
       <td align="right">填写金额:</td>
  	   <td align="left"><s:textfield name="payMoney"/></td>
       <td align="right">在线支付,现每100元需要收取${100-charge*100 }%手续费</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
       <tr>
       <td align="right">注&nbsp;意:</td>
  	   <td align="right">第一次使用在线充值方式加款，请先转帐0.01成功并确定额度到账后再转计划加款金额。</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td colspan="4" align="center"><s:submit icon="icon-apply" value="充值"></s:submit>
        </td>
        <td>&nbsp;</td>
      </tr>
		
  </table>
  </s:form>
   -->
		<%@ include file="../common/incFooter.jsp"%>

		 <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
               <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                    <strong class="tqxj">在线充值账户</strong>
                    <div class="update fixed">
                             <div class="upadeform" style="width: 550px;">
                                   	<s:form name="strutsForm" action="payRecharge" namespace="/liandong" method="post" >
									  	<s:hidden name="userId" />
									  		<center><s:fielderror></s:fielderror></center>
									       <p >用&nbsp;户:&nbsp;&nbsp; ${sessionScope.user.userName }</p>
									       <p >填写金额: <input type="text" name="payMoney" class="jmm" /> </p>
									       <p >在线支付,现每100元需要收取${100-charge*100 }%手续费</p>
									       <p >注&nbsp;意:   第一次使用在线充值方式加款，请先转帐0.01成功并确定额度到账后再转计划加款金额。</p>
									  	    <td colspan="4" align="center"><button type="submit" value="充值" class="chongzhi11"> </button>
									  </s:form>
                             </div>
                    </div>
             </div>
          </div>
      </div>
	</body>
</html>

