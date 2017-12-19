<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		
		<script type="text/javascript">
			function selectUser(id,username){
				var userpack=new Object();
				userpack.uid=id;				
				userpack.username=username;				
				window.returnValue = userpack;
				window.close(); 
			}
            function doDel(){
              var ids = document.getElementsByName("id");
              var value="";
              for (var i=0;i<ids.length;i++ ){
              if(ids[i].checked){ //判断复选框是否选中
              value=value+ids[i].value+"_"; //值的拼凑 .. 具体处理看你的需要,
              }
              }
             //alert(value);//输出你选中的那些复选框的值
             window.location.href="doDel.do?supplyInterfaceId="+value;
            }                 
            function doRecover(){
              var ids = document.getElementsByName("id");
              var value="";
              for (var i=0;i<ids.length;i++ ){
              if(ids[i].checked){ //判断复选框是否选中
              value=value+ids[i].value+"_"; //值的拼凑 .. 具体处理看你的需要,
              }
              }
             //  alert(value);//输出你选中的那些复选框的值
             window.location.href="doRecover.do?supplyInterfaceId="+value;
            }       
            
            $(document).ready(function(){
               //-------------------->复选框的单选，全选，反选；
		      $(".checkAll").click(function(){
		         if($(".checkAll").attr("checked")==true){
		             $("[name='id']").attr("checked",'true');
		         }else{
		             $("[name='id']").removeAttr("checked",'true');
		         }
		      })
		       
		       $(".check").click(function(){
		       //记录选中项
		          var checked = "";
		         $("[name='id']").each(function(){
		           if($(this).attr("checked")==true){
		               $(".checkAll").attr("checked",'true');
		               checked+=$(this).val();
		           }
		           })
		           if(checked==""){
		             $(".checkAll").removeAttr("checked",'true');
		           }
		       })
		       //------------------------------->复选框选择结束
               
            
            })
		</script>
		<!-- 不创建新窗口，在本窗口显示东西 -->
		<base target="_self">
	</head>
	<body style="min-width:1000px;">
		<%@ include file="../common/incBanner.jsp"%>
		<!-- 
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form1" name="form0"
			action="list!listInterface" namespace="/supplyInterface"
			method="post">
			<br/>
				<table>
					<tr>
					    <td>商品名称:<s:textfield name="goodsName"/></td>
						<td><s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select></td>
						<td>&nbsp;<s:select name="goodsId" list="goodsList" listKey="goodsId" listValue="goodsName"></s:select>&nbsp;</td>
						<td><s:select name="state" list="%{#{-1:'===选择状态===',0:'禁用',1:'可用'}}"/></td>
						<td><s:select name="canReverse" list="%{#{-1:'===选择冲正状态===',0:'禁用',1:'可用'}}"/></td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
			</s:form>
		</div>
				<br />
					<display:table id="idListTb" name="pageView" style="width:600px;"
						sort="external" pagesize="${pageSize}" cellspacing="1"
						class="list_tb"
						requestURI="${pageContext.request.contextPath}/supplyInterface/list!listInterface.do">
						<display:column title="供货商" property="supplyName"/>
						<display:column title="商品名称" property="goodsName"/>
						<display:column title="进货价" >
							<fmt:formatNumber maxFractionDigits="4" value="${idListTb.stockPrice }"/>
						</display:column>
						<display:column title="销售价" >
							<fmt:formatNumber maxFractionDigits="4" value="${idListTb.retailPrice }"/>
						</display:column>
						<display:column title="面额" property="value"/>
						<display:column title="库存警示" property="defaultAlarm"/>
						<display:column title="状态" >
							<c:choose>
								<c:when test="${idListTb.state==0}">禁用</c:when>
								<c:when test="${idListTb.state==1}">可用</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="冲正状态" >
							<c:choose>
								<c:when test="${idListTb.canReverse==0}">冲正禁用</c:when>
								<c:when test="${idListTb.canReverse==1}">冲正可用</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="客户类型" >
							<c:choose>
								<c:when test="${idListTb.clientType==1}">所有可用</c:when>
								<c:when test="${idListTb.clientType==2}">系统可用</c:when>
								<c:when test="${idListTb.clientType==3}">新泛联可用</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="操作">
							<a href="page!update.do?supplyInterfaceId=${idListTb.id}">修改</a>&nbsp;
							<%--
							<c:choose>
								<c:when test="${idListTb.state==0}"><a href="doRecover.do?supplyInterfaceId=${idListTb.id}">恢复</a>&nbsp;</c:when>
								<c:when test="${idListTb.state==1}"><a href="doDel.do?supplyInterfaceId=${idListTb.id}">禁用</a>&nbsp;</c:when>
							</c:choose>
							 --%>
							<input type="checkbox" name="id" value="${idListTb.id}"/>
						</display:column>
					</display:table>
				
			<center>
			   <input type="button" value="禁用" onclick="doDel()"/>
			   <input type="button" value="恢复" onclick="doRecover()"/>
			</center>
			
		</center>
		<br />
		<br />
		 -->
		 <div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<div class="quota cxjjtable" style="width: 900px; float: left;">
							<s:form id="form1" name="form0"
							action="list!listInterface" namespace="/supplyInterface"
							method="post">
								<table >
									<tr >
									    <td>商品名称:<s:textfield name="goodsName"/></td>
										<td><s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select></td>
										<td><s:select name="goodsId" list="goodsList" listKey="goodsId" listValue="goodsName"></s:select></td>
										<td><s:select name="goodsTypeId" list="goodsTypeList" listKey="goodsTypeId" listValue="goodsTypeName"></s:select></td>
										<td><s:select name="state" list="%{#{-1:'===选择状态===',0:'禁用',1:'可用'}}"/></td>
										<td><s:select name="canReverse" list="%{#{-1:'===选择冲正状态===',0:'禁用',1:'可用'}}"/></td>
										<td>&nbsp;&nbsp;&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
									</tr>
								</table>
							</s:form>
						</div>
						<%--
						<p class="jjcx">
							供货商对应商品列表
						</p>
						 --%>

					</div>
					<div class="modify" style="float: left;">
					
                        <display:table id="idListTb" name="pageView" style="width:600px;"
						sort="external" pagesize="${pageSize}" cellspacing="1"
						class="list_tb_002"
						requestURI="${pageContext.request.contextPath}/supplyInterface/list!listInterface.do">
						<display:column title="供货商" property="supplyName"/>
						<display:column title="商品名称" property="goodsName"/>
						<display:column title="进货价" >
							<fmt:formatNumber maxFractionDigits="4" value="${idListTb.stockPrice }"/>
						</display:column>
						<display:column title="销售价" >
							<fmt:formatNumber maxFractionDigits="4" value="${idListTb.retailPrice }"/>
						</display:column>
						<display:column title="面额" property="value"/>
						<display:column title="库存警示" property="defaultAlarm"/>
						<display:column title="91ka对应商品id" property="kaCardId"/>
						<display:column title="91ka对应商品基数" property="kaBaseNum"/>
						<display:column title="状态" >
							<c:choose>
								<c:when test="${idListTb.state==0}">禁用</c:when>
								<c:when test="${idListTb.state==1}">可用</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="冲正状态" >
							<c:choose>
								<c:when test="${idListTb.canReverse==0}">冲正禁用</c:when>
								<c:when test="${idListTb.canReverse==1}">冲正可用</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="客户类型" >
							<c:choose>
								<c:when test="${idListTb.clientType==1}">所有可用</c:when>
								<c:when test="${idListTb.clientType==2}">系统可用</c:when>
								<c:when test="${idListTb.clientType==3}">新泛联可用</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</display:column>
						<display:column media="html" title="操作   <input type='checkbox' class='checkAll' name='checkbox' value='0' />">
							<a href="page!update.do?supplyInterfaceId=${idListTb.id}">修改</a>&nbsp;
							<%--
							<c:choose>
								<c:when test="${idListTb.state==0}"><a href="doRecover.do?supplyInterfaceId=${idListTb.id}">恢复</a>&nbsp;</c:when>
								<c:when test="${idListTb.state==1}"><a href="doDel.do?supplyInterfaceId=${idListTb.id}">禁用</a>&nbsp;</c:when>
							</c:choose>
							 --%>
							<input type="checkbox" calss="check" name="id" value="${idListTb.id}"/>
						</display:column>
					</display:table>
			
			
			   <input type="button" value="禁用" onclick="doDel()"/>
			   <input type="button" value="恢复" onclick="doRecover()"/>
			
					</div>
				</div>
			</div>
		</div>	
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
