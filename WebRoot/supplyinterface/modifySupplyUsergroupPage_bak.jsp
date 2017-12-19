
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/divselect.js"></script>
		<script type="text/javascript">
	
	$(document).ready(function() {
		$(".check_checkbox_permission").click(function(){
	    
	     var removeIdTemp = $("#removeIdTemp").val();
		      
		      if($(this).attr("checked")==false){
		          //$(this).attr("name","removeIds");
		          if(removeIdTemp.length<=0||removeIdTemp==""){
			          document.getElementById("removeIdTemp").value=$(this).val();
		          }else{
			          document.getElementById("removeIdTemp").value+=(","+$(this).val());
		          }
		      }
		     
		});
		$(".nocheck_checkbox_permission").click(function(){
		 var addIdTemp = $("#addIdTemp").val();
		      if($(this).attr("checked")==true){
		         // $(this).attr("name","addId");
		          if(addIdTemp.length<=0||addIdTemp==""){
			          document.getElementById("addIdTemp").value= $(this).val();
		          }else{
			          document.getElementById("addIdTemp").value+=(","+$(this).val());
		          }
		          
		      }
		    
		});
	})
	
</script>
		<style type="text/css">
 
       </style>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>

		<div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<p class="jjcx">
							权限修改
						</p>
						<div class="quota cxjjtable" style="width: 700px; float: left;">
							<s:form id="form1" name="form0"
							action="setSupplyUsergroup!findByCondition" namespace="/supplyInterface"
							method="post">
								<table width="100%">
									<tr >
										<td><s:select name="supplyId" list="supplyList" listKey="id" listValue="supplyName"></s:select>
										  <input type="hidden" name="usergroupId" value="${usergroupId }"/>
										</td>
										<td>面额：<input type="text" name="value" value="${vlaue }"/> </td>
										<td><s:select name="state" list="%{#{-1:'===选择状态===',0:'禁用',1:'可用'}}"/></td>
										<td><s:select name="canReverse" list="%{#{-1:'===选择冲正状态===',0:'不可冲正',1:'可冲正'}}"/></td>
										<td>&nbsp;&nbsp;&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
									</tr>
								</table>
							</s:form>
						</div>
					</div>
					<div class="modify">
						<div class="cxjjtable3" style="max-height: 700px;overflow-y: scroll;">
						<form action="/MPRS/supplyInterface/setSupplyUsergroup!addSupplyUsergroup.do">
						   
							   <input type="hidden" value="${usergroupId}" name="usergroupId"/>
							   <input type="hidden" value="" name="addId" id="addIdTemp"/>
							   <input type="hidden" value="" name="removeId" id="removeIdTemp"/>
							<table border="1" cellpadding="3" cellspacing="0" style="width: 80%;margin:auto" class="list_tb_002">
							   <tr >
							     <th>id</th>
							     <th>供货商id</th>
							     <th>供货商名</th>
							     <th>商品id </th>
							     <th>商品名  </th>
							     <th>进货价  </th>
							     <th>销售价  </th>
							     <th>面额  </th>
							     <th>状态  </th>
							     <th>能否冲正  </th>
							     <th>操作  </th>
							   </tr>
							  <s:iterator value="sppinUsergroupList">
							   <tr>
							     <td>${id } </td>
							     <td>${supplyId } </td>
							     <td>${supplyName }</td>
							     <td> ${goodsId }</td>
							     <td> ${goodsName }</td>
							     <td> ${stockPrice }</td>
							     <td> ${retailPrice }</td>
							     <td> ${value }</td>
							     <td> 
								     <c:choose>
										<c:when test="${state==0}">禁用</c:when>
										<c:when test="${state==1}">可用</c:when>
									 </c:choose>
							     </td>
							     <td>  
								     <c:choose>
										<c:when test="${canReverse==0}">不可冲正</c:when>
										<c:when test="${canReverse==1}">可冲正</c:when>
									 </c:choose>
							     </td>
							     <td>
							         <c:if test="${usergroupId >0}">
								     	 <input class="check_checkbox_permission" type="checkbox"  value="${id }" checked="checked"/> 
							         </c:if>
								     <c:if test = "${usergroupId ==null}">
								     	 <input class="nocheck_checkbox_permission" type="checkbox" value="${id }"/> 
								     </c:if>
							      </td>
							   </tr>
							  </s:iterator> 
							  <tr>
							    <td colspan="11">
							       <button type="submit" >提交</button>
							    </td>
							  </tr>
							</table>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>	
			<br />
			<br />
			<script type="text/javascript">
				displaytagExportLink();
			</script>
			<a href="user!add.html"></a>
			<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
