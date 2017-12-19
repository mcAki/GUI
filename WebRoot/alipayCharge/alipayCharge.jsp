<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
	     <link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css" />
		<%@ include file="../common/incHead.jsp"%>
		<%@ include file="../common/incFooter.jsp"%>
		<%@ include file="../common/incBanner.jsp"%>
<script type="text/javascript" src="../js/show_div.js"></script>  
    <script>
      $(document).ready(function() {
             $("#total_fee").keyup(function(){
                 var total_fee = $("#total_fee").val();
                 if(total_fee != 1){
	                 	if(total_fee == ""||total_fee<500){
				       $(".total_fee_c").show();
				       $(".total_fee_c").html("金额不能小于500");
				    }else if(total_fee<2000){
				       $(".total_fee_c").show();
				       $(".total_fee_c").html("扣除一元手续费以后还剩下"+(total_fee-1)+"元");
				    }else{
				       $(".total_fee_c").hide();
				    }
                 }else{
                 	$(".total_fee_c").hide();
                 }
             });
			 $(".chongzhi11").click(function(){
			    var total_fee = $("#total_fee").val();
			    var userName = $("#user_name").val();
			    if(total_fee != 1){
			    	 if(total_fee == ""||total_fee<500){
				     $(".total_fee_c").show();
				       $(".total_fee_c").html("金额不能小于500");
				    }else if(total_fee>=500){
		                $(".amount_div").html(total_fee);
		                $(".account_number").html(userName);
		                
				     show('fd',jQuery.Event());
				     $("#strutsAlipayForm").attr('target', '_blank');
				     $("#strutsAlipayForm").submit();
				    }else{
				       alert("输入有误");
				    }
			    }else{
			    	$(".amount_div").html(total_fee);
		            $(".account_number").html(userName);
		                
				     show('fd',jQuery.Event());
				     $("#strutsAlipayForm").attr('target', '_blank');
				     $("#strutsAlipayForm").submit();
			    }
			    
			 });
      })
      function checked(elm_id)
{
    var danxuananniu = document.getElementById(elm_id);
        danxuananniu.checked = "checked";
        //alert(bank.value);
} 
     function moreBank(){
        $("#more_bank").toggle(500);
     }
     
 
     
    </script>		
<style>
 .blank_c img{
   cursor: pointer;
   height: 25px;
   width:125px;
   border: 1px;
   border-color: #ccc;
   border-style: solid;
   margin: 2px;
 }
 #more_bank img{
   cursor: pointer;
   height: 25px;
   width:125px;
    border: 1px;
   border-color: #ccc;
   border-style: solid;
   margin: 2px;
 }
 
 /* 弹出层 */
 
 #upcontent {
	list-style-position: outside;
	list-style-image: none;
	list-style-type: none;
}
#upcontent li {
	font-size:14px;
	color:#333;
	line-height:150%;
}
#bodyL {
	float:left;
	width:84px;
	margin-right:2px;
}
#tittleup {
	font-size:14px;
	font-weight:bold;
	color:#000066;
	padding-left:25px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #d0daec;
	margin-bottom: 10px;
	padding-bottom: 10px;
}
a.od {
	float:right;
	font-size:14px;
	color: #CC0000;
	text-decoration: none;
}
a.od:hover {
	color:#FF0000;
}
#fd {
	background:#EDF1F8;
	border: 2px solid #849BCA;
	margin-top:2px;
	margin-left:2px;
	float:left;
	overflow:hidden;
	position:absolute;
	left:30%;
	top:30%;
	vertical-align:middle;
	cursor:move;
	float:left;/*filter:alpha(opacity=50);*/
	z-index: 10;
	
}
.contentup {
	padding:20px;
}
.success_button{
    padding:5px 20px;
    background-color: #CC0000;
    color: #ffffff;
    font-size: 14px;
    font-weight: bold;
    cursor: pointer;
    margin-left: 20xp;
    margin-right: 20xp;
}
 #div_bottom{
    margin-left: 200px;
    mprgin-bottom:20px;
 }
