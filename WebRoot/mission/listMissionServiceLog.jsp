<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<%@ page import="com.sys.volunteer.common.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <%@ include file="../common/incHead.jsp" %>
	<link href="../css/criteria.css" rel="stylesheet" type="text/css">  
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
	
	
<script type="text/javascript">


var STATE_NORMAL   = "正常出勤";
var STATE_LATER    = "迟到/早退";
var STATE_SKIPWORK = "旷工"; 
var STATE_ASKOFF   = "请假";
//正在修改的Row的指针
var modifyRow = null;
var modifyState = null;


//修改考勤状态CSS
function setStateCss(modifyState,state){
	modifyState.removeClass('state1');
	modifyState.removeClass('state2');
	modifyState.removeClass('state3');
	modifyState.removeClass('state4');
	modifyState.addClass(state);
}

//点击并关闭设置考勤时间对话框
function closeAndSetTime(haveChanged){
	//用haveChanged标记是否点击确认而关闭对话框的
	$('#divSetTime').attr('changevalue',haveChanged);
	$('#divSetTime').jqmHide();
}

//点击选择并关闭迟到/早退对话框
function closeAndSetLateTime(haveChanged){
	//用haveChanged标记是否点击确认而关闭对话框的
	$('#divSetLateTime').attr('changevalue',haveChanged);
	$('#divSetLateTime').jqmHide();
}

function formatFloat(src, pos)
{
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}

//获取迟到时间(计算原始迟到时间的)
function getLateTime(jQrows,extendMinute){
//	alert(jQrows.children('.lateTime').val());
//	alert(jQrows.children('.hidLateMinute').val());	
	min = parseInt(0) + 1*(jQrows.children('.hidLateMinute').val());
	min += extendMinute;
	return (min>0)?min + '分':'无迟到';
}

function getEarlyTime(jQrows,extendMinute){
	min = parseInt(0) + 1*(jQrows.children('.hidEarlyMinute').val());
	min += extendMinute;
	return (min>0)?min + '分':'无早退';
}

function getStateName(str){
	switch(str){
	case STATE_NORMAL:
		return 1;
	case STATE_LATER:
		return 2;
	case STATE_SKIPWORK:
		return 4;
	case STATE_ASKOFF:
		return 5;
	default: 
		return 1;
	}
}

var arrSubmit;


function beginBatchCallBack(){	

	for(i=0;i<arrSubmit.length;i++){
		bacthCheckonAjax(arrSubmit[i][1],arrSubmit[i][2],arrSubmit[i][3],arrSubmit[i][4],arrSubmit[i][5]);
	}

	
	alert('已经全部登陆完毕，请点击确认');
}

function bacthCheckonAjax( uid, state, serviceMinute, lateMinute, earlyMinute){
	var missionTeamId = $('#missionTeamId').val();
	var checkOnDate = $('#checkOnDate').val();
	
	stateNum = getStateName(state);
	
	serviceMinute = serviceMinute.replace('分','');
	serviceMinutes = serviceMinute.split('小时');
	if(serviceMinutes.length <1){
		serviceMinute=0;
	}else{
		serviceMinute=(parseInt(serviceMinutes[0]) * 60) + (parseInt(serviceMinutes[1]) * 1);
		//alert(serviceMinute);
	}
	
	//serviceMinute = serviceMinute.replace('小时',':');
	

	lateMinute = lateMinute.replace('分','');
	earlyMinute = earlyMinute.replace('分','');
	//alert(lateMinute+"  "+earlyMinute );
	lateMinute = lateMinute.replace('无迟到','0');
	lateMinute = lateMinute.replace('无早退','0');
	earlyMinute = earlyMinute.replace('无迟到','0');
	earlyMinute = earlyMinute.replace('无早退','0');
	//alert(lateMinute+"  "+earlyMinute );
	lateMinute = 1*lateMinute;
	earlyMinute = 1*earlyMinute;
	//lateMinute = ($.trim(lateMinute));
	//earlyMinute = ($.trim(earlyMinute));		
	//alert(lateMinute+"  "+earlyMinute );
	var jsonData = {		
		"missionServiceLog.checkOnDate":$.trim(checkOnDate),
		"missionTeamId":missionTeamId,
		"missionServiceLog.usersByUserId.userId":$.trim(uid),
		"missionServiceLog.serviceMinute":serviceMinute,
		"missionServiceLog.state":stateNum,
		"missionServiceLog.arriveLateMinute":1*lateMinute,
		"missionServiceLog.leaveEarlyMinute":1*earlyMinute
	};
	
	//alert("步骤:1  提交json");
	
	ezAjax("${pageContext.request.contextPath}/addServiceLog.do",
		jsonData,
		"服务器出错",
		false,
		function(msg){
			if(msg.flag==<%=Const.MISSION_SERVICELOG_CHECK_LONG%>){
				alert(msg.description);
			}else{
				//alert(msg.description);
			}
			
			w=window.document.getElementById('divAjaxResult');
			//s = $('#divAjaxResult').html();
			//$('#divAjaxResult').html(s + msg.description + '<br/>');			
			s=w.innerHTML;
			w.innerHTML = s + msg.description + '<br/>';
			
			sleep(200);
			//再次启动递归
			//beginBatchCallBack();
			//alert(msg.desc);
		}
	);
}


