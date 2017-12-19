<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<%@ include file="../common/incFooter.jsp"%>
		<%@ include file="../common/incBanner.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		
		<%@ include file="../common/incHead.jsp"%>
				<script type="text/javascript" src="../js/divselect.js"></script>  
		
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
	</script>
	</head>
	<body style="min-width:750px;">

		 <div id="comInfo" class="fixed">
		   <div id="contain" class="fixed">
             <s:include value="../system/head_div.jsp" ></s:include>
		
			<div class="tqyj fixed" >
                     <div class="cxjj fixed">
                          <div class="rechargey cxjjtable" style="position:absolute; width: 80%">
								<div align="center">
									<s:form id="form0" name="form0" action="listCardLib" namespace="/cardLib" method="post">
									<table width="730px;">
										<tr >
											<td>
											   <div id="divselect8" class="divselect3 divselect" >
                                                              <cite>请选择商品</cite>
                                                                
                                                                   <ul style="height: 200px; overflow: scroll;">
                                                                    <s:iterator value="goodsList" id="goodsList">   
																        <li > <a href="javascript:;" selectid="<s:property value='goodsId'/> "> <s:property value="goodsName"/> </a>
																         </li>
																       </s:iterator>
                                                                    </ul>
                                                      </div>
                                                       <input name="goodsId" type="hidden" value="" id="inputselect"/>
                                                
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect8","#inputselect");
													});
													</script>
											</td>
											<td>
											    <div id="divselect10" class="divselect3 divselect" >
                                                              <cite>选择销售类型</cite>
                                                                   <ul >
																       <li><a href="javascript:;" selectid="0">选择销售类型</a></li>
																       <li><a href="javascript:;" selectid="1">空中充值</a></li>
                                                                      <li><a href="javascript:;" selectid="2">卡密</a></li>
                                                                    </ul>
                                                      </div>
                                                       <input name="sellType" type="hidden" value="" id="inputselect"/>
                                                
                                                <script type="text/javascript">
													$(function(){
														$.divselect("#divselect10","#inputselect");
													});
													</script>
											</td>
											
											<td>销售日期:&nbsp;</td>
											<td width="280px;">&nbsp;<input id="d5221" name="startDate" class="date1" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-{%M-2}-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
													至
													<input id="d5222" name="endDate" class="date2" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>&nbsp;</td>
											<td>&nbsp;<button  type="submit" class="sousuo1" value="搜索"></button> </td>
										</tr>
									</table>
									</s:form>
								</div>
							</div>
							  <p class="jjcx">商品销售情况</p>
					</div>
					<div class="modify">
					   <display:table export="true" id="idListTb" name="pageView" style="width:100%;"
							sort="external" pagesize="${pageSize}" cellspacing="1"
							class="list_tb_002" decorator="com.sys.volunteer.common.OverOutWrapperUtils"
							requestURI="${pageContext.request.contextPath}/cardLib/listCardLib.do">
							<display:setProperty name="paging.banner.placement" value="bottom" />
							<display:column property="cardCode" title="卡密编号" />
							<display:column property="goodsName" title="商品名" />
							<c:if test="${sessionScope.user.usergroup.groupType<5}">
								<display:column property="userName" title="经销商" />
							</c:if>
							<display:column property="cardValue" title="面值" />
							<display:column property="retailPrice" title="进货价" />
							<display:column property="expireTime" title="有效期"
								format="{0,date,yyyy-MM-dd HH:mm}" />
							<display:column title="购买">
								<c:choose>
									<c:when test="${idListTb.state==1}">未使用</c:when>
									<c:when test="${idListTb.state==2}">已提取</c:when>
									<c:when test="${idListTb.state==3}">已过期</c:when>
									<c:otherwise>未知</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="销售类型">
								<c:choose>
									<c:when test="${idListTb.sellType==1}">空中充值</c:when>
									<c:when test="${idListTb.sellType==2}">卡密</c:when>
								</c:choose>
							</display:column>
							<display:column property="buyTime" title="购买时间"
								format="{0,date,yyyy-MM-dd HH:mm}" />
							<display:column property="remark" title="备注" />
							<display:setProperty name="export.excel.filename" value="times.xls"/> 
						</display:table>
					</div>
			</div>		
		</div>
	</div>
					<!-- 
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="listCardLib" namespace="/cardLib"
			method="post">
				<br/>
				<table>
					<tr>
						<td>&nbsp;<s:select name="goodsId" list="goodsList" listKey="goodsId" listValue="goodsName"></s:select>&nbsp;</td>
						<td>&nbsp;<s:select name="sellType" list="%{#{0:'===选择销售类型===',1:'空中充值',2:'卡密'}}" listKey="key"></s:select>&nbsp;</td>
						<td>销售日期:&nbsp;</td>
						<td>&nbsp;<input id="d5221" name="startDate" class="Wdate" type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
								至
								<input id="d5222" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/>&nbsp;</td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				</s:form>
		</div>
		<br/>
		<center>
			<display:table export="true" id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/cardLib/listCardLib.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption>商品销售情况</display:caption>
				<display:column property="cardCode" title="卡密编号" />
				<display:column property="goodsName" title="商品名" />
				<c:if test="${sessionScope.user.usergroup.id<5}">
					<display:column property="userName" title="经销商" />
				</c:if>
				<display:column property="cardValue" title="面值" />
				<display:column property="retailPrice" title="进货价" />
				<display:column property="expireTime" title="有效期"
					format="{0,date,yyyy-MM-dd HH:mm}" />
				<display:column title="购买">
					<c:choose>
						<c:when test="${idListTb.state==1}">未使用</c:when>
						<c:when test="${idListTb.state==2}">已提取</c:when>
						<c:when test="${idListTb.state==3}">已过期</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="销售类型">
					<c:choose>
						<c:when test="${idListTb.sellType==1}">空中充值</c:when>
						<c:when test="${idListTb.sellType==2}">卡密</c:when>
					</c:choose>
				</display:column>
				<display:column property="buyTime" title="购买时间"
					format="{0,date,yyyy-MM-dd HH:mm}" />
				<display:column property="remark" title="备注" />
				<display:setProperty name="export.excel.filename" value="times.xls"/> 
			</display:table>
		</center>
		<br />
		<br />
		 -->
		<script type="text/javascript">
			displaytagExportLink();
	    </script>
		<a href="user!add.html"></a>
		
	</body>
</html>