</style>

	</head>
	<body style="min-width: 700px;">
	 <div id="comInfo" class="fixed">
          <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
              <div class="tqyj fixed">
                    <strong class="tqxj">支付宝在线充值 <img src="/MPRS/img/blank/alipay_i_safe.gif" alt="" /> </strong>
                    <div class="update fixed">
                             <div class="upadeform country communi" style="width:650px;">
                                   	<s:form name="strutsAlipayForm" id="strutsAlipayForm" action="alipayChargeAct" namespace="/alipayCharge" method="post" target="_parent">
									  	 <s:hidden name="userId" />
									  	 <s:token/>
									  	  <input type="hidden" id="user_name" value="${sessionScope.user.userName }"/>
									  		<center><s:fielderror></s:fielderror></center>
									       <p ><span class="alipay_font"> 用&nbsp;户:</span >&nbsp;&nbsp; ${sessionScope.user.userName }</p>
									       <p ><span class="alipay_font"> 填写金额:</span> <input type="text" name="total_fee" class="jmm" id="total_fee"/> </p>
									       <p class="total_fee_c" style="color: red; display: none;"></p>
									       <p >在线支付,支付2000元以下的扣除1元手续费</p>
									       <p >最低充值金额为500元</p>
									       <p style="color: red;"><strong>注：</strong> 充值成功以后如果没有跳转到本系统，切勿关闭页面，以免影响到账。</p>
									       <%--
									       <p >注&nbsp;意:   第一次使用在线充值方式加款，请先转帐0.01成功并确定额度到账后再转计划加款金额。</p>
									        --%>
									     
									       <p class="blank_c"> <span class="alipay_font">选择网银：</span><br/>
									         
									           <input type="radio" name="defaultBank" id = "ICBCB2C" value="ICBCB2C" checked="checked"> <img  onclick="checked('ICBCB2C')"  src="/MPRS/img/blank/ICBC_OUT.gif" alt="" />
									          
												<input type="radio" name="defaultBank" id = "CCB" value="CCB">  <img onclick="checked('CCB')" src="/MPRS/img/blank/CCB_OUT.gif" alt="" />
									         
												<input type="radio" name="defaultBank" id = "ABC" value="ABC">   <img onclick="checked('ABC')" src="/MPRS/img/blank/ABC_OUT.gif" alt="农业银行" />
									         
												<input type="radio" name="defaultBank" id = "CMB" value="CMB">   <img onclick="checked('CMB')"  src="/MPRS/img/blank/CMB_OUT.gif" alt="招商银行" /><br/>
									          
												<a href="javaScript:;" onclick="moreBank()">选择更多银行</a> 
												
												<div id="more_bank" style="display: none;">
												<br/>
												<br/>
												<br/>
												
												<input type="radio" name="defaultBank" id = "SPDBB2B" value="SPDBB2B"> <img onclick="checked('SPDBB2B')" src="/MPRS/img/blank/B2B ENV_SPDB_OUT.gif" alt="上海浦东发展银行（企业）" /> 
												      
												<input type="radio" name="defaultBank" id = "BOCBTB" value="BOCBTB">  <img onclick="checked('BOCBTB')" src="/MPRS/img/blank/BOC_OUT.gif" alt="中国银行" />
												     
												<input type="radio" name="defaultBank" id = "SPDB" value="SPDB"> <img onclick="checked('SPDB')" src="/MPRS/img/blank/SPDB_OUT.gif" alt="上海浦东发展银行" />
												     
												<input type="radio" name="defaultBank" id = "CIB" value="CIB">  <img onclick="checked('CIB')" src="/MPRS/img/blank/CIB_OUT.gif" alt="兴业银行" /><br/>
												    <br/>
												   
												<input type="radio" name="defaultBank" id = "GDB" value="GDB"> <img  onclick="checked('GDB')" src="/MPRS/img/blank/GDB_OUT.gif" alt="广东发展银行" />
												      
												<input type="radio" name="defaultBank" id = "CMBC" value="CMBC">   <img onclick="checked('CMBC')" src="/MPRS/img/blank/CMBC_OUT.gif" alt="中国民生银行" />
												      
												<input type="radio" name="defaultBank" id = "COMM" value="COMM">   <img onclick="checked('COMM')" src="/MPRS/img/blank/COMM_OUT.gif" alt="交通银行" />
												       
												<input type="radio" name="defaultBank" id = "CITIC" value="CITIC">     <img onclick="checked('CITIC')" src="/MPRS/img/blank/CITIC_OUT.gif" alt="中信银行" /><br/>
												      <br/>
												   
												<input type="radio" name="defaultBank" id = "HZCBB2C" value="HZCBB2C">   <img onclick="checked('HZCBB2C')" src="/MPRS/img/blank/HZCB_OUT.gif" alt="杭州银行" />
												     
												<input type="radio" name="defaultBank" id = "CEBBANK" value="CEBBANK">    <img onclick="checked('CEBBANK')" src="/MPRS/img/blank/CEB_OUT.gif" alt=" 中国光大银行" />
												     
												<input type="radio" name="defaultBank" id = "SHBANK" value="SHBANK">  <img onclick="checked('SHBANK')" src="/MPRS/img/blank/SHBANK_OUT.gif" alt="上海银行" />
												      
												<input type="radio" name="defaultBank" id = "NBBANK" value="NBBANK">  <img onclick="checked('NBBANK')" src="/MPRS/img/blank/NBBANK_OUT.gif" alt="宁波银行" /><br/>
												      <br/>
												    
												<input type="radio" name="defaultBank" id = "SPABANK" value="SPABANK">   <img  onclick="checked('SPABANK')"src="/MPRS/img/blank/SPABANK_OUT.gif" alt="平安银行" />
												     
												<input type="radio" name="defaultBank" id = "BJRCB" value="BJRCB">   <img onclick="checked('BJRCB')" src="/MPRS/img/blank/BJRCB_OUT.gif" alt="北京农村商业银" />
												       
												<input type="radio" name="defaultBank" id = "FDB" value="FDB">   <img onclick="checked('FDB')" src="/MPRS/img/blank/FDB_OUT.gif" alt="富滇银行" />
												       
												<input type="radio" name="defaultBank" id = "POSTGC" value="POSTGC">   <img onclick="checked('POSTGC')" src="/MPRS/img/blank/PSBC_OUT.gif" alt="中国邮政储蓄银" />
												       
									            </div>
									       </p>
									        
									       <br/>
									       <center>
									         
									  	     <button type="button" value="充值"  class="chongzhi11"> </button>
									       </center>
									</s:form>
                             </div>
                    </div>
             </div>
          </div>
      </div>
<div id="fd" style="display:none;filter:alpha(opacity=100);opacity:1;height: 260px;">
  <div class="contentup"> <a href="#" class="od" onclick = "closeed('fd');return false;"> 关 闭 </a>
    <div id="tittleup">请确认支付结果</div>
    <ul id="upcontent">
      <li><span style='font-weight: bold; color:red;font-size:16px;'> 请您在新打开的页面完成支付 </span> </li><br/>
      <li>支付账号：<span class="account_number" style="font-weight: bolder;"> </span> </li>
      <li>支付金额：<span class="amount_div" style="font-weight: bolder;"> </span>元 </li>
      <li>支付完成前请不要关闭此窗口</li>
      <li>支付失败时，可拨打4000307517，妙鸣在线客服将竭尽为您服务。</li>
    </ul>
  </div>
  <div id="div_bottom">
       <a onclick = "closeed('fd');return false;"><span class="success_button"> 支付成功</span></a>
     <a href="/MPRS/alipayCharge/alipayPage.do">  <span class="success_button"> 重新下单</span></a>
  </div>
</div>
      
	</body>
</html>