$(document).ready(function() {
	
	//刚打开程序时候进行时间替换
	
	$('.rows:not(.header) .totalHour').each(
		function(i){
			minute = $(this).html();
			hour = Math.floor(minute/60) + ':' + (minute%60);
			$(this).html(hour);
		}
	);

	//打开时候把屏蔽区的东西屏蔽
	$('#nocheckon .rows').addClass('disabled');
	$('#nocheckon .rows .enable').html('启用');

	//===弹出框设定与定义=======================
	
	
	
	$('#divSubmit').jqm({
		overlay:20,//半透明背景层
		modal: true, 
		trigger: false,
		onShow:function(h){
			h.w.css('opacity',0.94).show();
			
		},
		onHide:function(h){
			h.w.slideUp("fast",function(){
				
			});
		}		
	});
	
	$('#divSetState').jqm({//设置状态的弹出
		//trigger:'.state',//'#btnSetState',
		overlay:70,//半透明背景层
		onShow:function(h){
			h.w.css('opacity',0.96).slideDown("fast");
		},
		onHide:function(h){
			h.w.slideUp("fast",function(){
				if(h.o)h.o.remove();
			});
		}
	}).jqmAddClose($('#divSetState #btnState_cancel'));

	$('#divSetLateTime').jqm({
		//trigger:'.state',//'#btnSetState',
		overlay:70,//半透明背景层
		onShow:function(h){
			h.w.css('opacity',0.96).slideDown("fast");
			//$('#dpClearInput').html('aa');
		},
		onHide:function(h){
			h.w.slideUp("fast",function(){
				if(h.o)h.o.remove();
				//触发自定义事件
				$('#divSetLateTime').trigger("onClosed",[""+$('#txtLateTime').val(),""+$('#txtEarlyTime').val()]);
			});
		}		
	});
	
	$('#divSetTime').jqm({
		//trigger:'.state',//'#btnSetState',
		overlay:70,//半透明背景层
		onShow:function(h){
			$('#divSetTime').attr('changevalue',0);	
			h.w.css('opacity',0.96).slideDown("fast");
		},
		onHide:function(h){
			h.w.slideUp("fast",function(){
				if(h.o)h.o.remove();
				if($('#divSetTime').attr('changevalue')>0){
					//触发自定义事件
					$('#divSetTime').trigger("onClosed",[$('#txtWorkTime').val()]);
				}
			});
		}		
	});

	//====================
	//$('#divSetTime').bind('onClosed',function(e,time){alert('事件1');});
	$('#btnSubmitAll').click(
		function(){
			//点击批量考勤，开始调用递归提交
			$('#divSubmit').jqmShow();
			//$(this).attr('disabled','disabled');
					
			x =$('#checkon .rows:not(.header)'); 
			count = x.length;
			arrSubmit=new Array(count);
			
			for(i=0;i<count;i++){
				c=$('#checkon .rows:not(.header)').get(i);
				hidUid=$(c).children('.hidUid').val();
				if((hidUid!=null)&&(hidUid!='')){
					state=$(c).children('.state').html();
					hour=$(c).children('.hour').html();
					lateTime=$(c).children('.lateTime').html();
					earlyTime=$(c).children('.earlyTime').html();
					var arr= new Array(6);
					arr[0]=1;
					arr[1]=hidUid;
					arr[2]=state;
					arr[3]=hour;
					arr[4]=lateTime;
					arr[5]=earlyTime;
					arrSubmit[i]=arr;
				}
			}			
			
			beginBatchCallBack();
		}
	);


	$('.rows:not(.header) .hour').click(/*设定考勤时间*/
		function(){	
		//检查当前框属于登记,还是屏蔽的
			var divname = $(this).parent().parent().parent().parent().attr("id");
			//alert(x);
			if(divname=="checkon"){
				//关闭事件重新绑定
				var eventObj = $(this);
				$('#divSetTime').unbind('onClosed');
				$('#divSetTime').bind('onClosed',function(e,time){
					eventObj.html(time);
				});
				//读取原来的值
				$('#txtWorkTime').val(eventObj.html());
				$('#divSetTime').jqmShow();
			}
		}
	);
	
	$('#btnSetAllTime').click(/*设定所有考勤时间*/
		function(){	
			//关闭事件重新绑定
			$('#divSetTime').unbind('onClosed');
			$('#divSetTime').bind('onClosed',function(e,time){
				$('#checkon .rows:not(.header) .hour').html(time);
			});
			$('#txtWorkTime').val('1小时0分');
			$('#divSetTime').jqmShow();
			//$('#checkon .rows:not(.header) .hour').html('99');
		}
	);
	
	$('#btnSetState_1').click(/*功能按钮:正常考勤*/
		function(){
			modifyState.html( STATE_NORMAL );
			setStateCss(modifyState,'state1');
			modifyRow.children('.lateTime').html(getLateTime(modifyRow));
			modifyRow.children('.earlyTime').html(getLateTime(modifyRow));			
			$('#divSetState').jqmHide();
		}
	);
	
		
	$('#btnAllEnable').click(/*取消所有屏蔽人员*/
		function(){	
			$("#nocheckon .enable").click();			
		}
	);
	
	$('#btnSetState_2').click(/*功能按钮:迟到/早退*/
		function(){			
			$('#divSetState').jqmHide();
			//绑定事件
			$('#divSetLateTime').unbind('onClosed');
			$('#divSetLateTime').bind('onClosed',function(e,latetime,earlytime){
				if($('#divSetLateTime').attr('changevalue')>0){
					modifyRow.children('.lateTime').html(getLateTime(modifyRow,latetime));
					modifyRow.children('.earlyTime').html(getLateTime(modifyRow,earlytime));
					modifyState.html( STATE_LATER );
					setStateCss(modifyState,'state2');
				}else{
					modifyState.html( STATE_NORMAL );
					setStateCss(modifyState,'state1');
					modifyRow.children('.lateTime').html(getLateTime(modifyRow));
					modifyRow.children('.earlyTime').html(getLateTime(modifyRow));							
				}
			});			
			$('#divSetLateTime').jqmShow();
		}
	);
	
	$('#btnSetLateTime').click(/*功能按钮:迟到/早退 (填时间)*/
		function(){
			modifyState.html( STATE_LATER );
			setStateCss(modifyState,'state2');
			$('#divSetLateTime').jqmHide();
		}
	);

	$('#btnSetState_3').click(/*功能按钮:旷工*/
		function(){
			modifyState.html( STATE_SKIPWORK );
			setStateCss(modifyState,'state3');
			modifyRow.children('.hour').html('0小时00分');
			$('#divSetState').jqmHide();
		}
	);

	$('#btnSetState_4').click(/*功能按钮:请假*/
		function(){
			modifyState.html( STATE_ASKOFF );
			setStateCss(modifyState,'state4');
			$('#divSetState').jqmHide();
		}
	);
	
	$(".rows:not(.header) .faltBtnGreen").hover(/*按钮状态按钮群*/
		function () {
			$(this).addClass("faltBtnGreen_hover");
		},
		function () {
			$(this).removeClass("faltBtnGreen_hover");					
		}
	);

	$(".state").click(
		function(){
			//检查当前框属于登记,还是屏蔽的
			var divname = $(this).parent().parent().parent().parent().attr("id");
			//alert(x);
			if(divname=="checkon"){
				//移动考勤人到屏蔽区域
				modifyState = $(this);
				modifyRow = $(this).parent();
				$('#labStateName').html($(this).prev('.username').html());
				$('#divSetState').jqmShow();
			}
		}
	);
	
	$(".rows:not(.header) .enable").click(
		function(){
			//检查当前框属于登记,还是屏蔽的
			var divname = $(this).parent().parent().parent().parent().attr("id");
			//alert(x);
			if(divname=="checkon"){
				//移动考勤人到屏蔽区域
				//$("#nocheckon").find("#title").show();								
				$(this).parent().fadeOut(100,
					function(){
						//alert("删: "+$(this).parent());
						tmpLi = $(this).parent();
						$(this).children('.enable').text('启用').removeClass("faltBtnGreen_hover");
						//tmpLi.remove();
						var itemobj = $(this).parent().appendTo("#nocheckon ul");
						$(this).removeClass("hover").addClass("disabled").show(100);
					}			
				);
			}else if(divname=="nocheckon"){
				//恢复考勤人到考勤区域
				$(this).parent().fadeOut(100,
					function(){
						tmpLi = $(this).parent();
						$(this).children('.enable').text('屏蔽').removeClass("faltBtnGreen_hover");						
						//tmpLi.remove();
						var itemobj = $(this).parent().appendTo("#checkon ul");
						$(this).removeClass("hover").removeClass("disabled").show(100);
					}
				);				
			}

		}
	);
	
	
//$(".enable").addClass("hover");
});

