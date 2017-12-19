<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
				<%@ include file="../common/incHead.jsp"%>
				<script type="text/javascript">
		function showQuery(){
		
			//计算弹出位置
			
			height = 200;
			width = 300;
			//barHeight=30;
			//top = (screen.Height/2) - (height/2) - barHeight;
			//left = (screen.width/2) - (width/2);
			var mobile = document.getElementById("mobile").value;
			
			var tradeType = document.getElementsByName("tradeType");
			var traValue;
			for(var i=0;i<tradeType.length;i++){
				if(tradeType[i].checked)
				traValue = tradeType[i].value;
			}
	
			//要查询的链接
			var url="${pageContext.request.contextPath}/order/page!doQueryBalance.do?mobile="+mobile+"&tradeType="+traValue;
			//为了处理缓存加入随机时间
			url += "&date=" + new Date().getTime();
			
			//弹出参数
			var features = "resizable=yes;center=yes;status=yes;fullscreen=0;scroll=no;dialogWidth="+ width +"px;dialogHeight=" + height + "px";
			
			//创建传入参数
			var obj = new Object();
			obj.name = "参数";
			var returnVal = window.showModalDialog(url,obj,"dialogWidth=600px;dialogHeight=500px");
			if(!$.isEmptyObject(returnVal)){
				$("#userId").val(returnVal.uid);
				$("#userName").val(returnVal.username);
			}
		}
		
		function sub(){
			var qq_match = /^[1-9]\d{4,9}$/;
			if(qq_match.test(mobile.value)){
				strutsForm.submit();
			}else{
				alert("请填写正确的充值QQ号码！");
			}
			
		}
		
		function at_ele10_onclick(obj){
			var goodsNo = document.getElementById("goodsNo");
			goodsNo.value = obj.value;
		
		}
	</script>
	</head>

		<%@ include file="../common/incBanner.jsp"%>
		<s:form name="strutsForm" action="doAddQQRechargeOrder"
			namespace="/order" method="post">
			<center> <s:fielderror></s:fielderror></center>
			<s:token />
			<s:hidden name="mainorder.mainOrderId" />
			<s:hidden name="orderdetail.orderDetailId" />
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<table width="670" border="0" align="center" cellspacing="3"
				class="form_tb">
				<tr>
					<td align="right"></td>
					<td align="left" class="f14">
						充值QQ号：
					</td>
					<td align="left">
						<input name="mobile" id="mobile" type="text" maxlength="100" />

					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td align="left" class="f14">
						充值Q币数量：
					</td>
					<td align="left">
						<input name="goodsNo" id="goodsNo" type="text" />

					</td>
					<td align="right">&nbsp;</td>
				</tr>
				<tr>
					<td align="left">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
					<td align="left">
						<input name="radio" id="radio5" type="radio"
							onclick="at_ele10_onclick(this);" value="5">
						<label for="radio5" id="radio51">
							5个Q币&nbsp;
						</label>
						<input name="radio" id="radio6" type="radio"
							onclick="at_ele10_onclick(this);" value="6">
							<label for="radio6" id="radio61">
								6个Q币&nbsp;
							</label>
							<input name="radio" id="radio10" type="radio"
								onclick="at_ele10_onclick(this);" value="10">
								<label for="radio10" id="radio101">
									10个Q币&nbsp;
								</label>
								<Br>
									<input name="radio" id="radio15" type="radio"
										onclick="at_ele10_onclick(this);" value="15">
										<label for="radio15" id="radio151">
											15个Q币
										</label>
										<input name="radio" id="radio20" type="radio"
											onclick="at_ele10_onclick(this);" value="20">
											<label for="radio20" id="radio201">
												20个Q币
											</label>
											<input name="radio" id="radio30" type="radio"
												onclick="at_ele10_onclick(this);" value="30">
												<label for="radio30" id="radio301">
													30个Q币&nbsp;
												</label>
												<br>
													<input name="radio" id="radio50" type="radio"
														onclick="at_ele10_onclick(this);" value="50">
														<label for="radio50" id="radio501">
															50个Q币
														</label>
														<input name="radio" id="radio60" type="radio"
															onclick="at_ele10_onclick(this);" value="60">
															<label for="radio60" id="radio601">
																60个Q币
															</label>
															<input name="radio" id="radio100" type="radio"
																onclick="at_ele10_onclick(this);" value="100">
																<label for="radio100" id="radio1001">
																	100个Q币
																</label>
																<br>
																	<input name="radio" id="radio120" type="radio"
																		onclick="at_ele10_onclick(this);" value="120">
																		<label for="radio120" id="radio120l">
																			120个Q币
																		</label>
					</td>
					<td align="right">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><br /><br /></td>
					<td align="left" class="f14">
						<span id="at_ele13sst">注意</span>：
					</td>
					<td align="left">
						<span id="at_ele13ss" class="greyinput"><span
							id="at_ele13s" style="color: "><span
								style="color: red; font-size: 9pt">一个QQ号码一天最多可以充值5000个Q币，单次充值不能超出500个Q币；

							
					</td>
					<td align="right">&nbsp;</td>
				</tr>
				<tr>
			      	<td align="right" >输入密码:</td>
				 	<td align="left"><s:password  name="businessPassword" id="12" cssStyle="font-size:30px;"/></td>
				 	<td>&nbsp;</td>
				 	<td>&nbsp;</td>
			      </tr>
				<tr>
					<td colspan="4">
						重要说明： 1. Q币充值无冲正功能，请谨慎操作！ 2.
						如果用户的QQ号码属于QQ行号码或靓号，且号码因欠费而已被回收将无法充值；如果号码已欠费但还未被回收，必须在充值成功后及时续费才能登录使用。QQ号码一旦被回收，其个人帐户里的Q币余额也将一并收回，不予返还。请代理商务必对用户说明。
						3.
						充值成功后，可以登录my.qq.com，查询个人帐户余额，并用个人帐户来支付各种QQ增值服务，如：QQ秀、QQ交友中心、QQ会员服务等等。
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="提交" onclick="sub()" />
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp"%>

	</body>
</html>

