<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
				<%@ include file="../common/incHead.jsp"%>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/Anpworld.Comm.js"></script>
				<script type="text/javascript">
		var vTimerID;
		
		var times=50;
		
		var requestparam = ${storeSeq}+"";

        // 查询客户余额 开始
        function QueryBalance() {
            //var Number = document.getElementById('CustomerNumber');
            //var customerNumber = "";
            //var trimNumber = Trim(Number.value);
            //if ((trimNumber.substring(0, 1) == "1" && trimNumber.length == 11) || trimNumber.substring(0, 1) == "0") {
            //    customerNumber = trimNumber;
            //} else {
            //    customerNumber = areaCode + trimNumber;
            //}
            //var rPort = document.getElementsByName("VoucherType");
            //var typestr = "";
            //for (i = 0; i < rPort.length; i++) {
            //    if (rPort[i].checked)
            //        typestr = rPort[i].value;
            //}

            vTimerID = setTimeout("DoQueryBalanceError()", 30000);

            //Dialog.Running("<font color=red size=5>正在查询：<br/>" + ${mobile} + " 的余额,<br/>请稍候.....</font>");
            //"CustomerNumber=" + customerNumber + "&VoucherType=" + typestr + "&";
            //var requestparam = Anpworld.SerializeForm(document.forms["fm_refill"]);
            var call = Anpworld.AjaxCall3("/order/page!query.do", "storeSeq=${storeSeq}",
            QueryBalanceOver, DoQueryBalanceError);
        }

        function QueryBalanceOver(response) {
            //clearTimeout(vTimerID);
            //Dialog.Running.Close();
            //parent.Restart();
            if (response != null && response != "null") {
            	times = 50;
            	var userpack=new Object();
                var jasonText = eval("("+response+")");
                //switch (jasonText.result) {
                //    case "1":
                //    	clearTimeout(vTimerID);
                        //location.href = "${pageContext.request.contextPath}/order/page!chargeTelecom.do?storeSeq=${storeSeq}";
                        //parent.window.parent.name = "__self";
                        //parent.window.parent.open("${pageContext.request.contextPath}/order/page!chargeTelecom.do?storeSeq=${storeSeq}" , "__self");
                //        var userpack=new Object();
				//		userpack.result=1;				
				//		userpack.storeSeq="${storeSeq}";				
				//		window.returnValue = userpack;
                //        window.close();
                //        break;
//
//                    default:
                        //Dialog.AlertWith(jasonText.retresult,
                        //   function() { document.getElementById("CustomerNumber").focus(); });
//                        cnWait(2);
//                        var call = Anpworld.AjaxCall3("/order/page!query.do", "storeSeq=${storeSeq}",
//            		QueryBalanceOver, DoQueryBalanceError);
//                        break;
//                }
					userpack.result=jasonText.respCode;
					userpack.storeSeq="${storeSeq}";
					userpack.mobile=jasonText.mobile;
					userpack.payType=jasonText.payType;
					var balance = jasonText.cBalance;
					userpack.balance= balance/100;
					userpack.name=jasonText.cName;
					window.returnValue = userpack;
					window.close();
            }
            else {
                //Dialog.AlertWith('查询客户余额超时，请稍后再试.',
                //function() { document.getElementById("CustomerNumber").focus(); });
                
                if(times >0){
                	times = times - 1;
                	$("#retVal").html(times);
                	sleep(1000);
                	var call = Anpworld.AjaxCall3("/order/page!query.do", "storeSeq=${storeSeq}",
            			QueryBalanceOver, DoQueryBalanceError);
                }else{
                	$("#retVal").html("查询超时,重查请关闭窗口");
                }
            }
            
        }

        function DoQueryBalanceError() {
            clearTimeout(vTimerID);
            //Dialog.Running.Close();
            //Dialog.AlertWith("查询客户号码余额失败.",
            //function() { document.getElementById("CustomerNumber").focus(); });
            var tx1 = document.getElementById("tx1");
            tx1.value = "号码查询失败,请确认号码是否正确";
        }
        // 查询客户余额 结束
        
        function cnWait(second){  
	      var startTime,endTimes,s;   
		      var d=new Date();   
		      startTime=d.getTime();   
		      while(true){  
		      d=new Date();  
		      endTime=d.getTime();  
		      s = (endTime-startTime)/1000;  
		      if (s >= second)  
		          break;  
		      }  
		}
        
	</script>
	</head>
	<body onload="QueryBalance();">

		<%@ include file="../common/incBanner.jsp"%>
		<s:form name="strutsForm" action="page!doQueryBalance"
			namespace="/order" method="post">
			<p>
				&nbsp;<s:hidden name="storeSeq" id="storeSeq"></s:hidden>
			</p>
			<p>
				&nbsp;<s:hidden name="mobile" id="mobile"></s:hidden>
			</p>
			<table width="670" border="0" align="center" cellspacing="3"
				class="form_tb">
				<tr>
					<td colspan="5"></td>
				</tr>

				<tr>
					<td width="145" align="right">
						${mobile }
					</td>
					<td align="left" class="nowrap">
						<label id="tx1">正在查询余额,请稍后...</label>
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div>
                        <span id="retVal"></span></div>
					</td>
				</tr>

			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>