//弹出考勤日期
function openMy97Checkondate(){

	WdatePicker({
		//eCont:'divCheckOnDate',
		onpicked:function(dp){
			alert('你选择批量考勤的日期是: '+dp.cal.getDateStr())
			//$('#checkOnDate').val(dp.cal.getDateStr());
			$('#strutsForm').submit();
			//alert($('#checkOnDate').val());
			//alert(dp.cal.getNewDateStr());
		},
		dateFmt:'yyyy年M月d日',
		//minDate:'%y-%M-{%d-7}',
		//maxDate:'%y-%M-%d',
		minDate:'${StartDate}',
		maxDate:'${EndDate}',
		//startDate:'${checkOnDate}',
		vel:'checkOnDate',
		isShowClear:false,
		isShowToday:false
	})

}

</script>
<style type="text/css">
.faltBtnGreen{
	border:solid 1px;
	border-color:#E4FFCA #390 #390 #E4FFCA;
	background:#6C3;
	height:22px;
	line-height:22px;	
}
.faltBtnGreen_hover{
	border-color:#FF9 #930 #930 #FF9;
	background:#FC0;
	cursor:pointer;
}

.checkon_grid .hover{
	background-color:#FC0;
}
 
.checkon_grid{
	padding:5px;
}
 
.checkon_grid li{
	padding:0px;
	margin:0px;
	list-style:none;
	list-style-type:none;
}
 
.checkon_grid a:link, 
.checkon_grid a:visited {
	color:#0C0;
} 
.checkon_grid a:hover,
.checkon_grid a:active {
	color: #FF6600;
} 
 
 
.checkon_grid .rows{
	height:24px;
	width:640px;
	white-space:nowrap;
	border-color:#060;
	border-width:1px;
	border-style:solid;
	background-color:#F1FFBF;
	margin:-1px 0px 0px 0px;
	overflow:hidden;
	padding:1px;
}

.checkon_grid .disabled{
	background-color:#EEEEEE;
	color:#999999;
	border-color:#999999;
}

.checkon_grid .header{
	background-color:#FC0;
}

.checkon_grid .rows .uid{
	width:100px;
	overflow:hidden;
	float:left;
}
.checkon_grid .rows .username{
	width:130px;
	overflow:hidden;
	float:left;
}

.checkon_grid .rows .state{
	width:60px;
	overflow:hidden;
	float:left;
}
 
.checkon_grid .rows .minute{
	width:60px;
	overflow:hidden; 
	float:left;
}
 
.checkon_grid .rows .hour{
	width:80px;
	overflow:hidden; 	
	float:left;
	font-weight:bold;
}

.checkon_grid .rows .checkonCount{
	width:60px;
	overflow:hidden; 	
	float:left;
	font-weight:bold;
}

.checkon_grid .rows .totalHour{
	width:80px;
	overflow:hidden; 	
	float:left;
	font-weight:bold;
}
 
.checkon_grid .rows .lateTime{
	width:40px;
	overflow:hidden; 	
	float:left;
}
.checkon_grid .rows .earlyTime{
	width:40px;
	overflow:hidden; 	
	float:left;
}

 
.checkon_grid .rows .enable{
	float:right;
	width:28px;
}
 
/*.checkon_grid .rows .enableControl{
	color:#333;
	height:22px;
	line-height:22px;
}*/

.setStateWindow{
    top: 17%;
    left: 50%;
	    
	width: 200px;
    margin-left: -100px;
    
    background-color: #EEE;
    color: #333;
    border: 1px solid black;
	text-align:center;
}

.submitWindow{
    top: 50px;
    left: 50%;
	    
	width:600px; 
	height:550px;
    margin-left: -300px;
    
    background-color:#fdfffd;
    color: #333;
    border: 1px solid black;
	text-align:center;
}
.submitWindow #divAjaxResult{
	width:600px; 
	height:450px; 
	overflow:auto;
}

.checkon_grid .rows .state1{
}
.checkon_grid .rows .state2{
	background-color:#FF972F;
}
.checkon_grid .rows .state3{
	background-color:#FF7D7D;
}
.checkon_grid .rows .state4{
	background-color:#CCC;
}


/* jqModal base Styling courtesy of;
  Brice Burgess <bhb@iceburg.net> */

/* The Window's CSS z-index value is respected (takes priority). If none is supplied,
  the Window's z-index value will be set to 3000 by default (in jqModal.js). You
  can change this value by either;
    a) supplying one via CSS
    b) passing the "zIndex" parameter. E.g.  (window).jqm({zIndex: 500}); */
.jqmStand{
	margin-left: -300px;
    width: 600px;
    top: 17%;
    left: 50%;

    background-color: #EEE;
    color: #333;
    border: 1px solid black;	
}

.jqmWindow {
    display: none;
    position: fixed;
    padding: 12px;
}

/*弹出框后的背景*/
.jqmOverlay { background-color:#001100; }

/* 修正IE6坐标 */
* html .jqmWindow {
     position: absolute;
     top: expression((document.documentElement.scrollTop || document.body.scrollTop) + Math.round(17 * (document.documentElement.offsetHeight || document.body.clientHeight) / 100) + 'px');
}
 
 
 
 
/*=====================*/


</style>
</head>
<body><center><s:form id="strutsForm" name="strutsForm" action="list!queryServiceLog.do" method="post">
<H3 style="line-height: 25px;">队伍名: ${missionTeam.missionTeamname}</H3>
<input type="text" class="txtW3 Wdate" style="cursor:pointer;" onmousedown="openMy97Checkondate();" value="<fmt:formatDate value="${DatecheckOnDate}" pattern="yyyy年M月d日"/>" />
<div id="divCheckOnDate"></div>
<script type="text/javascript">
</script>
</br>
<input type="button" icon='icon-back' id="btnAllEnable" value="取消全部被屏蔽人员">&nbsp;
<input type="button" icon='icon-event' id="btnSetAllTime" value="批量考勤时间设定">&nbsp;
<input type="button"  icon='icon-success' id="btnSubmitAll" value="批量考勤提交">&nbsp;
<s:hidden name="checkOnDate" id="checkOnDate"/>
<s:hidden name="missionTeamId" id="missionTeamId"/>
<div id="checkon" class="checkon_grid">
<ul>
<li><div id="title" class="rows header">
    <div class="uid">电话</div>
    <div class="username">姓名</div>
    <div class="state">登记状态</div>
    <div class="hour">本次工时</div>
    <div class="lateTime">迟到</div>
    <div class="earlyTime">早退</div>
    <div class="checkonCount">登记次数</div>
	<div class="totalHour">本日总工时</div>
    <div class="enable">操作</div>
</div></li>
<!-- li><div id="r1" class="rows">
    <div class="uid">0010</div>
    <div class="username">张三</div>
    <div class="state faltBtnGreen state1">正常出勤</div>
    <div class="hour faltBtnGreen">0小时00分</div>
    <div class="lateTime">0分</div>
    <div class="earlyTime">0分</div>    
    <div class="checkonCount">0</div>
	<div class="totalHour">2小时00分</div>
    <div class="enable faltBtnGreen">屏蔽</div>
</div></li-->
   <s:iterator value="#request.pageView.records">
   	<s:if test="(countServiceMinute=='') || (countServiceMinute==0)">
	   	<li><div id="r1" class="rows">
	    <div class="uid"><s:property value="mobile"/></div>
	    <div class="username"><s:property value="userName"/></div>
	    <div class="state faltBtnGreen state1">正常出勤</div>
	    <div class="hour faltBtnGreen">0小时00分</div>
	    <div class="lateTime">
	    	<s:if test="arriveLateMinute>0">
	    		<s:property value="arriveLateMinute"/>分
	    	</s:if>
	    	<s:else>无迟到</s:else>	    	
	    </div>
	    <div class="earlyTime">
	    	<s:if test="leaveEarlyMinute>0">
	    		<s:property value="leaveEarlyMinute"/>分
	    	</s:if>
	    	<s:else>无早退</s:else>
	    </div>    
	    <div class="checkonCount">未登记</div>
		<div class="totalHour"><s:property value="sumServiceMinute"/></div>
  		<div class="enable faltBtnGreen">屏蔽</div>
	    <input class="hidUid" type="hidden" value='<s:property value="userid"/>'/><br />
	    <input class="hidLateMinute" type="hidden" value='<s:property value="arriveLateMinute"/>'/>
	    <input class="hidEarlyMinute" type="hidden" value='<s:property value="leaveEarlyMinute"/>'/>
		</div></li>
	</s:if>
   </s:iterator>
</ul>
</div>

<div id="nocheckon" class="checkon_grid">
<ul>
<li>
	<div id="title" class="rows disabled">不需要处理而屏蔽的</div>
</li>
<!-- <li><div id="r5" class="rows">
    <div class="uid">0013</div>
    <div class="username">王八</div>
    <div class="state faltBtnGreen state1">正常出勤</div>
    <div class="hour faltBtnGreen">0小时00分</div>
    <div class="lateTime">0分</div>
    <div class="earlyTime">0分</div>    
    <div class="checkonCount">0</div>
	<div class="totalHour">2小时00分</div>
    <div class="enable faltBtnGreen">屏蔽</div>
</div></li>-->
   <s:iterator value="#request.pageView.records">
   	<s:if test="countServiceMinute > 0">
	   	<li><div id="r1" class="rows">
	    <div class="uid"><s:property value="mobile"/></div>
	    <div class="username"><s:property value="userName"/></div>
	    <div class="state faltBtnGreen state1">正常出勤</div>
	    <div class="hour faltBtnGreen">0小时00分</div>
	    <div class="lateTime">
	    	<s:if test="arriveLateMinute>0">
	    		<s:property value="arriveLateMinute"/>分
	    	</s:if>
	    	<s:else>无迟到</s:else>	    	
	    </div>
	    <div class="earlyTime">
	    	<s:if test="leaveEarlyMinute>0">
	    		<s:property value="leaveEarlyMinute"/>分
	    	</s:if>
	    	<s:else>无早退</s:else>
	    </div> 
	    <div class="checkonCount"><s:property value="countServiceMinute"/>次</div>
		<div class="totalHour"><s:property value="sumServiceMinute"/></div>
	    <div class="enable faltBtnGreen">屏蔽</div>
	    <input class="hidUid" type="hidden" value='<s:property value="userid"/>'/>
	    <input class="hidLateMinute" type="hidden" value='<s:property value="arriveLateMinute"/>'/>
	    <input class="hidEarlyMinute" type="hidden" value='<s:property value="leaveEarlyMinute"/>'/>
		</div></li>
	</s:if>
   </s:iterator>
</ul>
</div>

</s:form>
 
</center>
<div id="divSubmit" class="submitWindow jqmWindow">
<div style="height:30px; color:#FF6600; weight:blod;">自动考勤结果</div>
<div id="divAjaxResult">发送中....<br/></div>
<div><input icon='icon-success' type="button" name="btnReset" id="btnReset" style="width:100px;" value="确定" onclick="javascript:$('#strutsForm').submit();" /></div>
</div>


<div id="divSetState" class="setStateWindow jqmWindow">
    <div>修改: <span id="labStateName"></span></div>
    <div><input icon='icon-fav' type="button" name="btnSetState" id="btnSetState_1" style="width:200px;" value=" 正常出勤 " /></div>
    <div><input icon='icon-collapse-all' type="button" name="btnSetState" id="btnSetState_2" style="width:200px;" value="迟到／早退" /></div>
    <div><input icon='icon-collapse-all' type="button" name="btnSetState" id="btnSetState_3" style="width:200px;" value="　旷　工　" /></div>
    <div><input icon='icon-collapse-all' type="button" name="btnSetState" id="btnSetState_4" style="width:200px;" value="　请　假　" /></div>
    <HR>
    <div><input icon='icon-cancel' type="button" name="btnState_cancel" id="btnState_cancel" style="width:100px;" value="取消选择" onclick="javascript:$('#divSetState').jqmHide();" /></div>
</div>

<div id="divSetLateTime" class="setStateWindow jqmWindow">
	<div style="text-align:left;">请设置迟到与早退的时间<br>迟到与早退可以同时设置<br>注意：<br>如果迟到超过30分钟应属旷工处理</div>
    <div><label>迟到:
    <select class="txtW1" id="txtLateTime" type="text" >
		<option value="0">0分</option>
		<option value="1">1分</option>
		<option value="2">2分</option>
		<option value="3">3分</option>
		<option value="4">4分</option>
		<option value="5">5分</option>
		<option value="6">6分</option>
		<option value="7">7分</option>
		<option value="8">8分</option>
		<option value="9">9分</option>
		<option value="10">10分</option>
		<option value="11">11分</option>
		<option value="12">12分</option>
		<option value="13">13分</option>
		<option value="14">14分</option>
		<option value="15">15分</option>
		<option value="16">16分</option>
		<option value="17">17分</option>
		<option value="18">18分</option>
		<option value="19">19分</option>
		<option value="20">20分</option>
		<option value="21">21分</option>
		<option value="22">22分</option>
		<option value="23">23分</option>
		<option value="24">24分</option>
		<option value="25">25分</option>
		<option value="26">26分</option>
		<option value="27">27分</option>
		<option value="28">28分</option>
		<option value="29">29分</option>
		<option value="30">30分</option>
		<option value="31">31分</option>
		<option value="32">32分</option>
		<option value="33">33分</option>
		<option value="34">34分</option>
		<option value="35">35分</option>
		<option value="36">36分</option>
		<option value="37">37分</option>
		<option value="38">38分</option>
		<option value="39">39分</option>
		<option value="40">40分</option>
		<option value="41">41分</option>
		<option value="42">42分</option>
		<option value="43">43分</option>
		<option value="44">44分</option>
		<option value="45">45分</option>
		<option value="46">46分</option>
		<option value="47">47分</option>
		<option value="48">48分</option>
		<option value="49">49分</option>
		<option value="50">50分</option>
		<option value="51">51分</option>
		<option value="52">52分</option>
		<option value="53">53分</option>
		<option value="54">54分</option>
		<option value="55">55分</option>
		<option value="56">56分</option>
		<option value="57">57分</option>
		<option value="58">58分</option>
		<option value="59">59分</option>
		<option value="60">60分</option>
    </select></label></div>
    <div><label>早退:
    <select class="txtW1" id="txtEarlyTime" type="text" >
		<option value="0">0分</option>
		<option value="1">1分</option>
		<option value="2">2分</option>
		<option value="3">3分</option>
		<option value="4">4分</option>
		<option value="5">5分</option>
		<option value="6">6分</option>
		<option value="7">7分</option>
		<option value="8">8分</option>
		<option value="9">9分</option>
		<option value="10">10分</option>
		<option value="11">11分</option>
		<option value="12">12分</option>
		<option value="13">13分</option>
		<option value="14">14分</option>
		<option value="15">15分</option>
		<option value="16">16分</option>
		<option value="17">17分</option>
		<option value="18">18分</option>
		<option value="19">19分</option>
		<option value="20">20分</option>
		<option value="21">21分</option>
		<option value="22">22分</option>
		<option value="23">23分</option>
		<option value="24">24分</option>
		<option value="25">25分</option>
		<option value="26">26分</option>
		<option value="27">27分</option>
		<option value="28">28分</option>
		<option value="29">29分</option>
		<option value="30">30分</option>
		<option value="31">31分</option>
		<option value="32">32分</option>
		<option value="33">33分</option>
		<option value="34">34分</option>
		<option value="35">35分</option>
		<option value="36">36分</option>
		<option value="37">37分</option>
		<option value="38">38分</option>
		<option value="39">39分</option>
		<option value="40">40分</option>
		<option value="41">41分</option>
		<option value="42">42分</option>
		<option value="43">43分</option>
		<option value="44">44分</option>
		<option value="45">45分</option>
		<option value="46">46分</option>
		<option value="47">47分</option>
		<option value="48">48分</option>
		<option value="49">49分</option>
		<option value="50">50分</option>
		<option value="51">51分</option>
		<option value="52">52分</option>
		<option value="53">53分</option>
		<option value="54">54分</option>
		<option value="55">55分</option>
		<option value="56">56分</option>
		<option value="57">57分</option>
		<option value="58">58分</option>
		<option value="59">59分</option>
		<option value="60">60分</option>
    </select></label></div>
    <hr />
    <div><input icon='icon-success' type="button" onclick="closeAndSetLateTime(1)" style="width:80px;" value="确认" />&nbsp;&nbsp;<input icon='icon-cancel' type="button" onclick="closeAndSetLateTime(0)"  style="width:80px;" value="取消"/></div>
</div>

<div id="divSetTime" class="setStateWindow jqmWindow">
    <div><label>志愿时:</label><input id="txtWorkTime" readonly="readonly" type="text" onmousedown="WdatePicker({dateFmt:'H小时mm分',minDate:'0小时:0分',maxDate:'24小时:0分',isShowClear:false,isShowToday:false})" class="Wdate txtW1" value="0小时15分" /></div>
    <hr />
    <div><input icon='icon-success' type="button" onclick="closeAndSetTime(1)" style="width:80px;" value="确认" />&nbsp;&nbsp;<input icon='icon-cancel' type="button" onclick="closeAndSetTime(0)" style="width:80px;" value="取消"/></div>
</div>

	</body>
</html>
